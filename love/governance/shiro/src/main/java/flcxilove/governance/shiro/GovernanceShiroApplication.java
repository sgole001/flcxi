package flcxilove.governance.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "flcxilove")
@MapperScan("flcxilove.governance.shiro.dao.mapper")
public class GovernanceShiroApplication {

  public static void main(String[] args) {
    SpringApplication.run(GovernanceShiroApplication.class, args);
  }

}

