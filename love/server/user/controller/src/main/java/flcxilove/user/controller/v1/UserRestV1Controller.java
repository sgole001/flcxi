package flcxilove.user.controller.v1;

import flcxilove.common.controller.RestApiController;
import flcxilove.user.api.v1.dto.request.EnrollUsersRequestMessage;
import flcxilove.user.api.v1.dto.request.LoginUserRequestMessage;
import flcxilove.user.api.v1.dto.request.UpdateProfileRequestMessage;
import flcxilove.user.api.v1.dto.request.data.EnrollUserRequestData;
import flcxilove.user.api.v1.dto.request.data.LoginUserRequestData;
import flcxilove.user.api.v1.dto.response.EnrollUsersResponseMessage;
import flcxilove.user.api.v1.dto.response.LoginUserResponseMessage;
import flcxilove.user.api.v1.dto.response.UpdateProfileResponseMessage;
import flcxilove.user.api.v1.dto.response.data.ProfileData;
import flcxilove.user.api.v1.dto.response.data.UserData;
import flcxilove.user.api.v1.rest.UserRestV1;
import flcxilove.user.config.constant.UserEnums.Gender;
import flcxilove.user.service.dto.AccountDto;
import flcxilove.user.service.dto.ProfileDto;
import flcxilove.user.service.dto.RoleDto;
import flcxilove.user.service.dto.UserDto;
import flcxilove.user.service.user.ProfileService;
import flcxilove.user.service.user.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务中心控制类
 *
 * @author sgole
 * @version v1
 * @since 2019-04-04 13:18
 */
@RestController
public class UserRestV1Controller extends RestApiController implements UserRestV1 {

  /**
   * 用户服务接口
   */
  @Resource(name = "userService")
  private UserService userService;

  /**
   * 账户简况服务接口
   */
  @Resource(name = "profileService")
  private ProfileService profileService;

  @Override
  public EnrollUsersResponseMessage<UserData> enroll(EnrollUsersRequestMessage requestMessage) throws Exception {

    // 获取注册用户请求信息
    EnrollUserRequestData enrollUser = Optional.of(requestMessage.getEnrollUsers()).get();

    List<UserData> responseData = new ArrayList<>();

    // 用户账号信息DTO
    AccountDto accountDto = new AccountDto();
    accountDto.setLoginId(Optional.of(enrollUser.getLoginId()).get());
    accountDto.setSource(Optional.of(enrollUser.getSource()).get());
    accountDto.setPassword(Optional.of(enrollUser.getPassword()).get());

    // 用户简况DTO
    ProfileDto profileDto = new ProfileDto();
    profileDto.setNickName(enrollUser.getNickName());
    profileDto.setAvatar(enrollUser.getAvatar());
    profileDto.setGender(enrollUser.getGender());

    // 注册处理
    UserDto userDto = userService.enroll(accountDto, profileDto);

    UserData userData = new UserData();
    userData.setUid(userDto.getAccount().getId());
    userData.setLoginId(userDto.getAccount().getLoginId());
    userData.setSource(userDto.getAccount().getSource());
    userData.setEmail(userDto.getAccount().getEmail());
    userData.setMobile(userDto.getAccount().getMobile());
    userData.setExternalId(userDto.getAccount().getExternalId());
    userData.setCreatIp(userDto.getAccount().getCreatIp());
    userData.setLastLoginIp(userDto.getAccount().getLastLoginIp());
    userData.setLoginTimes(userDto.getAccount().getLoginTimes());
    userData.setNickName(userDto.getProfile().getNickName());
    userData.setAvatar(userDto.getProfile().getAvatar());
    userData.setGender(Gender.of(userDto.getProfile().getGender()).getName());
    if (!CollectionUtils.isEmpty(userDto.getRoles())) {
      userData.setRoles(userDto.getRoles().stream().map(RoleDto::getName).collect(Collectors.toList()));
    }

    responseData.add(userData);

    return super.processResult(EnrollUsersResponseMessage.class, responseData);
  }

  @Override
  public LoginUserResponseMessage<UserData> login(LoginUserRequestMessage requestMessage) throws Exception {

    // 构建用户账号信息DTO
    LoginUserRequestData requestData = requestMessage.getLoginUser();

    AccountDto accountDto = new AccountDto();
    accountDto.setLoginId(requestData.getLoginId());
    accountDto.setSource(requestData.getSource());
    accountDto.setPassword(requestData.getPassword());

    // 调用用户登录服务处理
    UserDto userDto = userService.login(accountDto);
    //

    // 构建用户登录返回数据
    UserData userData = new UserData();
    userData.setUid(userDto.getAccount().getId());
    userData.setLoginId(userDto.getAccount().getLoginId());
    userData.setSource(userDto.getAccount().getSource());
    userData.setEmail(userDto.getAccount().getEmail());
    userData.setMobile(userDto.getAccount().getMobile());
    userData.setExternalId(userDto.getAccount().getExternalId());
    userData.setCreatIp(userDto.getAccount().getCreatIp());
    userData.setLastLoginIp(userDto.getAccount().getLastLoginIp());
    userData.setLoginTimes(userDto.getAccount().getLoginTimes());

    return super.processResult(LoginUserResponseMessage.class, userData);
  }

  @Override
  public UpdateProfileResponseMessage<ProfileData> updateProfile(@PathVariable String uid, UpdateProfileRequestMessage requestMessage) throws Exception {

    // 更新后账户简况信息
    ProfileData responseData = new ProfileData();

    ProfileDto profileDto = new ProfileDto();
    profileDto.setNickName(requestMessage.getProfile().getNickName());
    profileDto.setAvatar(requestMessage.getProfile().getAvatar());
    profileDto.setGender(requestMessage.getProfile().getGender());

    profileDto = profileService.updateProfile(uid, profileDto);

    responseData.setNickName(profileDto.getNickName());
    responseData.setAvatar(profileDto.getAvatar());
    responseData.setGender(profileDto.getGender());

    return super.processResult(UpdateProfileResponseMessage.class, responseData);
  }
}
