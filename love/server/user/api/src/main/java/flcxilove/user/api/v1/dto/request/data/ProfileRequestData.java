package flcxilove.user.api.v1.dto.request.data;

/**
 * 账号简况请求数据
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-19 14:44
 */
public class ProfileRequestData {

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
}
