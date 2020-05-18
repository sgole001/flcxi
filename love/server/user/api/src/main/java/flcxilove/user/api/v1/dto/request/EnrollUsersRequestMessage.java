package flcxilove.user.api.v1.dto.request;

import flcxilove.common.api.RestRequestMessage;
import flcxilove.user.api.v1.dto.request.data.EnrollUserRequestData;

/**
 * 用户注册请求消息类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-04 11:20
 */
public class EnrollUsersRequestMessage extends RestRequestMessage {

  private static final long serialVersionUID = -233305085121617933L;

  /**
   * 用户注册请求数据
   */
  private EnrollUserRequestData enrollUsers;

  public EnrollUserRequestData getEnrollUsers() {
    return enrollUsers;
  }

  public void setEnrollUsers(EnrollUserRequestData enrollUsers) {
    this.enrollUsers = enrollUsers;
  }
}
