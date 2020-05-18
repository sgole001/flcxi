package flcxilove.user.service.cache;

import flcxilove.common.data.DirectedGraph;
import flcxilove.user.service.dto.RoleDto;

public interface RoleCache {

  /**
   * 获取角色有向图缓存
   *
   * @return 角色有向图信息
   */
  DirectedGraph fetchRoleGraph();

  /**
   * 缓存角色有向图
   *
   * @param directedGraph 有向图数据结构
   */
  void cacheRoleGraph(DirectedGraph<RoleDto> directedGraph);
}
