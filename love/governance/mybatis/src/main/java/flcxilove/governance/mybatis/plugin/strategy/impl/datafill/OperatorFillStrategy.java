package flcxilove.governance.mybatis.plugin.strategy.impl.datafill;

import flcxilove.governance.mybatis.plugin.strategy.TableFieldFillStrategy;
import javax.annotation.Resource;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 操作人数据填充策略
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-16 16:15
 */
public class OperatorFillStrategy implements TableFieldFillStrategy {

  @Override
  public <T> Object fill(SqlCommandType type, T... entity) {
    return "Lambert";
  }
}
