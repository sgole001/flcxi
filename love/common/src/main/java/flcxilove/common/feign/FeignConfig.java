package flcxilove.common.feign;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

  @Bean(name = "feignBuilder")
  public Feign.Builder feignBuilder() {

    // Feign构建器
    Feign.Builder feignBuilder = Feign.builder();

    // 注解协议
    feignBuilder.contract(new SpringMvcContract());
    // 调用加密规则
    feignBuilder.encoder(new JacksonEncoder());
    // 调用解密规则
    feignBuilder.decoder(new JacksonDecoder());

    return feignBuilder;
  }
}
