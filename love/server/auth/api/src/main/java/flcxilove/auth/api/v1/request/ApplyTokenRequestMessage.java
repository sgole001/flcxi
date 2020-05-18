package flcxilove.auth.api.v1.request;

/**
 * 请求消息：申请安全访问令牌
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class ApplyTokenRequestMessage {

  /**
   * 用户登录ID
   */
  private String loginId;

  /**
   * 用户登录凭证
   */
  private String loginCert;

  /**
   * 申请渠道
   */
  private String source;

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public String getLoginCert() {
    return loginCert;
  }

  public void setLoginCert(String loginCert) {
    this.loginCert = loginCert;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }
}
