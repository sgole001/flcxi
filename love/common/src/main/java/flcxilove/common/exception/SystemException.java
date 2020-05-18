package flcxilove.common.exception;

/**
 * 系统异常类
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class SystemException extends BaseException {

  public SystemException(String errorCode, Object... params) {
    super(errorCode, params);
  }

  public SystemException(String errorCode, Throwable cause, Object... params) {
    super(errorCode, cause, params);
  }

  public SystemException(String errorCode, Throwable cause) {
    super(errorCode, cause);
  }

  public SystemException(String errorCode) {
    super(errorCode);
  }

  public SystemException(Throwable cause) {
    super(cause);
  }

}