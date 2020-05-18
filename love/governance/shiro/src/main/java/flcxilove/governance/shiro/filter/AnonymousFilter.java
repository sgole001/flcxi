package flcxilove.governance.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 匿名过滤器
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-30 12:53
 */
public class AnonymousFilter extends RestControlFilter {

  /**
   * 过滤器别名
   */
  public final static String FILTER_ALIAS = "anon";

  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    return Boolean.TRUE;
  }

  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) {
    return Boolean.TRUE;
  }
}
