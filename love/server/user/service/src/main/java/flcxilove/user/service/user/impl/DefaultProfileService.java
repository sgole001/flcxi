package flcxilove.user.service.user.impl;

import flcxilove.user.config.constant.UserEnums.Gender;
import flcxilove.user.dao.mapper.UserMapper;
import flcxilove.user.dao.po.Profile;
import flcxilove.user.dao.po.condition.ProfileCondition;
import flcxilove.user.service.dto.ProfileDto;
import flcxilove.user.service.user.ProfileService;
import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 默认账户简况处理服务实施类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-19 15:33
 */
@Service(value = "profileService")
public class DefaultProfileService implements ProfileService {

  /**
   * 持久层服务接口
   */
  @Resource
  private UserMapper userMapper;

  @Override
  public ProfileDto createProfile(String aid, ProfileDto profile) {

    // 构建用户简况PO对象
    Profile profilePo = new Profile();

    // 用户简况DTO Optional封装
    final ProfileDto profileDto = Optional.of(profile).orElse(new ProfileDto());

    // 账户ID
    profilePo.setAid(Optional.of(aid).get());
    // 昵称
    profilePo.setNickName(Optional.ofNullable(profileDto.getNickName()).orElse("---"));
    // 用户头像(路径)
    profilePo.setAvatar(Optional.ofNullable(profileDto.getAvatar()).orElse("/no_image"));
    // 性别
    profilePo.setGender(Optional.ofNullable(profileDto.getGender()).orElse(Gender.UNKNOWN.getCode()));

    // 插入用户简况数据
    userMapper.insertProfile(profilePo);

    return ProfileDto.build(profilePo);
  }

  @Override
  public ProfileDto updateProfile(String aid, ProfileDto profile) {

    // 用户简况更新内容PO对象
    Profile profileContentPo = new Profile();

    // 账户ID
    profileContentPo.setAid(Optional.of(aid).get());
    // 昵称
    profileContentPo.setNickName(profile.getNickName());
    // 用户头像(路径)
    profileContentPo.setAvatar(profile.getAvatar());
    // 性别
    profileContentPo.setGender(profile.getGender());

    // 用户简况更新条件PO对象
    ProfileCondition profileConditionPo = new ProfileCondition();
    // 账户ID
    profileConditionPo.setAid(Optional.of(aid).get());

    // 更新用户简况数据
    userMapper.updateProfile(profileContentPo, profileConditionPo);

    return ProfileDto.build(profileContentPo);
  }
}
