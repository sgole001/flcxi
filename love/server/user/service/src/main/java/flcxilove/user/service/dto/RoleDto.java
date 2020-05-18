package flcxilove.user.service.dto;

import flcxilove.user.dao.po.Role;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * 角色DTO
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-01 17:49
 */
public class RoleDto implements Serializable {

  private static final long serialVersionUID = -7724433771544502803L;
  /**
   * 角色编码
   */
  private String code;

  /**
   * 角色名
   */
  private String name;

  /**
   * 权限信息
   */
  private List<PrivilegeDto> privileges;

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

  public List<PrivilegeDto> getPrivileges() {
    return privileges;
  }

  public void setPrivileges(List<PrivilegeDto> privileges) {
    this.privileges = privileges;
  }

  /**
   * 账户角色持久对象构建DTO
   *
   * @param role 账户角色持久对象
   *
   * @return 账户角色DTO
   */
  public static RoleDto build(@NotNull Role role) {

    // DTO对象构建
    RoleDto roleDto = new RoleDto();

    roleDto.setCode(role.getCode());
    roleDto.setName(role.getName());
    // 权限信息通过HATEOAS获取

    return roleDto;
  }

  public static RoleDto build(Map role) {

    // DTO对象构建
    RoleDto roleDto = new RoleDto();

    roleDto.setCode((String) role.get("code"));
    roleDto.setName((String) role.get("name"));

    return roleDto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RoleDto)) {
      return false;
    }
    RoleDto roleDto = (RoleDto) o;
    return getCode().equals(roleDto.getCode());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCode());
  }
}
