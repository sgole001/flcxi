package flcxilove.user.dao.mapper;

import flcxilove.user.dao.bo.RoleBo;
import flcxilove.user.dao.bo.UserBo;
import flcxilove.user.dao.po.Account;
import flcxilove.user.dao.po.Profile;
import flcxilove.user.dao.po.Role;
import flcxilove.user.dao.po.condition.AccountCondition;
import flcxilove.user.dao.po.condition.ProfileCondition;
import flcxilove.user.dao.po.condition.RoleCondition;
import flcxilove.user.dao.po.rel.AccountAndRoleRel;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 用户中心数据处理接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-28 11:43
 */
public interface UserMapper {

  /**
   * 根据用户登录凭证（用户编号和密码）查询账号信息
   *
   * @param condition 查询条件信息
   *
   * @return 账号信息
   */
  List<Account> selectAccount(@Param("condition") AccountCondition condition);

  /**
   * 查询用户信息（基本信息 + 简要 + 角色）
   *
   * @param condition 查询条件信息
   *
   * @return 账号信息（基本信息 + 简要 + 角色）
   */
  UserBo selectUserInfo(@Param("condition") AccountCondition condition);

  /**
   * 查询角色信息
   *
   * @param condition 查询条件信息
   *
   * @return 角色信息
   */
  List<RoleBo> selectRoles(@Param("condition") RoleCondition condition);

  /**
   * 根据角色编号列表查询角色信息
   *
   * @param codes 角色编号列表
   *
   * @return 角色信息
   */
  List<Role> selectRolesByCodes(@Param("rcodes") List<String> codes);

  /**
   * 新增账号信息数据处理
   *
   * @param content 账号信息
   *
   * @return 处理数据行数量
   */
  int insertAccount(@Param("content") Account content);

  /**
   * 更新账号信息数据处理
   *
   * @param content 预更新信息
   * @param condition 预更新条件
   *
   * @return 处理数据行数量
   */
  int updateAccount(@Param("content") Account content, @Param("condition") AccountCondition condition);

  /**
   * 新增用户简要信息数据处理
   *
   * @param content 用户简要信息
   *
   * @return 新增用户简要信息
   */
  int insertProfile(@Param("content") Profile content);

  /**
   * 更新用户简要信息数据处理
   *
   * @param content 预更新信息
   * @param condition 预更新条件
   *
   * @return 处理数据行数量
   */
  int updateProfile(@Param("content") Profile content, @Param("condition") ProfileCondition condition);

  /**
   * 新增账户和角色关联信息数据处理
   *
   * @param aid 账户ID
   * @param rid 角色ID
   *
   * @return 数据操作结果
   */
  int insertAccountAndRoleRel(@Param("aid") String aid, @Param("rid") String rid);

  /**
   * 批量新增账户关联角色信息
   *
   * @param rels 账户关联角色信息列表
   *
   * @return 数据操作结果
   */
  int batchRoleToAccount(@Param("rels") List<AccountAndRoleRel> rels);

  /**
   * 新增角色和权限关联信息数据处理
   *
   * @param rid 角色ID
   * @param pid 权限ID
   *
   * @return 数据操作结果
   */
  int insertRoleAndPrivilegeRel(@Param("rid") String rid, @Param("pid") String pid);
}
