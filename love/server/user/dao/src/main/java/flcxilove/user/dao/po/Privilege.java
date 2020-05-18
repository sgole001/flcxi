package flcxilove.user.dao.po;

import flcxilove.governance.mybatis.bean.entity.CommonEntity;
import java.util.List;

/**
 * 权限持久层映射模型
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-26 18:25
 */
public class Privilege extends CommonEntity {

  /**
   * 模型对应持久层对象名
   */
  public static final String PERSISTENCE_MAPPER = "Privilege";

  /**
   * 需要权限控制的资源ID
   */
  private String resource;

  /**
   * 权限的具体功能或操作ID
   */
  private String operation;

  /**
   * 权限逻辑有效标志位
   */
  private String enabled;

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public String getEnabled() {
    return enabled;
  }

  public void setEnabled(String enabled) {
    this.enabled = enabled;
  }
}
