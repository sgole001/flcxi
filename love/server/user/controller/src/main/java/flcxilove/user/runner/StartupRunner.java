package flcxilove.user.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import flcxilove.user.service.user.RoleService;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 服务启动时任务加载
 *
 * @author sgole
 * @version v1.0
 * @since 2019-05-21 10:22
 */
@Component
public class StartupRunner implements CommandLineRunner {

  @Resource(name = "roleService")
  private RoleService roleService;

  @Autowired
  ObjectMapper objectMapper;

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(StartupRunner.class.getName());

  @Override
  public void run(String... args) throws Exception {

    // 加载角色图信息
    this.roleService.loadRoleGraph();

  }
}
