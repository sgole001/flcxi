package flcxilove.user.dao.po.condition;

/**
 * 角色数据处理条件
 *
 * @author sgole
 * @version v1.0
 * @since 2019-05-16 16:05
 */
public class RoleCondition {

  /**
   * 角色ID
   */
  private String id;

  /**
   * 角色编码
   */
  private String code;

  /**
   * 角色名
   */
  private String name;

  /**
   * 角色继承：上层角色ID
   */
  private String pid;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }
}
