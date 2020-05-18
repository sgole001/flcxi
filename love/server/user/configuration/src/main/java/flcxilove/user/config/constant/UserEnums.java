package flcxilove.user.config.constant;

/**
 * 用户服务枚举管理接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-01 17:16
 */
public interface UserEnums {

  /**
   * 性别枚举
   *
   * @author sgole
   * @version v1.0
   * @since 2019-04-01 17:15
   */
  enum Gender implements UserEnums {

    /**
     * 男性
     */
    MAN(0, "male"),

    /**
     * 女性
     */
    FEMALE(1, "female"),

    /**
     * 未知
     */
    UNKNOWN(2, "Unknown");

    /**
     * 枚举编号
     */
    private Integer code;

    /**
     * 枚举显示名
     */
    private String name;

    /**
     * 构造函数
     *
     * @param code 枚举编号
     * @param name 枚举显示名
     */
    Gender(Integer code, String name) {
      this.code = code;
      this.name = name;
    }

    public static Gender of(Integer code) {
      for (Gender value : Gender.values()) {

        if (code.equals(value.getCode())) {
          return value;
        }
      }
      return null;
    }

    public Integer getCode() {
      return code;
    }

    public String getName() {
      return name;
    }}

  /**
   * 权限类型
   *
   * @author sgole
   * @version v1.0
   * @since 2019-04-01 17:39
   */
  enum PrivilegeType implements UserEnums {

    /**
     * RestAPI
     */
    RESTAPI(0, "RestAPI"),
    /**
     * 页面元素
     */
    PAGE(1, "Page"),
    /**
     * 中台操作功能项
     */
    BACKEND(2, "Backend"),
    /**
     * 文件资源
     */
    FILE(3, "File");

    /**
     * 枚举编号
     */
    private Integer code;

    /**
     * 枚举显示名
     */
    private String name;

    /**
     * 构造函数
     *
     * @param code 枚举编号
     * @param name 枚举显示名
     */
    PrivilegeType(Integer code, String name) {
      this.code = code;
      this.name = name;
    }

    public static PrivilegeType of(Integer code) {
      for (PrivilegeType value : PrivilegeType.values()) {

        if (code.equals(value.getCode())) {
          return value;
        }
      }
      return null;
    }

    public Integer getCode() {
      return code;
    }

    public String getName() {
      return name;
    }}

  /**
   * 账户注册源
   *
   * @author sgole
   * @version v1.0
   * @since 2019-04-02 14:12
   */
  enum EnrollSource implements UserEnums {

    /**
     * 自主平台
     */
    OWN("Own", "Own"),
    /**
     * 支付宝
     */
    ALIPAY("Alipay", "Alipay"),
    /**
     * 微信
     */
    WECHAT("Wechat", "Wechat"),
    /**
     * 微信小程序
     */
    MINI_PROGRAM_WECHAT("MINI_PROGRAM_WECHAT", "MINI_PROGRAM_WECHAT");

    /**
     * 枚举编号
     */
    private String code;

    /**
     * 枚举显示名
     */
    private String name;

    /**
     * 构造函数
     *
     * @param code 枚举编号
     * @param name 枚举显示名
     */
    EnrollSource(String code, String name) {
      this.code = code;
      this.name = name;
    }

    public static EnrollSource of(String code) {
      for (EnrollSource value : EnrollSource.values()) {

        if (code.equals(value.getCode())) {
          return value;
        }
      }
      return null;
    }

    public String getCode() {
      return code;
    }

    public String getName() {
      return name;
    }}

  /**
   * 账户角色类型
   *
   * @author sgole
   * @version v1.0
   * @since 2019-04-02 14:12
   */
  enum RoleType implements UserEnums {

    /**
     * 新注册会员
     */
    NEW("Account:New", "Customer:New"),
    /**
     * 普通会员
     */
    ORDINARY("Account:Ordinary", "Account:Ordinary"),
    /**
     * VIP会员
     */
    VIP("Account:VIP", "Account:VIP");

    /**
     * 枚举编号
     */
    private String code;

    /**
     * 枚举显示名
     */
    private String name;

    /**
     * 构造函数
     *
     * @param code 枚举编号
     * @param name 枚举显示名
     */
    RoleType(String code, String name) {
      this.code = code;
      this.name = name;
    }

    public static RoleType of(String code) {
      for (RoleType value : RoleType.values()) {

        if (code.equals(value.getCode())) {
          return value;
        }
      }
      return null;
    }

    public String getCode() {
      return code;
    }

    public String getName() {
      return name;
    }}
}
