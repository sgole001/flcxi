package flcxilove.common.shiro;

import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.realm.Realm;

public interface ShiroBuilder {

  /**
   * 构建ShiroRealm
   *
   * @return ShiroRealm
   */
  Realm buildRealm();

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
