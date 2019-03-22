package flcxilove.governance.shiro.builder;

import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.realm.Realm;

/**
 * shiro构建器接口（Realm | Filter | FilterChain 构建）
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-18 12:02
 */
public interface ShiroBuilder {

  /**
   * 构建ShiroRealm
   *
   * @return ShiroRealm
   */
  List<Realm> buildRealms();

  /**
   * 构建Shiro过滤器
   *
   * @return Shiro过滤器
   */
  Map<String, Filter> buildFilters();

  /**
   * 构建Shiro过滤链
   *
   * @return Shiro过滤链
   */
  Map<String, String> buildFilterChain();
}
