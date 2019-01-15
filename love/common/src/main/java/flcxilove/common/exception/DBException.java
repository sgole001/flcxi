package flcxilove.common.exception;

/**
 * 数据库异常类
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class DBException extends BaseException {

    public DBException(String errorCode, Object[] params) {
        super(errorCode, params);
    }

    public DBException(String errorCode, Object[] params, Throwable cause) {
        super(errorCode, params, cause);
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

    @Override
    public String getMessage() {

        return super.getMessage("/i18n/database/messages");
    }
}
