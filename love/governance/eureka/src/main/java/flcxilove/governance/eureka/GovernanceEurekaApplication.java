package flcxilove.governance.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GovernanceEurekaApplication {

  public static void main(String[] args) {
    SpringApplication.run(GovernanceEurekaApplication.class, args);
  }
}

