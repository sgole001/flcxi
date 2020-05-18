package flcxilove.user.dao.bo;

import flcxilove.user.dao.po.Role;
import java.util.List;

/**
 * 角色信息领域模型
 *
 * @author sgole
 * @version v1.0
 * @since 2019-05-14 16:08
 */
public class RoleBo {

  /**
   * 角色信息
   */
  private Role role;

  /**
   * 角色继承信息：上层角色列表
   */
  private List<Role> prole;

  /**
   * 权限信息
   */
  private List<PrivilegeBo> privileges;

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public List<Role> getProle() {
    return prole;
  }

  public void setProle(List<Role> prole) {
    this.prole = prole;
  }

  public List<PrivilegeBo> getPrivileges() {
    return privileges;
  }

  public void setPrivileges(List<PrivilegeBo> privileges) {
    this.privileges = privileges;
  }
}
