package flcxilove.governance.shiro.constant;

/**
 * Shiro常量接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-19 16:23
 */
public interface ShiroConstant extends ShiroMessageConstant {

  /**
   * JWT认证HTTP头部属性名
   */
  String JWT_AUTH_HTTP_HEAD = "jwt";

  /**
   * Shiro针对Restful拦截过滤链分割符（URI|||Rest Method eg:/users|||Post）
   */
  String REST_CHAIN_PATTERN_SEPARATOR = "\\|\\|\\|";
}
