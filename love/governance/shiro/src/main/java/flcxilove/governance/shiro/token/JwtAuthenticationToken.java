package flcxilove.governance.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * ShiroJWT认证用Token
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class JwtAuthenticationToken implements AuthenticationToken {

  /**
   * 序列化和反序列化
   */
  private static final long serialVersionUID = 8422023104805659853L;

  /**
   * JWT信息
   */
  private String token;

  /**
   * 构造函数
   *
   * @param token JWT信息
   */
  public JwtAuthenticationToken(String token) {
    this.token = token;
  }

  @Override
  public Object getPrincipal() {
    return this.token;
  }

  @Override
  public Object getCredentials() {
    return this.token;
  }
}
