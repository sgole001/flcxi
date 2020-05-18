package flcxilove.governance.mybatis.plugin.factory;

import flcxilove.governance.mybatis.plugin.strategy.ComFieldStrategy;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 数据库共通字段处理策略工厂接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-15 13:49
 */
public interface ComFieldStrategyFactory {

  /**
   * 数据库共通字段操作策略创建
   *
   * @param sqlCommandType SQL操作类型
   *
   * @return 操作策略实例
   */
  ComFieldStrategy createStrategy(SqlCommandType sqlCommandType);
}
