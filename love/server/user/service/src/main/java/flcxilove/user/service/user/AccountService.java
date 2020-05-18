package flcxilove.user.service.user;

import flcxilove.user.service.dto.AccountDto;
import java.util.List;

/**
 * 账户处理服务接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-23 14:07
 */
public interface AccountService {

  /**
   * 根据ID获取账号信息
   *
   * @param id 账号ID
   *
   * @return 唯一账号信息
   */
  AccountDto searchAccountById(String id);

  /**
   * 获取唯一账号信息
   *
   * @param condition 账号信息查询条件
   *
   * @return 唯一账号信息
   */
  AccountDto searchUniqueAccount(AccountDto condition);

  /**
   * 获取账号信息
   *
   * @param condition 账户信息查询条件
   *
   * @return 账号信息
   */
  List<AccountDto> searchAccount(AccountDto condition);

  /**
   * 创建账户信息
   *
   * @param account 账户信息
   *
   * @return 创建后账户信息
   */
  AccountDto createAccount(AccountDto account);

  /**
   * 更新账户信息
   *
   * @param content 预更新内容
   * @param condition 预更新条件
   *
   * @return 更新后账户信息
   */
  AccountDto updateAccount(AccountDto content, AccountDto condition);
}
