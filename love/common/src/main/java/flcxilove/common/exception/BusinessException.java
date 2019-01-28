package flcxilove.common.exception;

/**
 * 业务异常类
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class BusinessException extends BaseException {

  public BusinessException(String errorCode, Object[] params) {
    super(errorCode, params);
  }

  public BusinessException(String errorCode, Object[] params, Throwable cause) {
    super(errorCode, params, cause);
  }

  public BusinessException(String errorCode, Throwable cause) {
    super(errorCode, cause);
  }

  public BusinessException(String errorCode) {
    super(errorCode);
  }

  public BusinessException(Throwable cause) {
    super(cause);
  }
}
