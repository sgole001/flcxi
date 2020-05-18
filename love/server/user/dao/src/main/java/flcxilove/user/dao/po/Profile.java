package flcxilove.user.dao.po;

import flcxilove.governance.mybatis.bean.entity.CommonEntity;

/**
 * 第一方注册用户简况持久层映射模型
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-26 18:19
 */
public class Profile extends CommonEntity {

  /**
   * 模型对应持久层对象名
   */
  public static final String PERSISTENCE_MAPPER = "Profile";

  /**
   * 账号ID
   */
  private String aid;

  /**
   * 昵称
   */
  private String nickName;

  /**
   * 用户头像(路径)
   */
  private String avatar;

  /**
   * 性别 0 : male; 1 : female
   */
  private Integer gender;

  public String getAid() {
    return aid;
  }

  public void setAid(String aid) {
    this.aid = aid;
  }

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
