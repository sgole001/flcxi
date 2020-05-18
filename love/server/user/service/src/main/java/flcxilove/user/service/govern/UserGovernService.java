package flcxilove.user.service.govern;

import flcxilove.user.config.constant.UserEnums;
import flcxilove.user.service.dto.OperationDto;
import flcxilove.user.service.dto.PrivilegeDto;
import flcxilove.user.service.dto.RoleDto;
import java.util.List;

/**
 * 用户治理服务接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-01 17:55
 */
public interface UserGovernService {

  /**
   * 账户角色分配
   *
   * @param codes 角色ID列表
   * @param accountId 用户账号ID
   *
   * @return 用户角色信息
   */
  List<RoleDto> distributeRolesToUser(final List<String> codes, final String accountId);

  /**
   * 角色权限分配
   *
   * @param privilegeIds 权限ID列表
   * @param roleId 角色ID
   *
   * @return 角色信息
   */
  RoleDto distributePrivilegesToRole(final List<String> privilegeIds, final String roleId);

  /**
   * 创建角色
   *
   * @param code 角色编号
   * @param name 角色名
   *
   * @return 角色信息
   */
  RoleDto createRole(final String code, final String name);

  /**
   * 创建权限
   *
   * @param code 权限编号
   * @param type 权限类型
   * @param operationIds 权限操作ID列表
   *
   * @return 权限信息
   */
  PrivilegeDto createPrivilege(final String code, final UserEnums.PrivilegeType type, final List<String> operationIds);

  /**
   * 创建权限操作
   *
   * @param code 操作编码
   * @param name 操作名
   * @param desc 操作描述
   *
   * @return 权限操作信息
   */
  OperationDto createOperation(final String code, final String name, final String desc);
}
