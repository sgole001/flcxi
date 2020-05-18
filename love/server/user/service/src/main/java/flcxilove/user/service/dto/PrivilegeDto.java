package flcxilove.user.service.dto;

import flcxilove.user.config.constant.UserEnums;
import flcxilove.user.config.constant.UserEnums.PrivilegeType;
import java.util.List;

/**
 * 权限DTO
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-01 17:48
 */
public class PrivilegeDto {

  /**
   * 权限编号
   */
  private String code;

  /**
   * 权限类型 0: RestAPI，1: 页面元素，2: 中台操作功能项，3: 文件资源
   */
  private UserEnums.PrivilegeType type;

  /**
   * 操作信息
   */
  private List<OperationDto> operations;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public PrivilegeType getType() {
    return type;
  }

  public void setType(PrivilegeType type) {
    this.type = type;
  }

  public List<OperationDto> getOperations() {
    return operations;
  }

  public void setOperations(List<OperationDto> operations) {
    this.operations = operations;
  }
}
