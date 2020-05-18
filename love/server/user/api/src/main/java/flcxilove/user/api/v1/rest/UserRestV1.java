package flcxilove.user.api.v1.rest;

import flcxilove.common.api.RestApi;
import flcxilove.user.api.v1.dto.request.EnrollUsersRequestMessage;
import flcxilove.user.api.v1.dto.request.LoginUserRequestMessage;
import flcxilove.user.api.v1.dto.request.UpdateProfileRequestMessage;
import flcxilove.user.api.v1.dto.response.EnrollUsersResponseMessage;
import flcxilove.user.api.v1.dto.response.LoginUserResponseMessage;
import flcxilove.user.api.v1.dto.response.UpdateProfileResponseMessage;
import flcxilove.user.api.v1.dto.response.data.ProfileData;
import flcxilove.user.api.v1.dto.response.data.UserData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户服务中心接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-01-15 16:00
 */
public interface UserRestV1 extends RestApi {

  /**
   * 注册用户
   *
   * @param requestMessage 用户注册用信息
   *
   * @return 注册用户信息
   *
   * @throws Exception
   */
  @RequestMapping(value = "/users", method = RequestMethod.POST, produces = "application/vnd.api.v1+json")
  EnrollUsersResponseMessage<UserData> enroll(@RequestBody EnrollUsersRequestMessage requestMessage) throws Exception;

  /**
   * 用户登录
   *
   * @param requestMessage 用户登录请求消息
   *
   * @return 用户登录响应消息
   *
   * @throws Exception
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/vnd.api.v1+json")
  LoginUserResponseMessage login(@RequestBody LoginUserRequestMessage requestMessage) throws Exception;

  /**
   * 更新账户简况信息
   *
   * @param uid 用户ID
   * @param requestMessage 更新用账户简况信息
   *
   * @return 更新后账户简况信息
   *
   * @throws Exception
   */
  @RequestMapping(value = "/users/{uid}/profiles", method = RequestMethod.PUT, produces = "application/vnd.api.v1+json")
  UpdateProfileResponseMessage<ProfileData> updateProfile(@PathVariable(value = "uid") String uid, @RequestBody UpdateProfileRequestMessage requestMessage) throws Exception;
}
