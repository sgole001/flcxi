package flcxilove.governance.mybatis.configuration;

import flcxilove.common.tools.PropertyUtil;
import flcxilove.governance.druid.configuration.DataSourceConfig;
import flcxilove.governance.mybatis.configuration.bean.FlcxiBatisConfiguration;
import flcxilove.governance.mybatis.configuration.bean.FlcxiBatisConfiguration.Constant;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.StringUtils;

/**
 * MyBatis配置
 *
 * @author sgole
 * @since 2019-03-22 17:59
 * @version v1.0
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

  @Primary
  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactoryBean createSqlSessionFactory() {

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

    } catch (Exception e) {
      logger.error(e);
    }

    return sqlSessionFactoryBean;
  }
}
