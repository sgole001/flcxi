package flcxilove.auth.controller.rest;

import flcxilove.auth.api.request.FetchTokenRequestMessage;
import flcxilove.auth.api.response.FetchTokenResponseMessage;
import flcxilove.auth.api.response.data.FetchTokenData;
import flcxilove.auth.api.rest.AuthRest;
import flcxilove.auth.service.token.JwtService;
import flcxilove.common.controller.RestApiController;
import flcxilove.common.feign.FeignClientBuilder;
import flcxilove.user.api.request.CreateUsersRequestMessage;
import flcxilove.user.api.rest.UserRest;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController extends RestApiController implements AuthRest {

  @Resource(name = "jwtService")
  private JwtService jwtService;

  @Resource(name = "feignClientBuilder")
  private FeignClientBuilder feignClientBuilder;

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(AuthRestController.class.getName());

  @Override
  public FetchTokenResponseMessage<FetchTokenData> accessTokenV101(FetchTokenRequestMessage requestMessage) throws Exception {

    // 根据请求方信息生成JWT
    FetchTokenData tokenData = jwtService.generateJwt(requestMessage);

    // 创建用户
    CreateUsersRequestMessage createUsersRequestMessage = new CreateUsersRequestMessage();
    createUsersRequestMessage.setUid(requestMessage.getClientId());
    createUsersRequestMessage.setName("sgole");
    createUsersRequestMessage.setAge(35);
    createUsersRequestMessage.setGender("Male");
    UserRest userRest = this.feignClientBuilder.build(UserRest.class, "user-service");
    userRest.createUsersV101(createUsersRequestMessage);

    return super.processResult(FetchTokenResponseMessage.class, tokenData);
  }

  @Override
  public FetchTokenResponseMessage<FetchTokenData> accessTokenV102(FetchTokenRequestMessage requestMessage) throws Exception {

    // 根据请求方信息生成JWT
    FetchTokenData tokenData = jwtService.generateJwt(requestMessage);

    return super.processResult(FetchTokenResponseMessage.class, tokenData);
  }
}
