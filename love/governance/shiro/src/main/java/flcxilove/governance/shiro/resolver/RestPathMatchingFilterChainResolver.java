package flcxilove.governance.shiro.resolver;

import flcxilove.common.tools.ServletUtil;
import flcxilove.governance.shiro.constant.ShiroConstant;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.springframework.util.CollectionUtils;

/**
 * Restful请求路径过滤解析器
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-28 10:50
 */
public class RestPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(RestPathMatchingFilterChainResolver.class.getName());

  /**
   * 默认构造器
   */
  public RestPathMatchingFilterChainResolver() {
    super();
  }

  /**
   * 过滤配置构造器
   *
   * @param filterConfig 过滤配置信息
   */
  public RestPathMatchingFilterChainResolver(FilterConfig filterConfig) {
    super(filterConfig);
  }

  @Override
  public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {

    // 获取过滤链管理器
    FilterChainManager filterChainManager = super.getFilterChainManager();

    // 没有过滤链信息
    if (!filterChainManager.hasChains()) {
      return null;
    }

    // 获取请求URI
    String requestURI = super.getPathWithinApplication(request);
    // 获取请求HttpMethod
    String requestMethod = ServletUtil.getRequestMethod(request);
    // 获取过滤链信息
    Set<String> chainNames = filterChainManager.getChainNames();

    // 匹配Rest请求过滤信息
    String pathPattern = this.pathMatches(chainNames, requestURI, requestMethod);

    if (StringUtils.isBlank(pathPattern)) {
      return null;
    }

    return filterChainManager.proxy(originalChain, pathPattern);
  }

  /**
   * 匹配Rest请求过滤信息
   *
   * @param chainNames 过滤链名信息
   * @param uri HTTP请求URI
   * @param httpMethod HTTP请求方法
   *
   * @return Rest请求过滤信息
   */
  private String pathMatches(Set<String> chainNames, String uri, String httpMethod) {

    if (CollectionUtils.isEmpty(chainNames)) {
      return null;
    }

    for (String chainName : chainNames) {

      // 获取匹配URI
      String pathUri = chainName;

      // 获取匹配HTTP方法
      String pathHttpMethods = "";

      // 分割过滤信息
      String[] splitChainName = chainName.split(ShiroConstant.REST_CHAIN_PATTERN_SEPARATOR);

      if (splitChainName.length == 2) {
        pathUri = splitChainName[0];
        pathHttpMethods = splitChainName[1].toUpperCase();
      }

      // 匹配URI处理
      boolean isMatched = super.pathMatches(pathUri, uri);
      // 匹配HTTP方法
      isMatched = isMatched && (StringUtils.isBlank(pathHttpMethods) || pathHttpMethods.contains(httpMethod.toUpperCase()));

      if (isMatched) {
        return chainName;
      }
    }

    return null;
  }
}
