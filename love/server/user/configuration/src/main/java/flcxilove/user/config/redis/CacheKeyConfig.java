package flcxilove.user.config.redis;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存键值生成配置
 *
 * @author sgole
 * @version v1.0
 * @since 2019-05-27 15:08
 */
@Configuration
public class CacheKeyConfig {

  @Bean
  public KeyGenerator roleDirectedGraphKeyGenerator() {
    return (target, method, params) -> "DirectedGraph";
  }
}
