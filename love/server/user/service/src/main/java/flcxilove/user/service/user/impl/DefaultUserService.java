package flcxilove.user.service.user.impl;

import flcxilove.common.exception.BusinessException;
import flcxilove.common.tools.ServletUtil;
import flcxilove.user.config.constant.UserEnums.RoleType;
import flcxilove.user.config.message.MessageConstant;
import flcxilove.user.service.cache.UserCache;
import flcxilove.user.service.dto.AccountDto;
import flcxilove.user.service.dto.ProfileDto;
import flcxilove.user.service.dto.RoleDto;
import flcxilove.user.service.dto.UserDto;
import flcxilove.user.service.govern.UserGovernService;
import flcxilove.user.service.user.AccountService;
import flcxilove.user.service.user.ProfileService;
import flcxilove.user.service.user.UserService;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 用户服务中心实现类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-24 13:21
 */
@Service("userService")
public class DefaultUserService implements UserService {

  /**
   * 账户信息处理服务接口
   */
  @Resource(name = "accountService")
  private AccountService accountService;

  /**
   * 账户简要信息处理服务接口
   */
  @Resource(name = "profileService")
  private ProfileService profileService;

  /**
   * 账户治理服务接口
   */
  @Resource(name = "governService")
  private UserGovernService userGovernService;

  /**
   * 用户缓存服务接口
   */
  @Resource(name = "userCache")
  private UserCache userCache;

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(DefaultUserService.class.getName());

  @Override
  public UserDto enroll(AccountDto account, ProfileDto profile) {

    // 根据用户凭证获取账号信息
    List<AccountDto> oldAccount = accountService.searchAccount(account);

    // 此用户名已被注册
    if (!CollectionUtils.isEmpty(oldAccount)) {
      throw new BusinessException(MessageConstant.MSG_BIZ_00003);
    }

    // 创建用户账号信息
    AccountDto newAccount = accountService.createAccount(account);

    // 创建用户简况信息
    ProfileDto profilePo = profileService.createProfile(newAccount.getId(), profile);

    // 分配用户角色
    List<RoleDto> roles = userGovernService.distributeRolesToUser(Collections.singletonList(RoleType.NEW.getCode()), newAccount.getId());

    // 构建注册用户信息DTO
    return UserDto.builder().account(newAccount).profile(profilePo).roles(roles).build();
  }

  @Override
  public UserDto login(AccountDto account) {

    // 账户查询条件
    AccountDto condition = new AccountDto();
    // 账号编码
    condition.setLoginId(account.getLoginId());
    // 账号注册源
    condition.setSource(account.getSource());

    // 根据用户凭证获取账号信息
    AccountDto accountDto = accountService.searchUniqueAccount(condition);

    // 此用户不存在！
    if (accountDto == null) {
      throw new BusinessException(MessageConstant.MSG_BIZ_00001);
    }

    // 登录凭证校验
    if (!BCrypt.checkpw(account.getPassword(), accountDto.getPassword())) {
      // 登录名或者密码不正确！
      throw new BusinessException(MessageConstant.MSG_BIZ_00002);
    }

    // 最后登陆IP
    accountDto.setLastLoginIp(ServletUtil.getRemoteIPAddress());
    // 登陆次数
    accountDto.setLoginTimes(accountDto.getLoginTimes() + 1);

    // 账户登录信息更新
    accountService.updateAccount(accountDto, condition);

    // 构建用户信息
    UserDto userDto = UserDto.builder().account(accountDto).build();

    // 缓存当前用户信息
    userCache.cacheCurrentUser(userDto);

    return userDto;
  }
}
