package flcxilove.governance.shiro.realm;

import flcxilove.common.constant.CommonMessageConstant;
import flcxilove.common.exception.SystemException;
import flcxilove.common.tools.JwtUtil;
import flcxilove.governance.redis.tools.RedisUtil;
import flcxilove.governance.shiro.token.JwtAuthenticationToken;
import io.jsonwebtoken.Claims;
import java.util.Map;
import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * ShiroJWT用Realm
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
@Component(value = "jwtRealm")
public class JwtRealm extends AuthorizingRealm {

  /**
   * Jwt工具类
   */
  @Resource
  private JwtUtil jwtUtil;

  /**
   * Redis工具类
   */
  @Resource
  private RedisUtil redisUtil;

  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof JwtAuthenticationToken;
  }

  /**
   * 权限验证处理方法
   *
   * @param principalCollection 用户信息
   *
   * @return 验证信息
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

    // 用户ID
    String clientId = (String) principalCollection.getPrimaryPrincipal();

    // 根据用户ID获取用户权限
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.setRoles(null);
    info.setStringPermissions(null);

    return info;
  }

  /**
   * 用户认证处理方法
   *
   * @param authenticationToken 认证用信息（JWT）
   *
   * @return 认证信息
   *
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

    // 获取JWT信息
    String token = (String) authenticationToken.getCredentials();

    // 获取对称加密密钥
    if (!this.redisUtil.exists(token)) {
      throw new AuthenticationException(new SystemException(CommonMessageConstant.MSG_COM_00000));
    }
    Map<String, String> specKey = (Map<String, String>) this.redisUtil.get(token);
    // 密钥encoded
    byte[] encoded = Base64.decode(specKey.get("encoded"));
    // 密钥算法
    String algorithm = specKey.get("algorithm");
    // 获取密钥
    SecretKeySpec key = new SecretKeySpec(encoded, algorithm);

    // 根据JWT获取用户信息
    try {
      // 检查 token 有效性 (已过期 | 被篡改)
      Claims claims = this.jwtUtil.parseJWT(token, key);

      return new SimpleAuthenticationInfo(claims.getAudience(), token, claims.getSubject());
    } catch (Exception e) {
      throw new AuthenticationException(new SystemException(CommonMessageConstant.MSG_COM_00000));
    }
  }
}
