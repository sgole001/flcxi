package flcxilove.governance.mybatis.plugin.strategy.impl.datafill;

import flcxilove.governance.mybatis.plugin.strategy.TableFieldFillStrategy;
import java.util.Date;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 日期数据填充策略
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-16 16:12
 */
public class DateFillStrategy implements TableFieldFillStrategy {

  @Override
  public <T> Object fill(SqlCommandType type, T... entity) {
    return new Date();
  }
}
