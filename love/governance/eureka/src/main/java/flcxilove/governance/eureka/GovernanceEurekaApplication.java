package flcxilove.governance.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GovernanceEurekaApplication extends SpringBootServletInitializer {

  @Override
  public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(GovernanceEurekaApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(GovernanceEurekaApplication.class, args);
  }
}

