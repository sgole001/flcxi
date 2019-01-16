package flcxilove.user.api.rest;

import flcxilove.user.api.response.GetUsersResponseMessage;
import flcxilove.user.api.response.data.UserData;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <Description>
 *
 * @author sgole
 * @version v1.0
 * @since 2019-01-15 16:00
 */
public interface UserRest {

  /**
   * 根据用户ID获取用户信息
   *
   * @return 用户信息
   *
   * @throws Exception
   * @version v1.0.1
   */
  @RequestMapping(value = "/users/{uid}", method = RequestMethod.GET, produces = "application/vnd.api.v1.0.1+json")
  GetUsersResponseMessage<UserData> getUsersByIdV101(@PathVariable(value = "uid") String uid) throws Exception;
}
