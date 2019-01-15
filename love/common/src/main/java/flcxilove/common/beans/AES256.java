package flcxilove.common.beans;

import flcxilove.common.tools.CryptoUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * AES256加解密算法信息
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
@Configuration
@PropertySource("classpath:config/crypto.properties")
@ConfigurationProperties(prefix = "aes.256")
public class AES256 extends CryptoBean {

    @Bean("AES256Util")
    @Override
    CryptoUtil cryptoUtil() {
        return new CryptoUtil(this);
    }
}
