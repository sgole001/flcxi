package flcxilove.governance.shiro.configuration;

import flcxilove.governance.shiro.builder.ShiroBuilder;
import flcxilove.governance.shiro.filter.JwtSubjectFactory;
import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Shiro配置
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-18 12:04
 */
@Configuration
@DependsOn("message")
public class ShiroConfig {

  @Resource
  private ShiroBuilder shiroBuilder;

  /**
   * 配置shiro过滤器
   *
   * @param securityManager Shiro安全管理器
   *
   * @return shiro过滤器
   */
  @Bean
  public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

    // 1.定义shiroFactoryBean
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    // 2.设置securityManager
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    // 3.拦截器配置
    shiroFilterFactoryBean.setFilters(shiroBuilder.buildFilters());
    // 4.拦截规则配置
    shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroBuilder.buildFilterChain());

    return shiroFilterFactoryBean;
  }

  /**
   * 配置shiro安全管理器
   *
   * @return shiro安全管理器
   */
  @Bean
  public SecurityManager securityManager() {

    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

    DefaultSubjectDAO subjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
    DefaultSessionStorageEvaluator evaluator = (DefaultSessionStorageEvaluator) subjectDAO.getSessionStorageEvaluator();
    securityManager.setSubjectFactory(new JwtSubjectFactory(evaluator));
    securityManager.setRealms(shiroBuilder.buildRealms());

    SecurityUtils.setSecurityManager(securityManager);

    return securityManager;
  }
}
