package flcxilove.common.messages;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 国际化配置类
 *
 * @author: sgole
 * @since: 2018/11/10
 * @version: 1.0
 */
@Configuration
public class MessageSourceConfig {

  /**
   * 资源文件路径匹配规则
   */
  private static Pattern pattern = Pattern.compile("^.*(/i18n/.*/messages)");

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(MessageSourceConfig.class.getName());

  @Primary
  @Bean(name = "flcMessageSource")
  public MessageSource messageSourceConfig() {

    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setCacheSeconds(-1);
    messageSource.setDefaultEncoding(Charset.forName("UTF-8").name());
    this.buildBaseNames(messageSource);
    return messageSource;
  }

  /**
   * 构建消息配置BaseName信息
   *
   * @param messageSource 消息管理对象
   */
  private void buildBaseNames(ReloadableResourceBundleMessageSource messageSource) {

    // 路径匹配资源加载器
    PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

    // BaseName列表
    Set<String> baseNames = new HashSet<>();

    try {
      // 资源加载
      Resource[] resources = pathMatchingResourcePatternResolver.getResources("classpath*:/i18n/**/messages**");
      // 资源路径
      String resourcePath;

      for (Resource resource : resources) {
        // 获取资源路径
        resourcePath = resource.getURI().getPath();

        if (StringUtils.isEmpty(resourcePath)) {
          resourcePath = resource.getURL().getPath();
        }

        // 非properties文件剔除
        if (StringUtils.isEmpty(resourcePath) || !resourcePath.endsWith(".properties")) {
          continue;
        }

        // 文件路径解析
        Matcher matcher = pattern.matcher(resourcePath);

        if (matcher.find()) {

          // 文件路径解析（file:....）
          String baseName = "file:" + matcher.group();

          // 提取公共目录
          if (!baseNames.contains(baseName)) {
            baseNames.add(baseName);
          }
        }
      }

      if (CollectionUtils.isEmpty(baseNames)) {
        // 默认BaseName
        messageSource.setBasenames("classpath:/i18n/message");
      } else {
        messageSource.setBasenames(baseNames.toArray(new String[baseNames.size()]));
      }

    } catch (Exception ex) {
      // ...
      logger.error(ex);
    }
  }
}
