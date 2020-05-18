package flcxilove.user.dao.po;

/**
 * 接口信息资源持久层映射模型
 *
 * @author sgole
 * @version v1.0
 * @since 2019-05-15 15:48
 */
public class Interface extends Resource {

  /**
   * 接口协议
   */
  private String protocol;

  /**
   * 主机地址（可以是域名，也可以是IP地址）
   */
  private String host;

  /**
   * 接口端口
   */
  private Integer port;

  /**
   * 接口路径
   */
  private String path;

  /**
   * 查询字符串 ?xxx=
   */
  private String query;

  /**
   * 片段|锚点 #xxx
   */
  private String fragment;

  /**
   * HTTP请求方法
   * GET,
   * HEAD,
   * POST,
   * PUT,
   * PATCH,
   * DELETE,
   * OPTIONS,
   * TRACE
   */
  private String method;

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getFragment() {
    return fragment;
  }

  public void setFragment(String fragment) {
    this.fragment = fragment;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }
}
