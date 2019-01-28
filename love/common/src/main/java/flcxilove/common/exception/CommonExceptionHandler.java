package flcxilove.common.exception;

import flcxilove.common.constant.CommonMessageConstant;
import flcxilove.common.messages.RestApiMessage;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-01-25 17:16
 */
@ControllerAdvice
public class CommonExceptionHandler {

  @Resource
  private MessageSource messageSource;

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(CommonExceptionHandler.class.getName());

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public RestApiMessage handle(Exception e) {

    // 响应消息实例
    RestApiMessage message = new RestApiMessage();

    if (e instanceof BaseException) {

      // 异常错误码
      message.setStatus(((BaseException) e).getErrorCode());
      // 异常信息
      message.setMessage(e.getMessage());

      // HTTP状态码
      // 系统异常
      if (e instanceof SystemException) {
        message.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
      }
      // 数据库异常
      else if (e instanceof DBException) {
        message.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
      }
      // 业务异常
      else if (e instanceof BusinessException) {
        message.setHttpStatus(HttpStatus.OK.toString());
      }

      logger.error(e.getMessage());
    } else {

      // 异常错误码
      message.setStatus(CommonMessageConstant.MSG_COM_00001);
      // 异常信息
      message.setMessage(messageSource.getMessage(CommonMessageConstant.MSG_COM_00001, null, LocaleContextHolder.getLocale()));
      // HTTP状态码
      message.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());

      logger.error(e);
    }

    return message;
  }
}
