package flcxilove.user.dao.bo;

import flcxilove.user.dao.po.Operation;
import flcxilove.user.dao.po.Resource;

/**
 * 权限信息领域模型
 *
 * @author sgole
 * @version v1.0
 * @since 2019-05-14 15:35
 */
public class PrivilegeBo<T extends Resource> {

  /**
   * 权限对应资源
   */
  private T resource;

  /**
   * 权限操作信息
   */
  private Operation operation;

  public T getResource() {
    return resource;
  }

  public void setResource(T resource) {
    this.resource = resource;
  }

  public Operation getOperation() {
    return operation;
  }

  public void setOperation(Operation operation) {
    this.operation = operation;
  }
}
