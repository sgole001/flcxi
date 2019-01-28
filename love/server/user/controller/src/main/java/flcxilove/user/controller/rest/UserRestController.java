package flcxilove.user.controller.rest;

import flcxilove.common.controller.RestApiController;
import flcxilove.user.api.request.CreateUsersRequestMessage;
import flcxilove.user.api.response.CreateUsersResponseMessage;
import flcxilove.user.api.response.GetUsersResponseMessage;
import flcxilove.user.api.response.data.UserData;
import flcxilove.user.api.rest.UserRest;
import flcxilove.user.service.user.UserService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController extends RestApiController implements UserRest {

  @Resource(name = "userService")
  private UserService userService;

  @Override
  public GetUsersResponseMessage<UserData> getUsersByIdV101(@PathVariable(value = "uid") String uid) throws Exception {

    // 根据请求方信息生成用户信息
    UserData userData = userService.searchUserInfo(uid);

    return super.processResult(GetUsersResponseMessage.class, userData);
  }

  @Override
  public CreateUsersResponseMessage<UserData> createUsersV101(CreateUsersRequestMessage requestMessage) throws Exception {

    // 根据请求方信息生成用户信息
    UserData userData = userService.createUser(requestMessage);

    return super.processResult(CreateUsersResponseMessage.class, userData);
  }
}
