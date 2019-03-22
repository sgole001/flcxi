package flcxilove.governance.druid.configuration.bean;

import org.springframework.stereotype.Component;

/**
 * Druid Web过滤规则配置信息Bean
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-22 14:57
 */
@Component
public class WebStatFileterConfBean {

  /**
   * 内部常量
   */
  public interface Constant {

    /**
     * 属性Key前缀
     */
    String PREFIX = "druid.web-stat-filter";

    /**
     * 属性Key名
     */
    interface Key {

      String _EXCLUSIONS = "exclusions";
      String _PRINCIPAL_COOKIE_NAME = "principalCookieName";
      String _PRINCIPAL_SESSION_NAME = "principalSessionName";
      String _PROFILE_ENABLE = "profileEnable";
      String _SESSION_STAT_ENABLE = "sessionStatEnable";
      String _SESSION_STAT_MAX_COUNT = "sessionStatMaxCount";
    }
  }

  /**
   * URL过滤规则
   */
  private String urlPattern = "/*";

  /**
   * URL排除规则
   */
  private String exclusions = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*";

  /**
   * 用户信息保存至Cookie中的名字
   */
  private String principalCookieName;

  /**
   * 用户信息保存至Session中的名字
   */
  private String principalSessionName;

  /**
   * 监控单个url调用的sql列表
   */
  private Boolean profileEnable;

  /**
   * session统计功能
   */
  private Boolean sessionStatEnable;

  /**
   * session统计上限
   */
  private Integer sessionStatMaxCount;

  public String getUrlPattern() {
    return urlPattern;
  }

  public void setUrlPattern(String urlPattern) {
    this.urlPattern = urlPattern;
  }

  public String getExclusions() {
    return exclusions;
  }

  public void setExclusions(String exclusions) {
    this.exclusions = exclusions;
  }

  public String getPrincipalCookieName() {
    return principalCookieName;
  }

  public void setPrincipalCookieName(String principalCookieName) {
    this.principalCookieName = principalCookieName;
  }

  public String getPrincipalSessionName() {
    return principalSessionName;
  }

  public void setPrincipalSessionName(String principalSessionName) {
    this.principalSessionName = principalSessionName;
  }

  public Boolean getProfileEnable() {
    return profileEnable;
  }

  public void setProfileEnable(Boolean profileEnable) {
    this.profileEnable = profileEnable;
  }

  public Boolean getSessionStatEnable() {
    return sessionStatEnable;
  }

  public void setSessionStatEnable(Boolean sessionStatEnable) {
    this.sessionStatEnable = sessionStatEnable;
  }

  public Integer getSessionStatMaxCount() {
    return sessionStatMaxCount;
  }

  public void setSessionStatMaxCount(Integer sessionStatMaxCount) {
    this.sessionStatMaxCount = sessionStatMaxCount;
  }
}
