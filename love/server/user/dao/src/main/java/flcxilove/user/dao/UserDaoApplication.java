package flcxilove.user.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "flcxilove")
@MapperScan("flcxilove.user.dao.mapper")
public class UserDaoApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserDaoApplication.class, args);
  }

}

