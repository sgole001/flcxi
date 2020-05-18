package flcxilove.user.service.dto;

import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.CollectionUtils;

/**
 * 注册用户信息DTO
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-01 15:38
 */
public class UserDto {

  /**
   * 用户账号信息
   */
  private AccountDto account;

  /**
   * 用户简况信息
   */
  private ProfileDto profile;

  /**
   * 账户角色信息
   */
  private List<RoleDto> roles;

  public AccountDto getAccount() {
    return account;
  }

  public ProfileDto getProfile() {
    return profile;
  }

  public List<RoleDto> getRoles() {
    return roles;
  }

  /**
   * 唯一构造函数，调用者不能直接创建User对象
   *
   * @param builder Builder模式构建器
   */
  private UserDto(@NotNull Builder builder) {
    this.account = builder.account;
    this.profile = builder.profile;
    this.roles = builder.roles;
  }

  @NotNull
  @Contract(" -> new")
  public static Builder builder() {
    return new Builder();
  }

  /**
   * 用户账号信息DTO对象Builder模式构建类
   *
   * @author sgole
   * @version v1.0
   * @since 2019-04-04 15:07
   */
  public static class Builder {

    /**
     * 用户账号信息
     */
    private AccountDto account;

    /**
     * 用户简况信息
     */
    private ProfileDto profile;

    /**
     * 账户角色信息
     */
    private List<RoleDto> roles;

    /**
     * 构建注册用户信息DTO
     *
     * @return 注册用户信息DTO
     */
    public UserDto build() {

      return new UserDto(this);
    }

    /**
     * 用户账号信息DTO对象构建
     *
     * @param account
     *
     * @return 构建器
     */
    public Builder account(AccountDto account) {

      if (Optional.ofNullable(account).isEmpty()) {
        return this;
      }

      this.account = account;

      return this;
    }

    /**
     * 用户简况信息DTO对象构建
     *
     * @param profile
     *
     * @return 构建器
     */
    public Builder profile(ProfileDto profile) {

      if (Optional.ofNullable(profile).isEmpty()) {
        return this;
      }

      this.profile = profile;

      return this;
    }

    /**
     * 用户角色DTO对象构建
     *
     * @param roles
     *
     * @return 构建器
     */
    public Builder roles(List<RoleDto> roles) {

      if (CollectionUtils.isEmpty(roles)) {
        return this;
      }

      this.roles = roles;

      return this;
    }
  }
}
