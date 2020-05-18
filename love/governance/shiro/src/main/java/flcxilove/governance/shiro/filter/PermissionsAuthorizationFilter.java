package flcxilove.governance.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.subject.Subject;

/**
 * 权限过滤器（全部满足）
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-30 13:45
 */
public class PermissionsAuthorizationFilter extends AuthorizationFilter {

  /**
   * 过滤器别名
   */
  public final static String FILTER_ALIAS = "perms";

  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

    Subject subject = super.getSubject(request, response);

    // 权限过滤条件信息获取
    String[] perms = (String[]) mappedValue;

    if (perms != null && perms.length > 0) {

      if (perms.length == 1) {
        return subject.isPermitted(perms[0]);
      }
      return subject.isPermittedAll(perms);
    }

    return Boolean.FALSE;
  }
}
