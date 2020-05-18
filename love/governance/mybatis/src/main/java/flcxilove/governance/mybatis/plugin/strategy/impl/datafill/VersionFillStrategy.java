package flcxilove.governance.mybatis.plugin.strategy.impl.datafill;

import flcxilove.governance.mybatis.plugin.strategy.TableFieldFillStrategy;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 数据版本（乐观锁）填充策略
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-16 16:17
 */
public class VersionFillStrategy implements TableFieldFillStrategy {

  @Override
  public <T> Object fill(SqlCommandType type, T... entity) {
    return Integer.valueOf(0);
  }
}
