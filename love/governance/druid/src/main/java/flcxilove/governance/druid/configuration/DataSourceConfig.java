package flcxilove.governance.druid.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import flcxilove.common.tools.PropertyUtil;
import flcxilove.governance.druid.configuration.bean.StatViewServletConfBean;
import flcxilove.governance.druid.configuration.bean.WebStatFileterConfBean;
import java.sql.SQLException;
import java.util.Arrays;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

/**
 * 数据源配置
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-22 15:47
 */
@Configuration
@DependsOn("flcProperties")
public class DataSourceConfig {

  /**
   * 数据库连接驱动类
   * 特别注意：java 9以后需要将com.mysql.jdbc.Driver  改为  com.mysql.cj.jdbc.Driver即可
   */
  @Value("${druid.driver-class-name}")
  private String driveClassName;

  /**
   * 数据库连接字符串
   */
  @Value("${druid.url}")
  private String url;

  /**
   * 数据库连接用户名
   */
  @Value("${druid.username}")
  private String username;

  /**
   * 数据库连接密码
   */
  @Value("${druid.password}")
  private String password;

  /**
   * 初始化时建立物理连接的个数
   */
  @Value("${druid.initial-size:5}")
  private Integer initialSize;

  /**
   * 最小连接池数量
   */
  @Value("${druid.min-idle:5}")
  private Integer minIdle;

  /**
   * 最大连接池数量 maxIdle已经不再使用
   */
  @Value("${druid.max-active:20}")
  private Integer maxActive;

  /**
   * 获取连接时最大等待时间，单位毫秒
   */
  @Value("${druid.max-wait:60000}")
  private Long maxWait;

  /**
   * 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
   */
  @Value("${druid.test-while-idle:true}")
  private Boolean testWhileIdle;

  /**
   * 既作为检测的间隔时间又作为testWhileIdel执行的依据
   */
  @Value("${druid.time-between-eviction-runs-millis:60000}")
  private Long timeBetweenEvictionRunsMillis;

  /**
   * 销毁线程时检测当前连接的最后活动时间和当前时间差大于该值时，关闭当前连接
   */
  @Value("${druid.min-evictable-idle-time-millis:30000}")
  private Long minEvictableIdleTimeMillis;

  /**
   * 用来检测连接是否有效的sql 必须是一个查询语句
   * mysql中为 select 'x'
   * oracle中为 select 1 from dual
   */
  @Value("${druid.validation-query:select 'x'}")
  private String validationQuery;

  /**
   * 申请连接时会执行validationQuery检测连接是否有效,开启会降低性能,默认为true
   */
  @Value("${druid.test-on-borrow:false}")
  private Boolean testOnBorrow;

  /**
   * 归还连接时会执行validationQuery检测连接是否有效,开启会降低性能,默认为true
   */
  @Value("${druid.test-on-return:false}")
  private Boolean testOnReturn;

  /**
   * 当值大于0时poolPreparedStatements会自动修改为true
   */
  @Value("${druid.max-pool-prepared-statement-per-connection-size:20}")
  private Integer maxPoolPreparedStatementPerConnectionSize;

  /**
   * 配置扩展插件
   */
  @Value("${druid.filters:stat,wall,log4j2}")
  private String filters;

  /**
   * 通过connectProperties属性来打开mergeSql功能；慢SQL记录
   */
  @Value("${druid.connection-properties:druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500}")
  private String connectionProperties;

  /**
   * 合并多个DruidDataSource的监控数据
   */
  @Value("${druid.use-global-data-source-stat:true}")
  private Boolean useGlobalDataSourceStat;

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(DataSourceConfig.class.getName());

  /**
   * Druid数据源配置
   *
   * @return 数据源配置
   */
  @Primary
  @Bean(name = "dataSource")
  public DataSource druidDataSource() {

    // 构建Druid数据源
    DruidDataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();

    // JDBC配置
    dataSource.setDriverClassName(this.driveClassName);
    dataSource.setUrl(this.url);
    dataSource.setUsername(this.username);
    dataSource.setPassword(this.password);

    // Druid属性配置
    dataSource.setInitialSize(this.initialSize);
    dataSource.setMinIdle(this.minIdle);
    dataSource.setMaxActive(this.maxActive);
    dataSource.setMaxWait(this.maxWait);
    dataSource.setTestWhileIdle(this.testWhileIdle);
    dataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
    dataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
    dataSource.setValidationQuery(this.validationQuery);
    dataSource.setTestOnBorrow(this.testOnBorrow);
    dataSource.setTestOnReturn(this.testOnReturn);
    // 当数据库抛出不可恢复的异常时,抛弃该连接
//    dataSource.setExceptionSorter();
    // 是否缓存preparedStatement,mysql5.5+建议开启
//    dataSource.setPoolPreparedStatements(true);
    dataSource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);
    try {
      dataSource.setFilters(this.filters);
    } catch (SQLException e) {
      // ...
      logger.error(e);
    }
    dataSource.setConnectProperties(PropertyUtil.buildPropertiesFromStr(Arrays.asList(this.connectionProperties.split(";"))));
    dataSource.setUseGlobalDataSourceStat(this.useGlobalDataSourceStat);

    return dataSource;
  }

  /**
   * Druid监控服务配置
   *
   * @return 监控服务配置
   */
  @Bean
  @ConditionalOnProperty(name = "druid.stat-view-servlet.enabled", havingValue = "true")
  public ServletRegistrationBean druidServlet() {

    // Druid监控服务Servlet注册
    ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet());

    // 获取属性配置信息
    StatViewServletConfBean confBean = PropertyUtil.binder.bind(StatViewServletConfBean.Constant.PREFIX, StatViewServletConfBean.class).get();

    // 服务URL
    registrationBean.addUrlMappings(new String[]{confBean.getUrlPattern()});
    // 服务登录用户名
    if (!StringUtils.isEmpty(confBean.getLoginUsername())) {
      registrationBean.addInitParameter(StatViewServletConfBean.Constant.Key._LOGIN_USER_NAME, confBean.getLoginUsername());
    }
    // 服务登录密码
    if (!StringUtils.isEmpty(confBean.getLoginPassword())) {
      registrationBean.addInitParameter(StatViewServletConfBean.Constant.Key._LOGIN_PASSWORD, confBean.getLoginPassword());
    }
    // 服务访问控制：允许
    registrationBean.addInitParameter(StatViewServletConfBean.Constant.Key._ALLOW, confBean.getAllow());
    // 服务访问控制：拒绝
    if (!StringUtils.isEmpty(confBean.getDeny())) {
      registrationBean.addInitParameter(StatViewServletConfBean.Constant.Key._DENY, confBean.getDeny());
    }
    // 允许清空统计数据标记位
    if (confBean.getResetEnable() != null) {
      registrationBean.addInitParameter(StatViewServletConfBean.Constant.Key._REST_ENABLE, confBean.getResetEnable().toString());
    }

    return registrationBean;
  }

  @Bean
  @ConditionalOnProperty(name = "druid.web-stat-filter.enabled", havingValue = "true")
  public FilterRegistrationBean druidWebStatFilter() {

    // Druid Web过滤规则注册
    FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());

    // 获取属性配置信息
    WebStatFileterConfBean confBean = PropertyUtil.binder.bind(WebStatFileterConfBean.Constant.PREFIX, WebStatFileterConfBean.class).get();

    // URL过滤规则
    registrationBean.addUrlPatterns(confBean.getUrlPattern());
    // URL排除规则
    registrationBean.addInitParameter(WebStatFileterConfBean.Constant.Key._EXCLUSIONS, confBean.getExclusions());
    // 用户信息保存至Cookie中的名字
    if (!StringUtils.isEmpty(confBean.getPrincipalCookieName())) {
      registrationBean.addInitParameter(WebStatFileterConfBean.Constant.Key._PRINCIPAL_COOKIE_NAME, confBean.getPrincipalCookieName());
    }
    // 用户信息保存至Session中的名字
    if (!StringUtils.isEmpty(confBean.getPrincipalSessionName())) {
      registrationBean.addInitParameter(WebStatFileterConfBean.Constant.Key._PRINCIPAL_SESSION_NAME, confBean.getPrincipalSessionName());
    }
    // 监控单个url调用的sql列表
    if (confBean.getProfileEnable() != null) {
      registrationBean.addInitParameter(WebStatFileterConfBean.Constant.Key._PROFILE_ENABLE, confBean.getProfileEnable().toString());
    }
    // session统计功能
    if (confBean.getSessionStatEnable() != null) {
      registrationBean.addInitParameter(WebStatFileterConfBean.Constant.Key._SESSION_STAT_ENABLE, confBean.getSessionStatEnable().toString());
    }
    // session统计上限
    if (confBean.getSessionStatMaxCount() != null) {
      registrationBean.addInitParameter(WebStatFileterConfBean.Constant.Key._SESSION_STAT_MAX_COUNT, confBean.getSessionStatMaxCount().toString());
    }

    return registrationBean;
  }
}
