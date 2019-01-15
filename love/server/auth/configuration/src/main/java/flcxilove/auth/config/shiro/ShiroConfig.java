package flcxilove.auth.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;

import flcxilove.common.shiro.filter.JwtAuth2Filter;
import flcxilove.common.shiro.filter.JwtSubjectFactory;
import flcxilove.common.shiro.realm.JwtRealm;
import flcxilove.common.tools.JwtUtil;
import flcxilove.common.tools.RedisUtil;

@Configuration
public class ShiroConfig {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 配置shiro过滤器
     *
     * @param securityManager Shiro安全管理器
     * @return shiro过滤器
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        // 1.定义shiroFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 2.设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 3.拦截器配置
        shiroFilterFactoryBean.setFilters(this.buildFilters());
        // 4.拦截规则配置
        shiroFilterFactoryBean.setFilterChainDefinitionMap(this.buildFilterChain());

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
        securityManager.setRealm(this.buildRealm());

        SecurityUtils.setSecurityManager(securityManager);

        return securityManager;
    }

    /**
     * 构建ShiroRealm
     *
     * @return ShiroRealm
     */
    private Realm buildRealm() {

        // JWT
        JwtRealm jwtRealm = new JwtRealm();
        jwtRealm.setRedisUtil(this.redisUtil);
        jwtRealm.setJwtUtil(this.jwtUtil);

        return jwtRealm;
    }

    /**
     * 构建Shiro过滤器
     *
     * @return Shiro过滤器
     */
    private Map<String, Filter> buildFilters() {

        Map<String, Filter> filters = new LinkedHashMap<>();
        // JWT认证过滤器
        JwtAuth2Filter jwtAuth2Filter = new JwtAuth2Filter();

        filters.put("jwt", jwtAuth2Filter);

        return filters;
    }

    /**
     * 构建Shiro过滤链
     *
     * @return Shiro过滤链
     */
    private Map<String, String> buildFilterChain() {

        Map<String, String> filterChain = new LinkedHashMap<>();

        filterChain.putIfAbsent("/auth/token", "anon");

        return filterChain;
    }
}
