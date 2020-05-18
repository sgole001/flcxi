package flcxilove.auth.config.token;

import flcxilove.common.beans.JwtPayload;
import flcxilove.common.beans.JwtPayload.Prefix;
import flcxilove.common.tools.PropertyUtil;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JWT配置类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-25 18:46
 */
@Configuration
public class JwtConfig {

  /**
   * 加载资源访问令牌负载配置
   *
   * @return 资源访问令牌负载
   */
  @Bean(name = "accessTokenPayload")
  public JwtPayload accessTokenPayload() {
    return PropertyUtil.binder.bind(Prefix.ACCESS_TOKEN, Bindable.of(JwtPayload.class)).get();
  }

  /**
   * 加载刷新资源访问令牌的令牌负载
   *
   * @return 刷新资源访问令牌的令牌
   */
  @Bean(name = "refreshTokenPayload")
  public JwtPayload refreshTokenPayload() {
    return PropertyUtil.binder.bind(Prefix.REFRESH_TOKEN, Bindable.of(JwtPayload.class)).get();
  }
}
