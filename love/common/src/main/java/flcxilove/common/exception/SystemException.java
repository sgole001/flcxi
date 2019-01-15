package flcxilove.common.exception;

/**
 * 系统异常类
 *
 * @author: sgole
 * @since: 2018/11/6
 * @version: 1.0
 */
public class SystemException extends BaseException {

    public SystemException(String errorCode, Object[] params) {
        super(errorCode, params);
    }

    public SystemException(String errorCode, Object[] params, Throwable cause) {
        super(errorCode, params, cause);
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

    @Override
    public String getMessage() {

        return super.getMessage("/i18n/system/messages");
    }
}