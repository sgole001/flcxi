package flcxilove.user.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "flcxilove")
@EnableEurekaClient
@EnableCaching
public class UserControllerApplication extends SpringBootServletInitializer {

  @Override
  public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(UserControllerApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(UserControllerApplication.class, args);
  }

}

