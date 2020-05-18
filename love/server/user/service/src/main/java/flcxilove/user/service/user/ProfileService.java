package flcxilove.user.service.user;

import flcxilove.user.service.dto.ProfileDto;

/**
 * 账户简况处理服务接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-19 15:30
 */
public interface ProfileService {

  /**
   * 创建用户简况信息
   *
   * @param aid 账号ID
   * @param profile 用户简况信息
   *
   * @return 创建后用户简况信息
   */
  ProfileDto createProfile(String aid, ProfileDto profile);

  /**
   * 更新用户简况信息
   *
   * @param aid 账号ID
   * @param profile 用户简况信息
   *
   * @return 更新后用户简况信息
   */
  ProfileDto updateProfile(String aid, ProfileDto profile);
}
