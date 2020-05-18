package flcxilove.user.api.v1.dto.response.data;

import java.util.List;

/**
 * 注册用户数据
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-04 11:55
 */
public class UserData {

  /**
   * 用户ID
   */
  private String uid;

  /**
   * 登录ID
   */
  private String loginId;

  /**
   * 账号注册源
   */
  private String source;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 手机
   */
  private String mobile;

  /**
   * 账号密码
   */
  private String password;

  /**
   * 第三方外部UID
   */
  private String externalId;

  /**
   * 创建时IP
   */
  private String creatIp;

  /**
   * 最后登陆IP
   */
  private String lastLoginIp;

  /**
   * 登陆次数
   */
  private Integer loginTimes;

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
  private String gender;

  /**
   * 用户所属组列表
   */
  private List<String> groups;

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getExternalId() {
    return externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

  public String getCreatIp() {
    return creatIp;
  }

  public void setCreatIp(String creatIp) {
    this.creatIp = creatIp;
  }

  public String getLastLoginIp() {
    return lastLoginIp;
  }

  public void setLastLoginIp(String lastLoginIp) {
    this.lastLoginIp = lastLoginIp;
  }

  public Integer getLoginTimes() {
    return loginTimes;
  }

  public void setLoginTimes(Integer loginTimes) {
    this.loginTimes = loginTimes;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public List<String> getGroups() {
    return groups;
  }

  public void setGroups(List<String> groups) {
    this.groups = groups;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }
}
