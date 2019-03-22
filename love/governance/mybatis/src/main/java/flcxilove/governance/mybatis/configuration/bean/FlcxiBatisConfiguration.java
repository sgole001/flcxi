package flcxilove.governance.mybatis.configuration.bean;

import org.apache.ibatis.session.Configuration;
import org.springframework.stereotype.Component;

@Component
public class FlcxiBatisConfiguration {

  /**
   * 内部常量
   */
  public interface Constant {

    /**
     * 配置属性Key前缀
     */
    String PREFIX = "flcxibatis";
  }

  /**
   * Mapper资源路径配置
   */
  private String mapperLocations;

  /**
   * 类型别名包
   */
  private String typeAliasesPackage;

  /**
   * MyBatis行为属性配置
   */
  private Configuration configuration;

  public String getMapperLocations() {
    return mapperLocations;
  }

  public void setMapperLocations(String mapperLocations) {
    this.mapperLocations = mapperLocations;
  }

  public String getTypeAliasesPackage() {
    return typeAliasesPackage;
  }

  public void setTypeAliasesPackage(String typeAliasesPackage) {
    this.typeAliasesPackage = typeAliasesPackage;
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }
}
