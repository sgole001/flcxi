package flcxilove.auth.service.dto;

import java.util.List;

/**
 * 令牌申请数据DTO
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-26 11:35
 */
public class AuthDto {

  /**
   * 用户ID
   */
  private String uid;

  /**
   * 令牌签名原始密钥
   */
  private String secretKey;

  /**
   * 令牌申请源平台
   */
  private String source;

  /**
   * 用户所属角色列表
   */
  private List<String> roles;

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }
}
