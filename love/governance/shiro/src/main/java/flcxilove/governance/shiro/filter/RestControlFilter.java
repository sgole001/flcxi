package flcxilove.governance.shiro.filter;

import flcxilove.common.tools.ServletUtil;
import flcxilove.governance.shiro.constant.ShiroConstant;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;

/**
 * Restful请求处理过滤器
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-29 14:26
 */
public abstract class RestControlFilter extends PathMatchingFilter {

  /**
   * 默认构造器
   */
  public RestControlFilter() {
  }

  /**
   * Subject对象获取
   *
   * @param request HTTP请求
   * @param response HTTP响应
   *
   * @return Subject对象
   */
  protected Subject getSubject(ServletRequest request, ServletResponse response) {
    return SecurityUtils.getSubject();
  }

  /**
   * 路径匹配处理，支持Restful请求
   *
   * @param path 匹配格式
   * @param request HTTP请求
   *
   * @return 匹配结果
   */
  @Override
  protected boolean pathsMatch(String path, ServletRequest request) {

    // 获取请求URI
    String requestURI = super.getPathWithinApplication(request);
    // 获取请求HttpMethod
    String requestMethod = ServletUtil.getRequestMethod(request);

    // 获取匹配URI
    String pathUri = path;
    // 获取匹配HTTP方法
    String pathHttpMethods = "";

    // 分割过滤信息
    String[] splitPath = path.split(ShiroConstant.REST_CHAIN_PATTERN_SEPARATOR);

    if (splitPath.length == 2) {
      pathUri = splitPath[0];
      pathHttpMethods = splitPath[1];
    }

    // 匹配URI处理
    boolean isMatched = super.pathsMatch(pathUri, requestURI);
    // 匹配HTTP方法
    return isMatched && (StringUtils.isBlank(pathHttpMethods) || pathHttpMethods.contains(requestMethod.toUpperCase()));
  }

  @Override
  public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    return this.isAccessAllowed(request, response, mappedValue) || this.onAccessDenied(request, response, mappedValue);
  }

  /**
   * 判定请求是否允许
   *
   * @param request HTTP请求
   * @param response HTTP响应
   * @param mappedValue 过滤器参数信息
   *
   * @return 判定条件
   *
   * @throws Exception
   */
  protected abstract boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception;

  /**
   * 请求拒绝后续处理
   *
   * @param request HTTP请求
   * @param response HTTP响应
   * @param mappedValue 过滤器参数信息
   *
   * @return 处理结果
   *
   * @throws Exception
   */
  protected abstract boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception;
}
