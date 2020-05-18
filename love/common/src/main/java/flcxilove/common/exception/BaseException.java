package flcxilove.common.exception;

import flcxilove.common.tools.MessageUtil;

/**
 * 异常类基类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-15 21:18
 */
public class BaseException extends RuntimeException {

  /**
   * 错误码
   */
  protected String errorCode;

  /**
   * 错误信息动态参数
   */
  protected Object[] params;

  public BaseException(String errorCode, Object... params) {
    super("");
    this.errorCode = errorCode;
    this.params = params;
  }

  public BaseException(String errorCode, Throwable cause, Object... params) {
    super(cause);
    this.errorCode = errorCode;
    this.params = params;
  }

  public BaseException(String errorCode, Throwable cause) {
    super("", cause);
    this.errorCode = errorCode;
  }

  public BaseException(String errorCode) {
    super("");
    this.errorCode = errorCode;
  }

  public BaseException(Throwable cause) {
    super(cause);
  }

  public String getErrorCode() {
    return this.errorCode;
  }

  @Override
  public String getMessage() {

    return MessageUtil.accessor.getMessage(this.errorCode, this.params);
  }
}
