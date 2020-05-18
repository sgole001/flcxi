package flcxilove.governance.shiro.filter;

import flcxilove.governance.shiro.resolver.RestPathMatchingFilterChainResolver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.BeanInitializationException;

/**
 * 自定义Shiro过滤服务配置接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-28 14:04
 */
public class FlcxiShiroFilterFactoryBean extends ShiroFilterFactoryBean {

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(FlcxiShiroFilterFactoryBean.class.getName());

  /**
   * 默认构造器
   */
  public FlcxiShiroFilterFactoryBean() {
    super();
  }

  @Override
  protected AbstractShiroFilter createInstance() {

    // 获取SecurityManager
    SecurityManager securityManager = super.getSecurityManager();

    if (securityManager == null) {
      throw new BeanInitializationException("SecurityManager property must be set.");
    }

    if (!(securityManager instanceof WebSecurityManager)) {
      throw new BeanInitializationException("The security manager does not implement the WebSecurityManager interface.");
    }

    // 创建过滤链管理实例
    FilterChainManager manager = super.createFilterChainManager();
    // 设定过滤链解析接口（自定义实现REST拦截）
    RestPathMatchingFilterChainResolver chainResolver = new RestPathMatchingFilterChainResolver();
    chainResolver.setFilterChainManager(manager);

    return new FlcxiShiroFilterFactoryBean.SpringShiroFilter((WebSecurityManager) securityManager, chainResolver);
  }

  /**
   * 沿用ShiroFilterFactoryBean#SpringShiroFilter
   */
  private static final class SpringShiroFilter extends AbstractShiroFilter {

    protected SpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {
      super();
      if (webSecurityManager == null) {
        throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
      }
      super.setSecurityManager(webSecurityManager);
      if (resolver != null) {
        super.setFilterChainResolver(resolver);
      }
    }
  }
}
