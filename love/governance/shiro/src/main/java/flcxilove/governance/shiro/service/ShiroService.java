package flcxilove.governance.shiro.service;

import flcxilove.governance.shiro.dao.entity.ShiroFilterChain;
import java.util.List;

/**
 * Shiro相关逻辑服务接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-19 16:00
 */
public interface ShiroService {

  /**
   * 查询Shiro过滤过滤规则配置信息
   *
   * @return Shiro过滤过滤规则配置信息
   */
  List<ShiroFilterChain> findShiroFilterChain();
}
