package flcxilove.governance.shiro.builder.impl;

import flcxilove.governance.shiro.builder.ShiroBuilder;
import flcxilove.governance.shiro.filter.JwtAuth2Filter;
import flcxilove.governance.shiro.realm.JwtRealm;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.Filter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.realm.Realm;

/**
 * 默认Shiro构建器
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-18 12:03
 */
public abstract class AbstractShiroBuilder implements ShiroBuilder {

  @Resource(name = "jwtRealm")
  private JwtRealm jwtRealm;

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(AbstractShiroBuilder.class.getName());

  @Override
  public List<Realm> buildRealms() {

    // 创建Realm列表
    List<Realm> realmList = new ArrayList<>();
    // 添加Realm(JWT)
    realmList.add(this.jwtRealm);

    return realmList;
  }

  /**
   * 构建Shiro过滤器
   *
   * 内置过滤器
   *
   * a.认证过滤器 : anon, authc, authcBasic, user
   * b.授权过滤器 : rest, port, perms, roles, ssl
   *
   * 1. rest
   * rest风格拦截器，自动根据请求方法构建权限字符串（GET=read, POST=create,PUT=update,DELETE=delete,HEAD=read,TRACE=read,OPTIONS=read, MKCOL=create
   * ex: /users=rest[user]
   *
   * 2. port
   * 端口拦截器
   * ex: /test=port[80]
   *
   * 3. perms
   * 权限授权拦截器，验证用户是否拥有所有权限
   * ex: /user/**=perms["user:create"]
   *
   * 4. roles
   * 角色授权拦截器，验证用户是否拥有所有角色
   * ex: /admin/**=roles[admin]
   *
   * 5. anon
   * 匿名拦截器，即不需要登录即可访问；一般用于静态资源过滤
   * ex: /static/**=anon
   *
   * 6. authc
   * 基于表单的拦截器
   * ex: /**=authc
   *
   * 7. authcBasic
   * Basic HTTP身份验证拦截器
   *
   * 8. ssl
   * SSL拦截器，只有请求协议是https才能通过；否则自动跳转会https端口（443）；其他和port拦截器一样；
   *
   * 9. user
   * 用户拦截器，用户已经身份验证/记住我登录的都可
   *
   * @return Shiro过滤器集合映射
   */
  @Override
  public Map<String, Filter> buildFilters() {

    // 过滤器集合映射
    Map<String, Filter> filters = new LinkedHashMap<>();
    // JWT过滤器
    filters.put(JwtAuth2Filter.FILTER_CODE, new JwtAuth2Filter());

    return filters;
  }

  /**
   * 构建Shiro过滤对象链
   *
   * @return Shiro过滤对象链集合映射
   */
  @Override
  public abstract Map<String, String> buildFilterChain();
}
