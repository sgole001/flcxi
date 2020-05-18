package flcxilove.user.api.v1.dto.request;

import flcxilove.common.api.RestRequestMessage;
import flcxilove.user.api.v1.dto.request.data.LoginUserRequestData;

/**
 * 用户登录请求消息类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-24 11:30
 */
public class LoginUserRequestMessage extends RestRequestMessage {

  private static final long serialVersionUID = 8578380389260490210L;

  /**
   * 用户登录请求数据
   */
  private LoginUserRequestData loginUser;

  public LoginUserRequestData getLoginUser() {
    return loginUser;
  }

  public void setLoginUser(LoginUserRequestData loginUser) {
    this.loginUser = loginUser;
  }
}
