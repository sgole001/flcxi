package flcxilove.user.config.redis;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import flcxilove.governance.redis.builder.CacheConfig;
import flcxilove.governance.redis.builder.impl.AbstractRedisCacheConfigBuilder;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.stereotype.Component;

/**
 * 用户服务缓存配置实现类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-05-23 10:58
 */
@Component(value = "cacheConfigBuilder")
public class UserRedisCacheConfigBuilder extends AbstractRedisCacheConfigBuilder {

  @Override
  public RedisCacheConfiguration buildDefaultCacheConfiguration() {

    // value序列化器
    SerializationPair<Object> valueSerializationPair = RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

    // 缓存配置信息
    CacheConfig cacheConfig = new CacheConfig();
    // 有效时间
    cacheConfig.setTtl(Duration.ofSeconds(30 * 60));
    // NULL值是否缓存标志位
    cacheConfig.setCacheNullValues(Boolean.FALSE);
    // value序列化器
    cacheConfig.setValueSerializationPair(valueSerializationPair);

    return super.buildRedisCacheConfiguration(cacheConfig);
  }

  @Override
  public Map<String, RedisCacheConfiguration> buildInitialCacheConfigurations() {

    FastJsonRedisSerializer jackson2JsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
    // value序列化器
    SerializationPair<Object> valueSerializationPair = RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer);

    Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>(2);
    // 当前用户信息缓存失效策略
    // 缓存配置信息
    CacheConfig cacheConfig4CurrentUser = new CacheConfig();
    // 有效时间
    cacheConfig4CurrentUser.setTtl(Duration.ofSeconds(24 * 60 * 60));
    // NULL值是否缓存标志位
    cacheConfig4CurrentUser.setCacheNullValues(Boolean.FALSE);
    // value序列化器
    cacheConfig4CurrentUser.setValueSerializationPair(valueSerializationPair);

    redisCacheConfigurationMap.put("UserCache", super.buildRedisCacheConfiguration(cacheConfig4CurrentUser));

    // 角色有向图信息缓存失效策略
    // 缓存配置信息
    CacheConfig cacheConfig4RoleGraph = new CacheConfig();
    // 有效时间
    cacheConfig4RoleGraph.setTtl(Duration.ofSeconds(24 * 60 * 60));
    // NULL值是否缓存标志位
    cacheConfig4RoleGraph.setCacheNullValues(Boolean.FALSE);
    // value序列化器（使用默认JDK序列化，JSON序列化成本高，需重写DefaultRedisCacheWriter部分方法，原生并不能实现Hash保存）
//    cacheConfig4RoleGraph.setValueSerializationPair(valueSerializationPair);

    redisCacheConfigurationMap.put("RoleCache", super.buildRedisCacheConfiguration(cacheConfig4RoleGraph));

    return redisCacheConfigurationMap;
  }
}
