package flcxilove.governance.mybatis.configuration;

import flcxilove.common.tools.PropertyUtil;
import flcxilove.governance.druid.configuration.DataSourceConfig;
import flcxilove.governance.mybatis.bean.FlcxiBatisConfiguration;
import flcxilove.governance.mybatis.bean.FlcxiBatisConfiguration.Constant;
import flcxilove.governance.mybatis.plugin.CommonDbFieldInterceptor;
import flcxilove.governance.mybatis.plugin.OptimisticLockerInterceptor;
import flcxilove.governance.mybatis.plugin.factory.ComFieldStrategyFactory;
import flcxilove.governance.mybatis.plugin.factory.impl.DefaultComFieldStrategyFactory;
import flcxilove.governance.mybatis.plugin.strategy.impl.ComFieldContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

/**
 * MyBatis配置
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-22 17:59
 */
@Configuration
@DependsOn("flcProperties")
public class MyBatiseConfig {

  @Resource(name = "dataSource")
  private DataSource dataSource;

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(DataSourceConfig.class.getName());

  /**
   * 定制化插件设定
   *
   * @return 设定插件列表
   */
  @Bean
  public List<Interceptor> customizePlugins(Interceptor commonDbFieldInterceptor, Interceptor optimisticLockerInterceptor) {

    // 插件拦截链采用了责任链模式，执行顺序和加入连接链的顺序有关
    List<Interceptor> interceptors = new ArrayList<>();

    interceptors.add(commonDbFieldInterceptor);
    interceptors.add(optimisticLockerInterceptor);

    return interceptors;
  }

  /**
   * 数据共通字段处理策略工厂设定
   *
   * @return 数据共通字段处理策略工厂
   */
  @Bean
  public ComFieldStrategyFactory comFieldStrategyFactory() {
    return new DefaultComFieldStrategyFactory();
  }

  /**
   * 设置数据共通字段处理插件
   *
   * @param comFieldStrategyFactory 策略工厂
   *
   * @return 数据共通字段处理插件
   */
  @Bean
  public CommonDbFieldInterceptor commonDbFieldInterceptor(ComFieldStrategyFactory comFieldStrategyFactory) {

    // 数据共通字段处理插件
    CommonDbFieldInterceptor interceptor = new CommonDbFieldInterceptor();
    // 处理策略上下文属性配置
    ComFieldContext context = new ComFieldContext();
    // 设置策略工厂
    context.setStrategyFactory(comFieldStrategyFactory);
    // 插件属性扩展
    Properties properties = new Properties();
    properties.put(CommonDbFieldInterceptor.COMMON_FIELD_STRATEGY_CONTEXT, context);
    interceptor.setProperties(properties);

    return interceptor;
  }

  /**
   * 设置乐观锁插件
   *
   * @return 乐观锁插件
   */
  @Bean
  public OptimisticLockerInterceptor optimisticLockerInterceptor() {

    // 乐观锁插件
    OptimisticLockerInterceptor interceptor = new OptimisticLockerInterceptor();

    return interceptor;
  }

  @Primary
  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactoryBean createSqlSessionFactory(List<Interceptor> customizePlugins) {

    // 创建SessionFactory
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

    try {
      // 配置数据源
      sqlSessionFactoryBean.setDataSource(this.dataSource);

      FlcxiBatisConfiguration configuration = PropertyUtil.binder.bind(Constant.PREFIX, FlcxiBatisConfiguration.class).get();

      // 加载MyBatis配置信息
      PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
      // Mapper资源文件
      sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources(configuration.getMapperLocations()));
      // Mapper类型别名包
      if (!StringUtils.isEmpty(configuration.getTypeAliasesPackage())) {
        sqlSessionFactoryBean.setTypeAliasesPackage(configuration.getTypeAliasesPackage());
      }
      // MyBatis行为属性
      sqlSessionFactoryBean.setConfiguration(configuration.getConfiguration());
      // 插件设定
      sqlSessionFactoryBean.setPlugins(customizePlugins.stream().toArray(Interceptor[]::new));

    } catch (Exception e) {
      logger.error(e);
    }

    return sqlSessionFactoryBean;
  }

  @Primary
  @Bean(name = "flcxiTransactionManager")
  public PlatformTransactionManager defaultTransactionManager() {
    return new DataSourceTransactionManager(this.dataSource);
  }
}
