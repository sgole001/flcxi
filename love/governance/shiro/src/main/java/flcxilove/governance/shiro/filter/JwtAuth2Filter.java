package flcxilove.governance.shiro.filter;

import flcxilove.common.tools.MessageUtil;
import flcxilove.governance.shiro.constant.ShiroConstant;
import flcxilove.governance.shiro.token.JwtAuthenticationToken;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;

/**
 * ShiroJwt认证过滤器
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class JwtAuth2Filter extends RestControlFilter {

  /**
   * 过滤器别名
   */
  public final static String FILTER_ALIAS = "jwt";

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(JwtAuth2Filter.class);

  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

    Subject subject = SecurityUtils.getSubject();

    if (null != subject && subject.isAuthenticated()) {
      return Boolean.TRUE;
    }

    return Boolean.FALSE;
  }

  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

    // 对外错误信息
    String msgError = MessageUtil.accessor.getMessage(ShiroConstant.MSG_SHIRO_SYS_00000);

    if (this.isJwtHttpRequest(request)) {

      // 构建认证Token对象
      JwtAuthenticationToken token = this.buildJwtAuthToken(request);

      try {
        // 当前用户对象登陆
        SecurityUtils.getSubject().login(token);

        return Boolean.TRUE;
      } catch (AuthenticationException e) {
        logger.error(msgError, e);
      }
    }

    // 错误信息返回
    WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, msgError);

    return Boolean.FALSE;
  }

  /**
   * 判定是否HTTP请求，并且头部存在JWT信息
   *
   * @param request ServletRequest
   *
   * @return 判定结果
   */
  private boolean isJwtHttpRequest(ServletRequest request) {

    // 判定是否HTTP请求
    if (request instanceof HttpServletRequest) {

      // 从header中获取JWT信息
      String jwt = WebUtils.toHttp(request).getHeader(ShiroConstant.JWT_AUTH_HTTP_HEAD);

      if (!StringUtils.isEmpty(jwt)) {
        return Boolean.TRUE;
      }
    }

    return Boolean.FALSE;
  }

  /**
   * 构建认证Token对象
   *
   * @param request ServletRequest
   *
   * @return 认证Token对象
   */
  @NotNull
  private JwtAuthenticationToken buildJwtAuthToken(ServletRequest request) {

    // 从header中获取JWT信息
    String jwt = WebUtils.toHttp(request).getHeader(ShiroConstant.JWT_AUTH_HTTP_HEAD);

    return new JwtAuthenticationToken(jwt);
  }
}
