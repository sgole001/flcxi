package flcxilove.common.beans;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * AES256加解密算法信息
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
@Component
@PropertySource("classpath:/config/crypto.properties")
public class AES256 extends CryptoBean {

  /**
   * 属性Key前缀
   */
  public static final String PREFIX = "aes.256";
}
