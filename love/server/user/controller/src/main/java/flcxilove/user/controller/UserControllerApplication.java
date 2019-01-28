package flcxilove.user.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "flcxilove")
@EnableEurekaClient
public class UserControllerApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserControllerApplication.class, args);
  }

}

