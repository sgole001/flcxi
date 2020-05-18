package flcxilove.common.exception;

/**
 * 数据库异常类
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class DBException extends BaseException {

  public DBException(String errorCode, Object... params) {
    super(errorCode, params);
  }

  public DBException(String errorCode, Throwable cause, Object... params) {
    super(errorCode, cause, params);
  }

  public DBException(String errorCode, Throwable cause) {
    super(errorCode, cause);
  }

  public DBException(String errorCode) {
    super(errorCode);
  }

  public DBException(Throwable cause) {
    super(cause);
  }
}
