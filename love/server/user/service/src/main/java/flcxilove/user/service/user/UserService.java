package flcxilove.user.service.user;

import flcxilove.user.service.dto.AccountDto;
import flcxilove.user.service.dto.ProfileDto;
import flcxilove.user.service.dto.UserDto;

/**
 * 用户服务中心接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-01-15 17:28
 */
public interface UserService {

  /**
   * 用户注册处理
   *
   * @param account 账户注册信息
   * @param profile 账户简要信息
   *
   * @return 注册用户信息DTO
   */
  UserDto enroll(AccountDto account, ProfileDto profile);

  /**
   * 用户登录处理
   *
   * @param account 账户登录信息
   *
   * @return 登录用户信息DTO
   */
  UserDto login(AccountDto account);
}
