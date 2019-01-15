package flcxilove.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import flcxilove.common.exception.BaseException;
import flcxilove.common.messages.RestApiMessage;

public class RestApiController {

    private MessageSource messageSource;

    /**
     * RestfulAPI处理状态设定
     *
     * @param httpStatus  HTTP状态
     * @param messageCode 消息码
     * @param params      消息动态参数
     * @return 响应数据接口
     */
    protected <E, T extends RestApiMessage<E>> T processResult(final HttpStatus httpStatus, final String messageCode, final Object[] params, final Class<T> rescls, E data) {

        try {
            // 响应数据接口
//            final T responseDate = rescls.newInstance(); // JDK9以后被废弃
            final T responseDate = rescls.getDeclaredConstructor().newInstance();

            // HTTP响应返回状态
            responseDate.setHttpStatus(String.valueOf(httpStatus.value()));
            // 业务响应返回状态
            responseDate.setStatus(messageCode);
            // 响应返回额外信息
            responseDate.setMessage(messageSource.getMessage(messageCode, params, LocaleContextHolder.getLocale()));
            // 响应返回主体数据信息
            responseDate.setData(data);

            return responseDate;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * RestfulAPI处理状态设定
     *
     * @param httpStatus HTTP状态
     * @param ex         异常消息
     * @return 响应数据接口
     */
    protected <E, T extends RestApiMessage<E>> T processResult(final HttpStatus httpStatus, final BaseException ex, final Class<T> rescls) {

        try {
            // 响应数据接口
            final T responseDate = rescls.getDeclaredConstructor().newInstance();

            // HTTP响应返回状态
            responseDate.setHttpStatus(String.valueOf(httpStatus.value()));
            // 业务响应返回状态
            responseDate.setStatus(ex.getErrorCode());
            // 响应返回额外信息
            responseDate.setMessage(ex.getMessage());

            return responseDate;
        } catch (Exception e) {
            return null;
        }
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    @Qualifier(value = "messageSource")
    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
