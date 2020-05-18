package flcxilove.governance.mybatis.plugin.strategy;

import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 数据库字段填充策略接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-15 18:28
 */
public interface TableFieldFillStrategy {

  /**
   * 数据填充
   *
   * @param type 数据库操作类型
   * @param entity 策略处理所需数据主体
   *
   * @return 填充值
   */
  <T> Object fill(SqlCommandType type, T... entity);
}
