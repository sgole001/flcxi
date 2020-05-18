package flcxilove.user.dao.po;

import flcxilove.governance.mybatis.bean.entity.CommonEntity;

/**
 * 角色持久层映射模型
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-26 18:24
 */
public class Role extends CommonEntity {

  /**
   * 模型对应持久层对象名
   */
  public static final String PERSISTENCE_MAPPER = "Role";

  /**
   * 角色编码
   */
  private String code;

  /**
   * 角色名
   */
  private String name;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
