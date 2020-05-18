package flcxilove.auth.controller.rest.v1;

import flcxilove.auth.api.v1.request.ApplyTokenRequestMessage;
import flcxilove.auth.api.v1.response.ApplyTokenResponseMessage;
import flcxilove.auth.api.v1.response.data.TokenData;
import flcxilove.auth.api.v1.rest.AuthRestV1;
import flcxilove.auth.service.dto.AuthDto;
import flcxilove.auth.service.token.JwtService;
import flcxilove.common.controller.RestApiController;
import flcxilove.common.feign.FeignClientBuilder;
import flcxilove.user.api.v1.dto.request.LoginUserRequestMessage;
import flcxilove.user.api.v1.dto.request.data.LoginUserRequestData;
import flcxilove.user.api.v1.dto.response.LoginUserResponseMessage;
import flcxilove.user.api.v1.dto.response.data.UserData;
import flcxilove.user.api.v1.rest.UserRestV1;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestV1Controller extends RestApiController implements AuthRestV1 {

  @Resource(name = "jwtService")
  private JwtService jwtService;

  @Resource(name = "feignClientBuilder")
  private FeignClientBuilder feignClientBuilder;

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(AuthRestV1Controller.class.getName());

  @Override
  public ApplyTokenResponseMessage<TokenData> applyToken(ApplyTokenRequestMessage requestMessage) throws Exception {

    // 申请令牌用户登录处理
    LoginUserRequestMessage loginUserRequestMessage = new LoginUserRequestMessage();
    LoginUserRequestData loginUser = new LoginUserRequestData();
    loginUser.setLoginId(requestMessage.getLoginId());
    loginUser.setPassword(requestMessage.getLoginCert());
    loginUser.setSource(requestMessage.getSource());
    loginUserRequestMessage.setLoginUser(loginUser);
    // 用户登录处理
    UserRestV1 userRest = this.feignClientBuilder.build(UserRestV1.class, "user-service");
    LoginUserResponseMessage responseMessage = userRest.login(loginUserRequestMessage);
    // 获取用户信息
    UserData userData = (UserData) responseMessage.getData();

    AuthDto authDto = new AuthDto();
    authDto.setUid(userData.getUid());
    authDto.setSource(userData.getSource());
    authDto.setSecretKey(requestMessage.getLoginCert());
    authDto.setRoles(userData.getRoles());

    // 根据请求方信息生成JWT
    TokenData tokenData = jwtService.applyToken(authDto);

    return super.processResult(ApplyTokenResponseMessage.class, tokenData);
  }
}
