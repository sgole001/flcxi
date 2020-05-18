package flcxilove.user.service.cache.impl;

import flcxilove.common.exception.BusinessException;
import flcxilove.common.tools.ServletUtil;
import flcxilove.governance.redis.tools.RedisUtil;
import flcxilove.user.service.cache.UserCache;
import flcxilove.user.service.dto.AccountDto;
import flcxilove.user.service.dto.UserDto;
import flcxilove.user.service.user.AccountService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 默认用户缓存服务实现类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-26 14:50
 */
@Service(value = "userCache")
public class DefaultUserCache implements UserCache {

  @Resource(name = "accountService")
  private AccountService accountService;

  @Resource
  private RedisUtil redisUtil;

  @Override
  public UserDto currentUser() {

    // 获取资源访问令牌JWT
    String accessToken = ServletUtil.getToken();

    // 获取用户ID信息
    if (!redisUtil.exists(accessToken)) {
      throw new BusinessException("");
    }

    // 获取用户ID
    String uid = (String) redisUtil.get(accessToken);

    if (redisUtil.exists(uid)) {
      return (UserDto) redisUtil.get(uid);
    }

    // 根据UID查询账户信息
    AccountDto accountDto = accountService.searchAccountById(uid);
    // 构建用户信息
    UserDto user = UserDto.builder().account(accountDto).build();
    // 缓存用户信息
    this.cacheCurrentUser(user);

    return user;
  }

  @Override
  public void cacheCurrentUser(UserDto user) {

    // 缓存用户信息
    redisUtil.set(user.getAccount().getId(), user);
  }
}
