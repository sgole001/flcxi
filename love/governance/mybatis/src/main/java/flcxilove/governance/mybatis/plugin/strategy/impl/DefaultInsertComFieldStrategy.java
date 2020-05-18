package flcxilove.governance.mybatis.plugin.strategy.impl;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement.ValuesClause;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.util.JdbcConstants;
import flcxilove.governance.mybatis.bean.CommonDbFieldFillStrategy;
import flcxilove.governance.mybatis.bean.entity.Entity;
import flcxilove.governance.mybatis.constant.MyBatisConstant;
import java.util.List;
import java.util.stream.Stream;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 默认插入处理SQL共通字段策略实现类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-15 16:19
 */
public class DefaultInsertComFieldStrategy extends AbstractComFieldStrategy {

  @Override
  protected MySqlInsertStatement buildSQLStatement(BoundSql originalSql) {

    // SQL回溯注入项目
    String resolveSql = super.recallSqlInjection(originalSql);

    return (MySqlInsertStatement) SQLUtils.parseSingleStatement(resolveSql, JdbcConstants.MYSQL);
  }

  @Override
  protected String resolveSql(BoundSql originalSql, List<Entity> parameters, SqlCommandType sqlCommandType) {

    // 获取SQL声明对象
    MySqlInsertStatement sqlStatement = (MySqlInsertStatement) super.getSqlStatement();
    // 获取字段填充策略信息
    List<CommonDbFieldFillStrategy> fillStrategies = super.getFillStrategies();

    // 获取Insert Value列表
    List<ValuesClause> valuesClauses = sqlStatement.getValuesList();

    // 构建共通字段
    fillStrategies.forEach(strategy -> sqlStatement.addColumn(new SQLIdentifierExpr(strategy.getColumn())));

    // 构建共通字段对应变量
    Stream.iterate(0, index -> index + 1).limit(valuesClauses.size()).forEach(index -> {

      // Value参数
      ValuesClause clause = valuesClauses.get(index);
      // 别名
      String alias = MyBatisConstant.COMMON_FIELD_ALIAS.concat(index.toString());

      // 构建参数
      fillStrategies.forEach(strategy -> clause.addValue(new SQLIdentifierExpr(super.formatSqlValueParam(strategy.getName(), alias))));
      // 共通字段数据填充
      super.fillCommonField(parameters.get(index), sqlCommandType);

      originalSql.setAdditionalParameter(alias, parameters.get(index));
    });

    return sqlStatement.toString();
  }
}
