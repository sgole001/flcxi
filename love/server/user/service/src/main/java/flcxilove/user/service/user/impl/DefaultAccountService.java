package flcxilove.user.service.user.impl;

import flcxilove.common.exception.BusinessException;
import flcxilove.common.tools.PropertyUtil;
import flcxilove.common.tools.ServletUtil;
import flcxilove.user.config.message.MessageConstant;
import flcxilove.user.dao.mapper.UserMapper;
import flcxilove.user.dao.po.Account;
import flcxilove.user.dao.po.condition.AccountCondition;
import flcxilove.user.service.dto.AccountDto;
import flcxilove.user.service.user.AccountService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 账户处理服务实施类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-23 14:08
 */
@Service(value = "accountService")
public class DefaultAccountService implements AccountService {

  @Resource
  private UserMapper userMapper;

  /**
   * 加密用Salt复杂度：位数属性Key名
   */
  private static final String SALT_KEY = "crypto.salt.size";

  @Override
  public AccountDto searchAccountById(String id) {

    // 构建查询条件
    AccountDto condition = new AccountDto();
    // 账号ID
    condition.setId(id);

    // 唯一账号信息查询
    return this.searchUniqueAccount(condition);
  }

  @Override
  public AccountDto searchUniqueAccount(AccountDto condition) {

    // 根据查询条件获取账号信息
    List<AccountDto> accounts = this.searchAccount(condition);

    // 没有查询到用户信息
    if (CollectionUtils.isEmpty(accounts)) {
      return null;
    }

    // 账号数量超过1个
    if (accounts.size() > 1) {
      throw new BusinessException(MessageConstant.MSG_BIZ_00008);
    }

    return accounts.get(0);
  }

  @Override
  public List<AccountDto> searchAccount(AccountDto condition) {

    // 参数Optional非空
    Optional.of(condition);

    // 账号更新条件PO对象
    AccountCondition accountConditionPo = condition.convertToCondition();

    // 查询账号信息
    List<Account> searchResult = userMapper.selectAccount(accountConditionPo);

    // 没有查询到数据
    if (CollectionUtils.isEmpty(searchResult)) {
      return null;
    }

    return searchResult.stream().map(AccountDto::build).collect(Collectors.toList());
  }

  @Override
  public AccountDto createAccount(AccountDto account) {

    // 参数Optional非空
    Optional.of(account);

    // 构建账号PO对象
    Account accountPo = account.convertToEntity();
    // 加密用Salt负载度-位数获取
    final Integer saltSize = PropertyUtil.binder.bind(SALT_KEY, Integer.class).get();
    accountPo.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(saltSize)));
    // 创建时IP
    accountPo.setCreatIp(ServletUtil.getRemoteIPAddress());
    // 最后登陆IP
    accountPo.setLastLoginIp(accountPo.getCreatIp());
    // 登陆次数(注册视为首次登录)
    accountPo.setLoginTimes(1);

    // 插入账号数据
    userMapper.insertAccount(accountPo);

    return AccountDto.build(accountPo);
  }

  @Override
  public AccountDto updateAccount(AccountDto content, AccountDto condition) {

    // 参数Optional非空
    Optional.of(content);
    Optional.of(condition);

    // 构建账号更新内容PO对象
    Account accountContentPo = content.convertToEntity();

    // 账号更新条件PO对象
    AccountCondition accountConditionPo = condition.convertToCondition();

    // 更新账号数据
    userMapper.updateAccount(accountContentPo, accountConditionPo);

    return AccountDto.build(accountContentPo);
  }
}
