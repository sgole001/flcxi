package flcxilove.governance.mybatis.plugin.factory.impl;

import flcxilove.governance.mybatis.plugin.factory.ComFieldStrategyFactory;
import flcxilove.governance.mybatis.plugin.strategy.ComFieldStrategy;
import flcxilove.governance.mybatis.plugin.strategy.impl.DefaultInsertComFieldStrategy;
import flcxilove.governance.mybatis.plugin.strategy.impl.DefaultUpdateComFieldStrategy;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 数据库共通字段处理策略工厂类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-15 11:50
 */
public class DefaultComFieldStrategyFactory implements ComFieldStrategyFactory {

  /**
   * 数据库共通字段操作策略创建
   *
   * @param sqlCommandType SQL操作类型
   *
   * @return 操作策略实例
   */
  @Override
  public ComFieldStrategy createStrategy(SqlCommandType sqlCommandType) {

    switch (sqlCommandType) {
      // 插入操作
      case INSERT:
        return new DefaultInsertComFieldStrategy();
      // 更新操作
      case UPDATE:
        return new DefaultUpdateComFieldStrategy();
      // 其他操作
      default:
        // 不支持除插入和更新以外的数据库操作
        return null;
    }
  }
}
