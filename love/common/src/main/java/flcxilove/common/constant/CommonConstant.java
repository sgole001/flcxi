package flcxilove.common.constant;

/**
 * 公共常量接口
 *
 * @author: sgole
 * @since: 2018/11/8
 * @version: 1.0
 */
public interface CommonConstant {

  /**
   * 成功状态
   */
  Integer SUCCESS = 1;

  /**
   * 失败状态
   */
  Integer FAILURE = 0;

  /**
   * 属性值_服务名
   */
  String PROPERTY_KEY_APPLICATION_NAME = "spring.application.name";

  /**
   * Squid默认设置forwarded_for项默认是为on，如果 forwarded_for设成了 off　则：X-Forwarded-For： unknown
   */
  String SQUID_OFF_IP_ADDRESS = "unknown";

  /**
   * 回送IP地址
   */
  String LOOPBACK_IP_ADDRESS = "127.0.0.1";
}
