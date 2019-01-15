package flcxilove.common.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import flcxilove.common.shiro.token.JwtAuthenticationToken;

/**
 * ShiroJwt认证过滤器
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class JwtAuth2Filter extends PathMatchingFilter {

    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        Subject subject = SecurityUtils.getSubject();

        // 从header中获取token
        String jwt = ((HttpServletRequest) request).getHeader("jwt");

        // 认证Token对象
        JwtAuthenticationToken token = new JwtAuthenticationToken(jwt);
        // 当前用户对象登陆
        SecurityUtils.getSubject().login(token);

        return Boolean.TRUE;
    }

    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        Subject subject = SecurityUtils.getSubject();

        // 未认证的情况
        if (null == subject || !subject.isAuthenticated()) {
            // 告知客户端JWT认证失败需跳转到登录页面
            System.out.print("告知客户端JWT认证失败需跳转到登录页面");
        } else {
            //  已经认证但未授权的情况
            // 告知客户端JWT没有权限访问此资源
            System.out.print("告知客户端JWT没有权限访问此资源");
        }
        // 过滤链终止
        return false;
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return this.onAccessDenied(request, response);
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return this.isAccessAllowed(request, response, mappedValue) || this.onAccessDenied(request, response, mappedValue);
    }
}
