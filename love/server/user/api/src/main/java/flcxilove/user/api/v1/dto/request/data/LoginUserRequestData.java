package flcxilove.user.api.v1.dto.request.data;

/**
 * 用户登录请求数据
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-24 11:31
 */
public class LoginUserRequestData {

  /**
   * 登录ID
   */
  private String loginId;

  /**
   * 账号源
   */
  private String source;

  /**
   * 账号密码
   */
  private String password;

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
