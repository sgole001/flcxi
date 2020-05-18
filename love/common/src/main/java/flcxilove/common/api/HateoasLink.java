package flcxilove.common.api;

/**
 * RestAPI HATEOAS设计接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-19 15:15
 */
public class HateoasLink {

  /**
   * 参照API与当前资源的关系
   */
  private String rel;

  /**
   * 参照API路径
   */
  private String href;

  public String getRel() {
    return rel;
  }

  public void setRel(String rel) {
    this.rel = rel;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }
}
