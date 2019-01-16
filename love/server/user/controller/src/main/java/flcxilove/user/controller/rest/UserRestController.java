package flcxilove.user.controller.rest;

import flcxilove.common.controller.RestApiController;
import flcxilove.common.exception.BaseException;
import flcxilove.user.api.response.GetUsersResponseMessage;
import flcxilove.user.api.response.data.UserData;
import flcxilove.user.api.rest.UserRest;
import flcxilove.user.config.message.MessageConstant;
import flcxilove.user.service.user.UserService;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController extends RestApiController implements UserRest {

  @Resource(name = "userService")
  private UserService userService;

  @Override
  public GetUsersResponseMessage<UserData> getUsersByIdV101(@PathVariable(value = "uid") String uid) throws Exception {

    try {

      // 根据请求方信息生成用户信息
      UserData userData = userService.searchUserInfo(uid);

      return super.processResult(HttpStatus.OK, MessageConstant.MSG_SYS_00000, null, GetUsersResponseMessage.class, userData);

    } catch (BaseException e) {
      return super.processResult(HttpStatus.INTERNAL_SERVER_ERROR, e, GetUsersResponseMessage.class);
    }
  }
}
