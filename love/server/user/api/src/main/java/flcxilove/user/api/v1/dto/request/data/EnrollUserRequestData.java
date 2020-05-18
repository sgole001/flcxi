package flcxilove.user.api.v1.dto.request.data;

/**
 * 用户注册请求数据
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-04 11:37
 */
public class EnrollUserRequestData {

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

  /**
   * 昵称
   */
  private String nickName;

  /**
   * 用户头像(路径)
   */
  private String avatar;

  /**
   * 性别
   */
  private Integer gender;

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

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }
}
