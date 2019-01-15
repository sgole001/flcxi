package flcxilove.common.exception;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.Charset;

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

    public String getMessage(final String messageSourceBaseName) {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(-1);
        messageSource.setDefaultEncoding(Charset.forName("UTF-8").name());
        messageSource.setBasenames(messageSourceBaseName);

        try {
            return messageSource.getMessage(this.errorCode, this.params, LocaleContextHolder.getLocale());
        } catch (Exception e) {
            // 消息转换失败的时候，直接返回消息编码
        }

        return this.errorCode;
    }
}
