package flcxilove.common.api;

import java.io.Serializable;

/**
 * RestApi响应消息基类
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class RestResponseMessage<T> implements Serializable {

  /**
   * Http响应返回状态
   */
  private String httpStatus;

  /**
   * 业务响应返回状态
   */
  private String status;

  /**
   * 业务响应返回额外信息
   */
  private String message;

  /**
   * 响应返回数据信息
   */
  private T data;

  public String getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(String httpStatus) {
    this.httpStatus = httpStatus;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
