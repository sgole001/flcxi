package flcxilove.governance.druid.configuration.bean;

import org.springframework.stereotype.Component;

/**
 * Druid监控服务配置信息Bean
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-22 13:47
 */
@Component
public class StatViewServletConfBean {

  /**
   * 内部常量
   */
  public interface Constant {

    /**
     * 属性Key前缀
     */
    String PREFIX = "druid.stat-view-servlet";

    /**
     * 属性Key名
     */
    interface Key {

      String _LOGIN_USER_NAME = "loginUsername";
      String _LOGIN_PASSWORD = "loginPassword";
      String _ALLOW = "allow";
      String _DENY = "deny";
      String _REST_ENABLE = "resetEnable";
    }
  }

  /**
   * Druid监控服务URL
   */
  private String urlPattern = "/druid/*";

  /**
   * 登录用户名
   */
  private String loginUsername;

  /**
   * 登录密码
   */
  private String loginPassword;

  /**
   * 访问控制:允许
   */
  private String allow = "127.0.0.1";

  /**
   * 访问控制:拒绝
   */
  private String deny;

  /**
   * 允许清空统计数据
   */
  private Boolean resetEnable;

  public String getUrlPattern() {
    return urlPattern;
  }

  public void setUrlPattern(String urlPattern) {
    this.urlPattern = urlPattern;
  }

  public String getLoginUsername() {
    return loginUsername;
  }

  public void setLoginUsername(String loginUsername) {
    this.loginUsername = loginUsername;
  }

  public String getLoginPassword() {
    return loginPassword;
  }

  public void setLoginPassword(String loginPassword) {
    this.loginPassword = loginPassword;
  }

  public String getAllow() {
    return allow;
  }

  public void setAllow(String allow) {
    this.allow = allow;
  }

  public String getDeny() {
    return deny;
  }

  public void setDeny(String deny) {
    this.deny = deny;
  }

  public Boolean getResetEnable() {
    return resetEnable;
  }

  public void setResetEnable(Boolean resetEnable) {
    this.resetEnable = resetEnable;
  }
}
