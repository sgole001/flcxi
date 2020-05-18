package flcxilove.user.service.cache;

import flcxilove.user.service.dto.UserDto;

/**
 * 用户缓存服务接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-26 14:49
 */
public interface UserCache {

  /**
   * 获取当前用户信息
   *
   * @return 用户信息DTO
   */
  UserDto currentUser();

  /**
   * 缓存当前用户信息
   *
   * @param user 用户信息
   */
  void cacheCurrentUser(UserDto user);
}
