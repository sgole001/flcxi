package flcxilove.user.service.govern.impl;

import flcxilove.common.exception.BusinessException;
import flcxilove.user.config.constant.UserEnums.PrivilegeType;
import flcxilove.user.config.message.MessageConstant;
import flcxilove.user.dao.mapper.UserMapper;
import flcxilove.user.dao.po.Role;
import flcxilove.user.dao.po.rel.AccountAndRoleRel;
import flcxilove.user.service.dto.OperationDto;
import flcxilove.user.service.dto.PrivilegeDto;
import flcxilove.user.service.dto.RoleDto;
import flcxilove.user.service.govern.UserGovernService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 用户治理服务实现类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-01 18:42
 */
@Service(value = "governService")
public class DefaultUserGovernService implements UserGovernService {

  @Resource
  private UserMapper userMapper;

  @Override
  public List<RoleDto> distributeRolesToUser(List<String> codes, String accountId) {

    // 获取角色信息
    List<Role> roles = userMapper.selectRolesByCodes(codes);

    // 指定角色信息不存在
    if (CollectionUtils.isEmpty(roles)) {
      // 指定的账户组编号
      String input = codes.stream().distinct().collect(Collectors.joining(","));
      throw new BusinessException(MessageConstant.MSG_BIZ_00006, input);
    }

    // 指定分配角色信息部分缺失
    if (roles.size() != codes.stream().distinct().count()) {
      // 信息存在的账户组编号
      String output = roles.stream().map(Role::getCode).collect(Collectors.joining(","));
      // 指定的账户组编号
      String input = codes.stream().distinct().collect(Collectors.joining(","));
      throw new BusinessException(MessageConstant.MSG_BIZ_00007, input, output);
    }

    // 返回用账户组DTO列表
    List<RoleDto> roleDTOs = new ArrayList<>(roles.size());
    // 关联数据信息对象列表构建
    List<AccountAndRoleRel> accountAndRoleMappers = new ArrayList<>(roles.size());

    roles.forEach(role -> {

      // 关联数据对象
      AccountAndRoleRel rel = new AccountAndRoleRel();
      // 账号ID
      rel.setAid(accountId);
      // 角色ID
      rel.setRid(role.getId());

      accountAndRoleMappers.add(rel);
      // DTO构建
      roleDTOs.add(RoleDto.build(role));
    });

    // 批量插入关联数据
    userMapper.batchRoleToAccount(accountAndRoleMappers);

    return roleDTOs;
  }

  @Override
  public RoleDto distributePrivilegesToRole(List<String> privilegeIds, String roleId) {
    return null;
  }

  @Override
  public RoleDto createRole(String code, String name) {
    return null;
  }

  @Override
  public PrivilegeDto createPrivilege(String code, PrivilegeType type, List<String> operationIds) {
    return null;
  }

  @Override
  public OperationDto createOperation(String code, String name, String desc) {
    return null;
  }
}
