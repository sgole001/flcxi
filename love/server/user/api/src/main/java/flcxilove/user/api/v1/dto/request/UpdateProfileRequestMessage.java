package flcxilove.user.api.v1.dto.request;

import flcxilove.common.api.RestRequestMessage;
import flcxilove.user.api.v1.dto.request.data.ProfileRequestData;

/**
 * 更新账户简况请求消息类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-19 14:47
 */
public class UpdateProfileRequestMessage extends RestRequestMessage {

  private static final long serialVersionUID = 4142908895041518880L;

  /**
   * 账号简况请求数据
   */
  private ProfileRequestData profile;

  public ProfileRequestData getProfile() {
    return profile;
  }

  public void setProfile(ProfileRequestData profile) {
    this.profile = profile;
  }
}
