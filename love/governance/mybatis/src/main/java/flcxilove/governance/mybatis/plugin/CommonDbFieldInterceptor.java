package flcxilove.governance.mybatis.plugin;

import flcxilove.common.tools.ReflectUtil;
import flcxilove.governance.mybatis.plugin.strategy.impl.ComFieldContext;
import java.sql.Connection;
import java.util.Properties;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 数据共通字段处理插件
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-08 10:48
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class CommonDbFieldInterceptor implements Interceptor {

  /**
   * 共通行数据字段处理策略配置属性Key
   */
  public static final String COMMON_FIELD_STRATEGY_CONTEXT = "strategy-context";

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(CommonDbFieldInterceptor.class.getName());

  /**
   * 插件属性配置信息
   */
  private Properties props = null;

  /**
   * 对于StatementHandler其实只有两个实现类，一个是RoutingStatementHandler，另一个是抽象类BaseStatementHandler
   * BaseStatementHandler有三个子类，分别是SimpleStatementHandler，PreparedStatementHandler和CallableStatementHandler
   * Mybatis在进行Sql语句处理的时候都是建立的RoutingStatementHandler，而在RoutingStatementHandler里面拥有一个StatementHandler类型的delegate属性
   * RoutingStatementHandler会依据Statement的不同建立对应的BaseStatementHandler
   *
   * @param invocation
   *
   * @return proceed
   *
   * @throws Throwable
   */
  @Override
  public Object intercept(Invocation invocation) throws Throwable {

    // 拦截对象获取
    RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();

    // 获取拦截对象元对象（未考虑多层代理）
    MetaObject metaObject = SystemMetaObject.forObject(invocation.getTarget());

    // 获取参数处理接口
    ParameterHandler parameterHandler = statementHandler.getParameterHandler();

    // 判定是否是允许的执行操作类型
    if (!this.isAllowedCommand(metaObject)) {
      return invocation.proceed();
    }

    // 获取处理策略
    ComFieldContext comFieldContext = (ComFieldContext) this.props.get(COMMON_FIELD_STRATEGY_CONTEXT);
    // 改造后SQL对象
    BoundSql reformBoundSql = comFieldContext.reform(invocation);

    // 针对metaObject属性"delegate.boundSql"修改不会影响ParameterHandler中的boundSql属性，因此手动反射设定。（OOTB issue？）
    metaObject.setValue("delegate.boundSql", reformBoundSql);
    ReflectUtil.setFieldValue(parameterHandler, "boundSql", reformBoundSql);

    return invocation.proceed();
  }

  /**
   * 判定合法的执行操作类型
   *
   * @param metaObject 拦截对象元对象
   *
   * @return 合法的执行操作类型
   */
  protected Boolean isAllowedCommand(MetaObject metaObject) {

    // 获取MappedStatement
    MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
    // 获取执行命令类型
    SqlCommandType commandType = mappedStatement.getSqlCommandType();

    return SqlCommandType.INSERT.equals(commandType) || SqlCommandType.UPDATE.equals(commandType);
  }

  @Override
  public Object plugin(Object target) {
    if (target instanceof StatementHandler) {
      return Plugin.wrap(target, this);
    }
    return target;
  }

  @Override
  public void setProperties(Properties properties) {
    if (null != properties && !properties.isEmpty()) {
      props = properties;
    }
  }
}
