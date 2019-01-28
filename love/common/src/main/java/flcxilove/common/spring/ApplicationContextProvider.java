package flcxilove.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringBoot获取上下文
 *
 * @author sgole
 * @version v1.0
 * @since 2019-01-25 11:00
 */
@Component(value = "applicationContextProvider")
public class ApplicationContextProvider implements ApplicationContextAware {

  /**
   * 上下文对象实例
   */
  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext appContext) throws BeansException {
    applicationContext = appContext;
  }

  /**
   * 获取applicationContext
   *
   * @return 上下文对象实例
   */
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  /**
   * 通过name获取 Bean.
   *
   * @param name Bean名
   *
   * @return Bean对象
   */
  public static Object getBean(String name) {
    return getApplicationContext().getBean(name);
  }

  /**
   * 通过class获取Bean.
   *
   * @param clazz Bean类
   * @param <T>
   *
   * @return Bean对象
   */
  public static <T> T getBean(Class<T> clazz) {
    return getApplicationContext().getBean(clazz);
  }

  /**
   * 通过name,以及Clazz返回指定的Bean
   *
   * @param name Bean名
   * @param clazz Bean类
   * @param <T>
   *
   * @return Bean对象
   */
  public static <T> T getBean(String name, Class<T> clazz) {
    return getApplicationContext().getBean(name, clazz);
  }
}
