package flcxilove.common.exception;

import flcxilove.common.tools.MessageUtil;

public class BaseException extends RuntimeException {

  /**
   * 错误码
   */
  protected String errorCode;

  /**
   * 错误信息动态参数
   */
  protected Object[] params;

  public BaseException(String errorCode, Object[] params) {
    super("");
    this.errorCode = errorCode;
    this.params = params;
  }

  public BaseException(String errorCode, Object[] params, Throwable cause) {
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
