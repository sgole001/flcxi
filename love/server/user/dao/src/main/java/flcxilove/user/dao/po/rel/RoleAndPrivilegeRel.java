package flcxilove.user.dao.po.rel;

import flcxilove.governance.mybatis.bean.entity.CommonEntity;

/**
 * 角色和权限关联关系持久层映射模型
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-30 17:04
 */
public class RoleAndPrivilegeRel extends CommonEntity {

  /**
   * 角色ID
   */
  private String rid;

  /**
   * 权限ID
   */
  private String pid;

  public String getRid() {
    return rid;
  }

  public void setRid(String rid) {
    this.rid = rid;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }
}
