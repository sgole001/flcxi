package flcxilove.auth.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = "flcxilove")
public class AuthControllerApplication extends SpringBootServletInitializer {

  @Override
  public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(AuthControllerApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(AuthControllerApplication.class, args);
  }
}
