package flcxilove.common.tools;

import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 属性配置工具类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-21 15:49
 */
@Component(value = "flcProperties")
public class PropertyUtil {

  @Resource
  private Environment environment;

  /**
   * 属性绑定封装类
   */
  public static Binder binder;

  @PostConstruct
  private void init() {
    // 绑定当前环境变量
    binder = Binder.get(this.environment);
  }

  /**
   * 根据K,V字符串构建Properties对象
   *
   * @param props K,V字符串集合
   *
   * @return Properties对象
   */
  public static Properties buildPropertiesFromStr(final List<String> props) {

    if (CollectionUtils.isEmpty(props)) {
      return null;
    }

    // 属性对象
    Properties properties = new Properties();

    // 遍历属性信息
    for (String prop : props) {

      // 获取属性分割符索引
      int splitIndex = prop.indexOf("=");

      if (splitIndex > 0) {
        // 添加属性
        properties.put(prop.substring(0, splitIndex), prop.substring(splitIndex + 1));
      }
    }

    if (properties.size() == 0) {
      return null;
    }

    return properties;
  }
}
