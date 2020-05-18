package flcxilove.user.config.shiro;

import flcxilove.governance.shiro.builder.impl.AbstractShiroBuilder;
import flcxilove.governance.shiro.dao.entity.ShiroFilterChain;
import flcxilove.governance.shiro.service.ShiroService;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 用户服务Shiro构建器
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-18 13:53
 */
@Component(value = "shiroBuilder")
public class UserShiroBuilder extends AbstractShiroBuilder {

  @Resource
  private ShiroService shiroService;

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(UserShiroBuilder.class.getName());

  @Override
  public Map<String, String> buildFilterChain() {

    // 过滤对象链集合映射
    Map<String, String> filterChain = new LinkedHashMap<>();

    // 查询Shiro过滤规则信息
    List<ShiroFilterChain> shiroFilterChains = shiroService.findShiroFilterChain();

    if (shiroFilterChains != null) {

      // 构建过滤对象链集合映射
      for (ShiroFilterChain shiroFilterChain : shiroFilterChains) {
        filterChain.putIfAbsent(shiroFilterChain.getTarget(), shiroFilterChain.getFilters());
      }
    }

    return filterChain;
  }
}
