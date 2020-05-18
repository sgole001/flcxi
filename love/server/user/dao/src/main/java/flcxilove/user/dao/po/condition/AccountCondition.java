package flcxilove.user.dao.po.condition;

import flcxilove.governance.mybatis.bean.entity.Condition;

/**
 * 账户数据处理条件
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-24 15:07
 */
public class AccountCondition implements Condition {

  /**
   * 用户ID
   */
  private String id;

  /**
   * 登录账号
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
}
