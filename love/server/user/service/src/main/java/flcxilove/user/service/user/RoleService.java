package flcxilove.user.service.user;

import flcxilove.common.data.DirectedGraph;
import flcxilove.user.service.dto.RoleDto;
import java.util.Map;
import java.util.Set;

/**
 * 角色处理服务接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-30 16:54
 */
public interface RoleService {

  /**
   * 加载角色信息有向无循环图
   *
   * @return 角色信息有向无循环图
   */
  DirectedGraph<RoleDto> loadRoleGraph();
}
