package flcxilove.common.tools;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

/**
 * 消息工具类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-20 14:05
 */
@Component(value = "message")
public class MessageUtil {

  @Resource(name = "flcMessageSource")
  private MessageSource messageSource;

  /**
   * MessageSource的封装类
   */
  public static MessageSourceAccessor accessor;

  @PostConstruct
  private void init() {
    ((ReloadableResourceBundleMessageSource) messageSource).setResourceLoader(new PathMatchingResourcePatternResolver());
    accessor = new MessageSourceAccessor(messageSource, LocaleContextHolder.getLocale());
  }
}
