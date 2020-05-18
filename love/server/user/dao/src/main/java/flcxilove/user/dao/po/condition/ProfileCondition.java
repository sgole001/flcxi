package flcxilove.user.dao.po.condition;

import flcxilove.governance.mybatis.bean.entity.Condition;

/**
 * 账户简要数据处理条件
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-23 10:34
 */
public class ProfileCondition implements Condition {

  /**
   * 账号ID
   */
  private String aid;

  /**
   * 昵称
   */
  private String nickName;

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
}
