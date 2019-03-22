package flcxilove.governance.shiro.dao.mapper;

import flcxilove.governance.shiro.dao.entity.ShiroFilterChain;
import java.util.List;

/**
 * Shiro过滤规则数据接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-19 15:55
 */
public interface ShiroFilterChainMapper {

  /**
   * 查询Shiro过滤规则信息
   *
   * @return Shiro过滤规则信息
   */
  List<ShiroFilterChain> selectShiroFilterChain();
}
