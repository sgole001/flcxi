package flcxilove.auth.api.v1.response.data;

/**
 * 消息主体：令牌数据
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class TokenData {

  /**
   * 资源访问令牌
   */
  private String accessToken;

  /**
   * 刷新资源访问令牌用令牌
   */
  private String refreshToken;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}
