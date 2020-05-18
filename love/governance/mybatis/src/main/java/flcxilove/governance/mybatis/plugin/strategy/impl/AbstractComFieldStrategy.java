package flcxilove.governance.mybatis.plugin.strategy.impl;

import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLStatement;
import flcxilove.common.tools.ReflectUtil;
import flcxilove.governance.mybatis.annotation.TableField;
import flcxilove.governance.mybatis.bean.CommonDbFieldFillStrategy;
import flcxilove.governance.mybatis.bean.entity.Entity;
import flcxilove.governance.mybatis.constant.MyBatisConstant;
import flcxilove.governance.mybatis.plugin.strategy.ComFieldStrategy;
import flcxilove.governance.mybatis.plugin.strategy.TableFieldFillStrategy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.util.CollectionUtils;

/**
 * 共通字段处理抽象类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-19 00:29
 */
@SuppressWarnings("unchecked")
public abstract class AbstractComFieldStrategy implements ComFieldStrategy {

  /**
   * SQL声明
   */
  private SQLStatement sqlStatement;

  /**
   * 填充策略信息
   */
  private List<CommonDbFieldFillStrategy> fillStrategies;

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(AbstractComFieldStrategy.class.getName());

  @Override
  public BoundSql reform(MappedStatement mappedStatement, Object parameterObject) {

    // 获取原始SQL封装对象
    BoundSql originalBoundSql = mappedStatement.getBoundSql(parameterObject);

    // 从拦截接口参数萃取需要自动填充的数据数据行对象列表
    // 约定：只针对实现了Entity接口的参数对象进行策略解析
    List<Entity> fillTargets = this.extractFillTargets(parameterObject);

    // 没有需要自动填充处理的数据行
    if (CollectionUtils.isEmpty(fillTargets)) {
      return null;
    }

    // 构建SQL声明
    this.setSqlStatement(this.buildSQLStatement(originalBoundSql));

    // 解析填充策略信息
    this.setFillStrategies(this.resolveFillStrategies(fillTargets, mappedStatement.getSqlCommandType()));

    // 无需自动填充操作，原SQL信息返回
    if (CollectionUtils.isEmpty(this.fillStrategies)) {
      return originalBoundSql;
    }

    // 共通字段信息SQL构造
    String resolvedSql = this.resolveSql(originalBoundSql, fillTargets, mappedStatement.getSqlCommandType());

    // 获取额外添加参数信息
    Map<String, Object> additionalParameters = (Map<String, Object>) ReflectUtil.getFieldValue(originalBoundSql, "additionalParameters");

    // 封装共通字段构造SQL对象
    BoundSql resolvedBoundSql = this.boundResolvedSql(mappedStatement, resolvedSql, parameterObject, additionalParameters);

    // 合并整合后返回新构造SQL封装对象
    return this.mergeBoundSQL(mappedStatement, parameterObject, originalBoundSql, resolvedBoundSql);
  }

  /**
   * 构建SQL声明
   *
   * @param originalSql 改造前原始SQL封装对象
   *
   * @return SQL声明
   */
  protected abstract SQLStatement buildSQLStatement(BoundSql originalSql);

  /**
   * 解析插入数据并进行共通字段构造
   *
   * @param originalSql 改造前原始SQL封装对象
   * @param parameters 需要填充的参数信息
   * @param sqlCommandType 数据库操作命令类型
   *
   * @return 解析并构造后SQL
   */
  protected abstract String resolveSql(BoundSql originalSql, List<Entity> parameters, SqlCommandType sqlCommandType);

  /**
   * 解析填充策略信息
   *
   * @param fillTargets 需要填充的参数信息
   * @param sqlCommandType 数据库操作命令类型
   *
   * @return 填充策略信息 Map<持久层对象名, 填充策略列表>
   */
  @Nullable
  private List<CommonDbFieldFillStrategy> resolveFillStrategies(List<Entity> fillTargets, SqlCommandType sqlCommandType) {

    // 拦截SQL处理持久层对象名
    SQLName persistenceName = (SQLName) ReflectUtil.invoke(this.sqlStatement, "getTableName", null);
    // 无法获取持久层对象名（非插入更新操作）
    if (persistenceName == null) {
      return null;
    }

    // 持久层对象去重后解析策略
    Optional<Entity> fillTarget = fillTargets.stream()
        // 持久层对象去重
        .filter(distinctByKey(target -> ReflectUtil.getFieldValue(target, "PERSISTENCE_MAPPER")))
        // 只针对操作持久层对象进行自动填充
        .filter(target -> persistenceName.getSimpleName().equalsIgnoreCase((String) ReflectUtil.getFieldValue(target, "PERSISTENCE_MAPPER")))
        // 自动填充字段信息解析
        .filter(target -> (long) ReflectUtil.getFieldsWithAnnotation(target.getClass(), TableField.class).size() > 0)
        // 构建
        .findFirst();

    // 没有需要自动填充处理的数据行
    if (fillTarget.isEmpty()) {
      return null;
    }

    // 构建持久层字段填充策略映射并返回
    return this.buildFillStrategies(fillTarget.get(), sqlCommandType);
  }

  /**
   * SQL回溯注入项目
   *
   * @param boundSql SQL封装对象
   *
   * @return 回溯注入项目后SQL
   */
  String recallSqlInjection(BoundSql boundSql) {

    // 回溯SQL注入项目
    String recallSql = boundSql.getSql();

    for (ParameterMapping parameterMapping : boundSql.getParameterMappings()) {
      recallSql = recallSql.replaceFirst("\\?", MyBatisConstant.PARAM_VAR_PREFIX.concat(parameterMapping.getProperty()).concat(MyBatisConstant.PARAM_VAR_SUFFER));
    }

    return recallSql;
  }

  /**
   * 封装共通字段构造SQL对象
   *
   * @param mappedStatement Mapper文件陈述对象信息
   * @param resolvedSql 改造前原始SQL封装对象
   * @param parameterObject 参数信息
   * @param additionalParameters 额外添加参数信息
   *
   * @return 共通字段构造后SQL封装对象
   */
  private BoundSql boundResolvedSql(@NotNull MappedStatement mappedStatement, String resolvedSql, @NotNull Object parameterObject, Map<String, Object> additionalParameters) {

    // 新构建SQL语法分析器
    SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(mappedStatement.getConfiguration());
    // 分析构造后SQL信息
    SqlSource sqlSource = sqlSourceParser.parse(resolvedSql, parameterObject.getClass(), additionalParameters);

    return sqlSource.getBoundSql(parameterObject);
  }

  /**
   * 合并整合SQL封装对象
   *
   * @param mappedStatement Mapper文件陈述对象信息
   * @param parameterObject 参数信息
   * @param originalBoundSql 改造前原始SQL封装对象
   * @param resolvedBoundSql 共通字段构造后SQL封装对象
   *
   * @return 合并整合后SQL封装对象
   */
  private BoundSql mergeBoundSQL(MappedStatement mappedStatement, Object parameterObject, BoundSql originalBoundSql, BoundSql resolvedBoundSql) {

    // 构建合并整合后SQL封装对象
    BoundSql boundSql = new BoundSql(mappedStatement.getConfiguration(), resolvedBoundSql.getSql(), resolvedBoundSql.getParameterMappings(), parameterObject);
    // 获取额外参数映射信息
    // 额外参数信息添加（附带前缀动态参数需要额外添加，否则在后续的字段反射中会查不到此对象，例如<foreach>，共通参数等）
    Map<String, Object> additionalParameters = (Map<String, Object>) ReflectUtil.getFieldValue(originalBoundSql, "additionalParameters");
    // 手动设定属性【additionalParameters】
    ReflectUtil.setFieldValue(boundSql, "additionalParameters", additionalParameters);
    // 手动设定属性【metaParameters】
    MetaObject metaObject = mappedStatement.getConfiguration().newMetaObject(additionalParameters);
    ReflectUtil.setFieldValue(boundSql, "metaParameters", metaObject);

    return boundSql;
  }

  /**
   * 共通字段数据填充
   *
   * @param parameter 填充数据对象
   * @param sqlCommandType 数据库操作命令类型
   */
  void fillCommonField(Entity parameter, SqlCommandType sqlCommandType) {

    this.fillStrategies.forEach(strategy -> {
      try {
        // 获取
        TableFieldFillStrategy instances = (TableFieldFillStrategy) strategy.getHandleCls().getDeclaredConstructor().newInstance();

        ReflectUtil.setFieldValue(parameter, strategy.getName(), instances.fill(sqlCommandType));
      } catch (Exception e) {
        logger.error(e);
      }
    });

  }

  /**
   * 从拦截接口参数萃取需要自动填充的数据数据行对象列表
   *
   * @param parameterObject SQL执行映射接口参数对象
   *
   * @return 自动填充的数据数据行对象列表
   */
  private List<Entity> extractFillTargets(Object parameterObject) {

    // 填充对象列表
    List<Entity> fillTargets = new ArrayList<>();

    // 多参数 | 单参数使用@Param注解
    if (parameterObject instanceof Map) {
      ((Map) parameterObject).values().forEach(param -> fillTargets.addAll(this.extractFillTargets(param)));
    }
    // 单参数集合数据且未使用@Param注解 | 递归调用
    else if (parameterObject instanceof List) {
      ((List) parameterObject).forEach(param -> fillTargets.addAll(this.extractFillTargets(param)));
    }
    // 单参数非集合数据且未使用@Param注解 | 递归调用
    else {
      if (parameterObject instanceof Entity) {
        fillTargets.add((Entity) parameterObject);
      }
    }

    return fillTargets.stream().distinct().collect(Collectors.toList());
  }

  /**
   * 构建持久层字段填充策略映射
   *
   * @param fillTarget 自动填充对象
   * @param sqlCommandType 数据库操作命令类型
   *
   * @return 持久层字段填充策略列表
   */
  private List<CommonDbFieldFillStrategy> buildFillStrategies(@NotNull Entity fillTarget, SqlCommandType sqlCommandType) {

    // 持久层字段填充策略映射
    List<CommonDbFieldFillStrategy> strategies = new ArrayList<>();

    // 获取自动填充字段信息
    List<Field> fields = ReflectUtil.getFieldsWithAnnotation(fillTarget.getClass(), TableField.class);

    fields.forEach(field -> {

      // 获取注解对象
      TableField annotation = field.getAnnotation(TableField.class);
      // 获取数据库操作命令类型
      List<SqlCommandType> sqlCommandTypes = Arrays.asList(annotation.cmd());

      // 只针对持久层插入和更新操作进行字段自动填充处理
      if (sqlCommandTypes.contains(sqlCommandType)) {

        // 字段自动填充策略信息
        CommonDbFieldFillStrategy strategy = new CommonDbFieldFillStrategy();
        // 填充数据行对象字段名
        strategy.setName(field.getName());
        // 填充持久层对象列名
        strategy.setColumn(annotation.col());
        // 填充策略处理类
        strategy.setHandleCls(annotation.handle());

        strategies.add(strategy);
      }
    });

    return strategies;
  }

  /**
   * 格式化SQL参数变量
   *
   * @param field 字段名
   * @param alias 别名
   *
   * @return 格式化SQL参数变量
   */
  String formatSqlValueParam(String field, String alias) {

    // 参数变量格式#{__com_$idx.$col}
    return MyBatisConstant.PARAM_VAR_PREFIX.concat(alias).concat(".").concat(field).concat(MyBatisConstant.PARAM_VAR_SUFFER);
  }

  /**
   * 函数式：根据指定键值去重
   *
   * @param keyExtractor 函数式
   * @param <T> 去除对象范型
   *
   * @return 断言函数式
   */
  @NotNull
  @Contract(pure = true)
  private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

  SQLStatement getSqlStatement() {
    return sqlStatement;
  }

  private void setSqlStatement(SQLStatement sqlStatement) {
    this.sqlStatement = sqlStatement;
  }

  List<CommonDbFieldFillStrategy> getFillStrategies() {
    return fillStrategies;
  }

  private void setFillStrategies(List<CommonDbFieldFillStrategy> fillStrategies) {
    this.fillStrategies = fillStrategies;
  }
}
