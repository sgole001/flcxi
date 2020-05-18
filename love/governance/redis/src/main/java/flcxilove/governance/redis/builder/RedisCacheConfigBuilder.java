package flcxilove.governance.redis.builder;

import java.util.Map;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

/**
 * 缓存配置构建接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-05-22 16:32
 */
public interface RedisCacheConfigBuilder {

  /**
   * 构建默认缓存配置
   *
   * @return 默认缓存配置
   */
  RedisCacheConfiguration buildDefaultCacheConfiguration();

  /**
   * 构建初始化缓存配置集合
   *
   * @return 特定缓存配置集合
   */
  Map<String, RedisCacheConfiguration> buildInitialCacheConfigurations();
}
