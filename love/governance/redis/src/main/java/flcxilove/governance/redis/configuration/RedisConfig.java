package flcxilove.governance.redis.configuration;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis配置信息
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-21 13:55
 */
@Configuration
public class RedisConfig {

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private Integer port;

  @Value("${spring.redis.database}")
  private Integer database;

  @Value("${spring.redis.password}")
  private String password;

  @Value("${spring.redis.timeout:3000}")
  private Long timeout;

  @Value("${spring.redis.maxIdle:300}")
  private Integer maxIdle;

  @Value("${spring.redis.maxTotal:1000}")
  private Integer maxTotal;

  @Value("${spring.redis.maxWaitMillis:1000}")
  private Integer maxWaitMillis;

  @Value("${spring.redis.minEvictableIdleTimeMillis:300000}")
  private Integer minEvictableIdleTimeMillis;

  @Value("${spring.redis.numTestsPerEvictionRun:1024}")
  private Integer numTestsPerEvictionRun;

  @Value("${spring.redis.timeBetweenEvictionRunsMillis:30000}")
  private Long timeBetweenEvictionRunsMillis;

  @Value("${spring.redis.testOnBorrow:true}")
  private Boolean testOnBorrow;

  @Value("${spring.redis.testWhileIdle:true}")
  private Boolean testWhileIdle;

  @Bean
  public JedisPoolConfig jedisPoolConfig() {

    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    // 最大空闲数
    jedisPoolConfig.setMaxIdle(this.maxIdle);
    // 连接池的最大数据库连接数
    jedisPoolConfig.setMaxTotal(this.maxTotal);
    // 最大建立连接等待时间
    jedisPoolConfig.setMaxWaitMillis(this.maxWaitMillis);
    // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
    jedisPoolConfig.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
    // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
    jedisPoolConfig.setNumTestsPerEvictionRun(this.numTestsPerEvictionRun);
    // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
    jedisPoolConfig.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
    // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
    jedisPoolConfig.setTestOnBorrow(this.testOnBorrow);
    // 在空闲时检查有效性, 默认false
    jedisPoolConfig.setTestWhileIdle(this.testWhileIdle);

    return jedisPoolConfig;
  }

  @Bean
  public RedisStandaloneConfiguration redisStandaloneConfiguration() {

    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();

    // Host名
    redisStandaloneConfiguration.setHostName(this.host);
    // 端口号
    redisStandaloneConfiguration.setPort(this.port);
    // 数据库索引
    redisStandaloneConfiguration.setDatabase(this.database);
    // 数据库密码
    redisStandaloneConfiguration.setPassword(RedisPassword.of(this.password));

    return redisStandaloneConfiguration;
  }

  @Bean
  public RedisConnectionFactory redisConnectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration, JedisPoolConfig jedisPoolConfig) {

    JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().poolConfig(jedisPoolConfig).and()
        .readTimeout(Duration.ofMillis(this.timeout)).build();

    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);

    return jedisConnectionFactory;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

    // 使用jackson序列化替代jdk序列化
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);
    template.setKeySerializer(jackson2JsonRedisSerializer);
    template.setValueSerializer(jackson2JsonRedisSerializer);
    template.setHashKeySerializer(jackson2JsonRedisSerializer);
    template.setHashValueSerializer(jackson2JsonRedisSerializer);
    template.afterPropertiesSet();

    return template;
  }
}
