package flcxilove.user.service.user.impl;

import flcxilove.common.data.DirectedGraph;
import flcxilove.common.exception.BusinessException;
import flcxilove.user.config.message.MessageConstant;
import flcxilove.user.dao.bo.RoleBo;
import flcxilove.user.dao.mapper.UserMapper;
import flcxilove.user.dao.po.Role;
import flcxilove.user.service.dto.RoleDto;
import flcxilove.user.service.user.RoleService;
import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 角色处理服务实现类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-05-16 15:55
 */
@Service(value = "roleService")
public class DefaultRoleService implements RoleService {

  @Resource
  private UserMapper userMapper;

  @Cacheable(value = "RoleCache", keyGenerator = "roleDirectedGraphKeyGenerator")
  @Override
  public DirectedGraph<RoleDto> loadRoleGraph() {

    // 获取全部角色数据信息
    List<RoleBo> roles = userMapper.selectRoles(null);

    // 不存在角色信息，异常处理
    if (CollectionUtils.isEmpty(roles)) {
      throw new BusinessException(MessageConstant.MSG_BIZ_00009);
    }

    // 创建角色信息图
    DirectedGraph<RoleDto> roleGraph = new DirectedGraph<>();

    // 遍历角色信息
    for (RoleBo role : roles) {

      // 构建角色顶点信息
      RoleDto roleDto = RoleDto.build(role.getRole());
      // 角色信息图中添加角色顶点信息
      roleGraph.addNode(roleDto);

      // 获取当前角色的上层角色信息
      List<Role> proles = role.getProle();

      // 如果存在上层角色，遍历构建有向边（出度）信息
      if (!CollectionUtils.isEmpty(proles)) {

        for (Role prole : proles) {

          // 构建上层角色顶点信息
          RoleDto proleDto = RoleDto.build(prole);
          // 角色信息图中添加上层角色顶点信息（如果图中已经存在此顶点，则不再次添加）
          roleGraph.addNode(proleDto);

          // 角色信息图中添加有向边
          roleGraph.addEdge(roleDto, proleDto);
        }

      }
    }

    return roleGraph;
  }
}
