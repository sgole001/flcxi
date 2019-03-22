package flcxilove.governance.shiro.filter;

import flcxilove.governance.shiro.token.JwtAuthenticationToken;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;

/**
 * ShiroJwt认证过滤器
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class JwtAuth2Filter extends PathMatchingFilter {

  /**
   * 过滤器编号
   */
  public static final String FILTER_CODE = "jwt";

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(JwtAuth2Filter.class.getName());

  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

    try {

      // 从header中获取token
      String jwt = ((HttpServletRequest) request).getHeader("jwt");

      // 认证Token对象
      JwtAuthenticationToken token = new JwtAuthenticationToken(jwt);
      // 当前用户对象登陆
      SecurityUtils.getSubject().login(token);

      return Boolean.TRUE;

    } catch (Exception e) {
      logger.error(e.getCause().getMessage());
    }

    return Boolean.FALSE;
  }

  protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

    Subject subject = SecurityUtils.getSubject();

    // 未认证的情况
    if (null == subject || !subject.isAuthenticated()) {
      // 告知客户端JWT认证失败需跳转到登录页面
      logger.info("告知客户端JWT认证失败需跳转到登录页面");
    }
    // 已经认证但未授权的情况
    else {
      // 告知客户端JWT没有权限访问此资源
      logger.info("告知客户端JWT没有权限访问此资源");
    }

    // 过滤链终止
    return Boolean.FALSE;
  }

  protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    return this.onAccessDenied(request, response);
  }

  @Override
  public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    return this.isAccessAllowed(request, response, mappedValue) || this.onAccessDenied(request, response, mappedValue);
  }
}
