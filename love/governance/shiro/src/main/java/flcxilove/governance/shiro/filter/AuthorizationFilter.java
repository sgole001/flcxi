package flcxilove.governance.shiro.filter;

import flcxilove.common.tools.MessageUtil;
import flcxilove.governance.shiro.constant.ShiroConstant;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.web.util.WebUtils;

/**
 * 授权过滤器抽象类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-30 14:02
 */
public abstract class AuthorizationFilter extends RestControlFilter {

  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

    // 对外错误信息
    String msgError = MessageUtil.accessor.getMessage(ShiroConstant.MSG_SHIRO_SYS_00000);

    // 错误信息返回
    WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, msgError);

    return Boolean.FALSE;
  }
}
