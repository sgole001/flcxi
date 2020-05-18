package flcxilove.user.service.dto;

import flcxilove.user.dao.po.Profile;
import org.jetbrains.annotations.NotNull;

/**
 * 用户简况DTO
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-01 16:01
 */
public class ProfileDto {

  /**
   * 昵称
   */
  private String nickName;

  /**
   * 用户头像(路径)
   */
  private String avatar;

  /**
   * 性别
   */
  private Integer gender;

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  /**
   * 根据账户简要持久对象构建DTO
   *
   * @param entity 账户简要持久对象
   *
   * @return 账户简要DTO
   */
  public static ProfileDto build(@NotNull Profile entity) {

    // DTO对象构建
    ProfileDto profileDto = new ProfileDto();

    profileDto.setNickName(entity.getNickName());
    profileDto.setAvatar(entity.getAvatar());
    profileDto.setGender(entity.getGender());

    return profileDto;
  }
}
