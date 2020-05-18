package flcxilove.governance.mybatis.plugin.strategy.impl;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.statement.SQLUpdateSetItem;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlUpdateStatement;
import com.alibaba.druid.util.JdbcConstants;
import flcxilove.governance.mybatis.bean.CommonDbFieldFillStrategy;
import flcxilove.governance.mybatis.bean.entity.Entity;
import flcxilove.governance.mybatis.constant.MyBatisConstant;
import java.util.List;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 默认更新处理SQL共通字段策略实现类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-19 13:53
 */
public class DefaultUpdateComFieldStrategy extends AbstractComFieldStrategy {

  @Override
  protected MySqlUpdateStatement buildSQLStatement(BoundSql originalSql) {

    // SQL回溯注入项目
    String resolveSql = super.recallSqlInjection(originalSql);

    // 解析UpdateSQL, MySql方言
    return (MySqlUpdateStatement) SQLUtils.parseSingleStatement(resolveSql, JdbcConstants.MYSQL);
  }

  @Override
  protected String resolveSql(BoundSql originalSql, List<Entity> parameters, SqlCommandType sqlCommandType) {

    // 获取SQL声明对象
    MySqlUpdateStatement sqlStatement = (MySqlUpdateStatement) super.getSqlStatement();
    // 获取字段填充策略信息
    List<CommonDbFieldFillStrategy> fillStrategies = super.getFillStrategies();

    // 获取Set项目列表信息
    List<SQLUpdateSetItem> updateSetItems = sqlStatement.getItems();

    // 构建更新共通项目
    fillStrategies.forEach(strategy -> {

      // 构建Set项目
      SQLUpdateSetItem setItem = new SQLUpdateSetItem();

      // 构建列信息
      setItem.setColumn(new SQLIdentifierExpr(strategy.getColumn()));
      // 构建值信息
      setItem.setValue(new SQLIdentifierExpr(super.formatSqlValueParam(strategy.getName(), MyBatisConstant.COMMON_FIELD_ALIAS)));

      updateSetItems.add(setItem);
    });

    // 共通字段数据填充
    super.fillCommonField(parameters.get(0), sqlCommandType);
    // 额外参数信息设定
    originalSql.setAdditionalParameter(MyBatisConstant.COMMON_FIELD_ALIAS, parameters.get(0));

    return sqlStatement.toString();
  }
}
