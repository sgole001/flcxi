package flcxilove.user.api.response.data;

/**
 * 消息主体 : 用户信息
 *
 * @author sgole
 * @version v1.0
 * @since 2019-01-15 16:02
 */
public class UserData {

  /**
   * UID
   */
  private String uid;

  /**
   * 用户姓名
   */
  private String name;

  /**
   * 用户年龄
   */
  private Integer age;

  /**
   * 用户性别
   */
  private String gender;

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
}
