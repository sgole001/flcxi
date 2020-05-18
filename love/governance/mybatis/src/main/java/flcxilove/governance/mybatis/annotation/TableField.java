package flcxilove.governance.mybatis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.JDBCType;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 数据库表字段标识
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-15 18:44
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableField {

  /**
   * 对应数据库列名
   */
  String col();

  /**
   * 数据处理类型
   */
  SqlCommandType[] cmd();

  /**
   * 数据库数据类型
   */
  JDBCType jdbcType() default JDBCType.VARCHAR;

  /**
   * 填充策略处理类
   * <p>继承flcxilove.governance.mybatis.plugin.strategy.TableFieldFillStrategy</p>
   * <p>优先级高于常量填充值</p>
   */
  Class<?> handle();
}
