package flcxilove.governance.shiro.dao.entity;

/**
 * shiro过滤规则对象
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-19 15:48
 */
public class ShiroFilterChain {

  /**
   * shiro过滤对象
   */
  private String target;

  /**
   * shiro过滤器
   */
  private String filters;

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public String getFilters() {
    return filters;
  }

  public void setFilters(String filters) {
    this.filters = filters;
  }
}
