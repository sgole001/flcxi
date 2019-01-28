package flcxilove.user.service.user;

import flcxilove.user.api.request.CreateUsersRequestMessage;
import flcxilove.user.api.response.data.UserData;

/**
 * 用户信息相关逻辑服务接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-01-15 17:28
 */
public interface UserService {

  /**
   * 通过用户ID获取用户信息
   *
   * @param uid 用户ID
   *
   * @return 用户信息
   */
  UserData searchUserInfo(final String uid);

  /**
   * 创建用户信息
   *
   * @param requestMessage 创建用用户信息
   *
   * @return 创建后用户信息
   */
  UserData createUser(final CreateUsersRequestMessage requestMessage);
}
