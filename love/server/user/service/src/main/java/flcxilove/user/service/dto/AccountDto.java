package flcxilove.user.service.dto;

import flcxilove.user.dao.po.Account;
import flcxilove.user.dao.po.condition.AccountCondition;
import org.jetbrains.annotations.NotNull;

/**
 * 用户账号信息DTO
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-01 15:59
 */
public class AccountDto {

  /**
   * 物理PK
   */
  private String id;

  /**
   * 账号编号(登录账号)
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
   * 根据账户持久对象构建DTO
   *
   * @param account 账户持久对象
   *
   * @return 账户DTO
   */
  public static AccountDto build(@NotNull Account account) {

    // DTO对象构建
    AccountDto accountDto = new AccountDto();

    accountDto.setId(account.getId());
    accountDto.setLoginId(account.getLoginId());
    accountDto.setSource(account.getSource());
    accountDto.setPassword(account.getPassword());
    accountDto.setEmail(account.getEmail());
    accountDto.setMobile(account.getMobile());
    accountDto.setExternalId(account.getExternalId());
    accountDto.setCreatIp(account.getCreatIp());
    accountDto.setLastLoginIp(account.getLastLoginIp());
    accountDto.setLoginTimes(account.getLoginTimes());
    // 密码不在构建范围内

    return accountDto;
  }

  /**
   * 构建持久层实体类对象
   *
   * @return 用户账号持久层对象
   */
  public Account convertToEntity() {

    // PO对象构建
    Account accountPo = new Account();

    accountPo.setLoginId(this.getLoginId().trim());
    accountPo.setSource(this.getSource());
    accountPo.setPassword(this.getPassword());
    accountPo.setEmail(this.getEmail());
    accountPo.setMobile(this.getMobile());
    accountPo.setExternalId(this.getExternalId());
    accountPo.setCreatIp(this.getCreatIp());
    accountPo.setLastLoginIp(this.getLastLoginIp());
    accountPo.setLoginTimes(this.getLoginTimes());

    return accountPo;
  }

  /**
   * 构建持久层操作条件对象
   *
   * @return 用户账号持久层操作条件对象
   */
  public AccountCondition convertToCondition() {

    // 条件对象构建
    AccountCondition condition = new AccountCondition();

    condition.setId(this.getId());
    condition.setLoginId(this.getLoginId().trim());
    condition.setSource(this.getSource());
    condition.setEmail(this.getEmail());
    condition.setMobile(this.getMobile());
    condition.setExternalId(this.getExternalId());
    condition.setCreatIp(this.getCreatIp());
    condition.setLastLoginIp(this.getLastLoginIp());
    condition.setLoginTimes(this.getLoginTimes());

    return condition;
  }

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
}
