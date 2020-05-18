package flcxilove.governance.mybatis.plugin.strategy;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

/**
 * 数据库共通字段处理策略接口（策略模式）
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-15 11:20
 */
public interface ComFieldStrategy {

  /**
   * 改造处理接口
   *
   * @param mappedStatement Mapper文件陈述对象信息
   * @param parameterObject 接口参数（接口只支持INSERT & UPDATE）
   *
   * @return 改造后SQL对象
   */
  BoundSql reform(MappedStatement mappedStatement, Object parameterObject);
}
