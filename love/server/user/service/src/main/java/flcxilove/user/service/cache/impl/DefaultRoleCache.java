package flcxilove.user.service.cache.impl;

import flcxilove.common.data.DirectedGraph;
import flcxilove.governance.redis.tools.RedisUtil;
import flcxilove.user.service.cache.RoleCache;
import flcxilove.user.service.dto.RoleDto;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 默认角色缓存服务实现类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-05-21 10:42
 */
@Service(value = "roleCache")
public class DefaultRoleCache implements RoleCache {

  @Resource
  private RedisUtil redisUtil;

  /**
   * 角色有向图缓存键值
   */
  public final static String ROLE_GRAPH_CACHE_KEY = "ROLE_GRAPH";

  @Override
  public DirectedGraph<RoleDto> fetchRoleGraph() {

    // 角色图信息缓存是否存在
    if (redisUtil.exists(ROLE_GRAPH_CACHE_KEY)) {

    }

    // 获取有向图信息
    Map<RoleDto, Set<RoleDto>> graph = redisUtil.getHashMap(ROLE_GRAPH_CACHE_KEY);

    return new DirectedGraph<RoleDto>(graph);
  }

  @Override
  public void cacheRoleGraph(DirectedGraph<RoleDto> directedGraph) {

    redisUtil.setHashMap(ROLE_GRAPH_CACHE_KEY, directedGraph.getGraph());
  }
}
