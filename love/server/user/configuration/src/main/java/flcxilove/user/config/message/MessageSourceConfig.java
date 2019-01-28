//package flcxilove.user.config.message;
//
//import java.nio.charset.Charset;
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//
///**
// * 国际化配置类
// *
// * @author: sgole
// * @since: 2018/11/10
// * @version: 1.0
// */
//@Configuration
//public class MessageSourceConfig {
//
//  @Primary
//  @Bean(name = "messageSource")
//  public MessageSource messageSourceConfig() {
//
//    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//    messageSource.setCacheSeconds(-1);
//    messageSource.setDefaultEncoding(Charset.forName("UTF-8").name());
//    messageSource.setBasenames("classpath:/i18n/common/messages", "classpath:/i18n/business/messages");
//
//    return messageSource;
//  }
//}
