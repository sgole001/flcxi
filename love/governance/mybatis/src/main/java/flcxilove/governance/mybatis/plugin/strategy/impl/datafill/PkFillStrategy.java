package flcxilove.governance.mybatis.plugin.strategy.impl.datafill;

import flcxilove.governance.mybatis.plugin.strategy.TableFieldFillStrategy;
import java.util.UUID;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 数据物理主键填充策略
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-15 18:50
 */
public class PkFillStrategy implements TableFieldFillStrategy {

  @Override
  public <T> Object fill(SqlCommandType type, T... entity) {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
}
