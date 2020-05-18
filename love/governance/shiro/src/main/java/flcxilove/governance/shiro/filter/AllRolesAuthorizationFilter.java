package flcxilove.governance.shiro.filter;

import java.util.Set;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.subject.Subject;
import org.springframework.util.CollectionUtils;

/**
 * 角色过滤器(全部满足)
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-30 13:04
 */
public class AllRolesAuthorizationFilter extends RolesAuthorizationFilter {

  /**
   * 过滤器别名
   */
  public final static String FILTER_ALIAS = "allroles";

  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

    Subject subject = super.getSubject(request, response);

    // 角色过滤条件信息获取
    Set<String> roles = super.resolveRoles(mappedValue);

    if (CollectionUtils.isEmpty(roles)) {
      return Boolean.TRUE;
    }

    return subject.hasAllRoles(roles);
  }
}
