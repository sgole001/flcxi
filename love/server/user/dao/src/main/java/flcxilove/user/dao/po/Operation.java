package flcxilove.user.dao.po;

import flcxilove.governance.mybatis.bean.entity.CommonEntity;

/**
 * 权限操作持久层映射模型
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-26 18:28
 */
public class Operation extends CommonEntity {

  /**
   * 模型对应持久层对象名
   */
  public static final String PERSISTENCE_MAPPER = "Operation";

  /**
   * 操作编码
   */
  private String code;

  /**
   * 操作内容
   */
  private String operation;

  /**
   * 操作说明
   */
  private String description;

  /**
   * 操作逻辑有效标志位
   */
  private String enabled;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEnabled() {
    return enabled;
  }

  public void setEnabled(String enabled) {
    this.enabled = enabled;
  }
}
