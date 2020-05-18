package flcxilove.user.dao.po.rel;

import flcxilove.governance.mybatis.bean.entity.CommonEntity;

/**
 * 用户组和角色关联信息持久层映射模型
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-09 10:06
 */
public class AccountAndRoleRel extends CommonEntity {

  /**
   * 模型对应持久层对象名
   */
  public static final String PERSISTENCE_MAPPER = "AccountAndRoleRel";

  /**
   * 账户ID
   */
  private String aid;

  /**
   * 角色ID
   */
  private String rid;

  public String getAid() {
    return aid;
  }

  public void setAid(String aid) {
    this.aid = aid;
  }

  public String getRid() {
    return rid;
  }

  public void setRid(String rid) {
    this.rid = rid;
  }
}
