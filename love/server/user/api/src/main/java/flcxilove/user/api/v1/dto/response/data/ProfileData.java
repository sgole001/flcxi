package flcxilove.user.api.v1.dto.response.data;

import flcxilove.common.api.HateoasLink;

/**
 * 账户简况资源数据
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-19 15:01
 */
public class ProfileData {

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

  /**
   * 相关账号资源Hypermedia
   */
  private HateoasLink link;

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

  public HateoasLink getLink() {
    return link;
  }

  public void setLink(HateoasLink link) {
    this.link = link;
  }
}
