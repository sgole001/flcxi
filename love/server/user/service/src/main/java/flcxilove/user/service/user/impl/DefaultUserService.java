package flcxilove.user.service.user.impl;

import flcxilove.common.exception.BusinessException;
import flcxilove.user.api.response.data.UserData;
import flcxilove.user.config.message.MessageConstant;
import flcxilove.user.service.user.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class DefaultUserService implements UserService {

  @Override
  public UserData searchUserInfo(String uid) {

    // 消息主体：用户信息
    UserData userData = new UserData();

    try {

      // 用户ID
      userData.setUid(uid);
      // 用户姓名
      userData.setName("sgole");
      // 用户年龄
      userData.setAge(35);
      // 用户性别
      userData.setGender("Male");

      return userData;

    } catch (Exception e) {
      throw new BusinessException(MessageConstant.MSG_BIZ_00001, e);
    }
  }
}
