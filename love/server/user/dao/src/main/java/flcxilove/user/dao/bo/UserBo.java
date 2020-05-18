package flcxilove.user.dao.bo;

import flcxilove.user.dao.po.Account;
import flcxilove.user.dao.po.Profile;
import flcxilove.user.dao.po.Role;
import java.util.List;

/**
 * 用户信息领域模型
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-26 18:45
 */
public class UserBo {

  /**
   * 用户账号信息
   */
  private Account account;

  /**
   * 用户简况
   */
  private Profile profile;

  /**
   * 用户角色信息
   */
  private List<Role> roles;

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
}
