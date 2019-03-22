//package flcxilove.auth.config.shiro;
//
//import flcxilove.common.shiro.ShiroBuilder;
//import flcxilove.common.shiro.filter.JwtAuth2Filter;
//import flcxilove.common.shiro.realm.JwtRealm;
//import flcxilove.common.tools.JwtUtil;
//import flcxilove.common.tools.RedisUtil;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import javax.annotation.Resource;
//import javax.servlet.Filter;
//import org.apache.shiro.realm.Realm;
//import org.springframework.stereotype.Component;
//
//@Component(value = "shiroBuilder")
//public class AuthShiroBuilder implements ShiroBuilder {
//
//  @Resource
//  private JwtUtil jwtUtil;
//
//  @Resource
//  private RedisUtil redisUtil;
//
//  @Override
//  public Realm buildRealm() {
//
//    // JWT
//    JwtRealm jwtRealm = new JwtRealm();
//    jwtRealm.setRedisUtil(this.redisUtil);
//    jwtRealm.setJwtUtil(this.jwtUtil);
//
//    return jwtRealm;
//  }
//
//  @Override
//  public Map<String, Filter> buildFilters() {
//
//    Map<String, Filter> filters = new LinkedHashMap<>();
//    // JWT认证过滤器
//    JwtAuth2Filter jwtAuth2Filter = new JwtAuth2Filter();
//
//    filters.put("jwt", jwtAuth2Filter);
//
//    return filters;
//  }
//
//  @Override
//  public Map<String, String> buildFilterChain() {
//
//    Map<String, String> filterChain = new LinkedHashMap<>();
//
//    filterChain.putIfAbsent("/auth/token", "anon");
//
//    return filterChain;
//  }
//}
