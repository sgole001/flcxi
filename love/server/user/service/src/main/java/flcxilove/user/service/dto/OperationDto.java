package flcxilove.user.service.dto;

/**
 * 权限操作信息DTO
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-01 17:33
 */
public class OperationDto {

  /**
   * 操作编码
   */
  private String code;

  /**
   * 操作名称
   */
  private String name;

  /**
   * 操作说明
   */
  private String description;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
