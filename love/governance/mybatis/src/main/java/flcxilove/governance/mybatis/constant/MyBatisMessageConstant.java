package flcxilove.governance.mybatis.constant;

/**
 * 消息编号常量接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-12 14:59
 */
public interface MyBatisMessageConstant {

  /**
   * 插入SQL[{0}]异常：Value信息为空。
   */
  String MSG_BATIS_00000 = "MSG_BATIS_00000";

  /**
   * 参数行数量和Value行数量不一致。（避免出现整行数据都是常量）
   */
  String MSG_BATIS_00001 = "MSG_BATIS_00001";
}
