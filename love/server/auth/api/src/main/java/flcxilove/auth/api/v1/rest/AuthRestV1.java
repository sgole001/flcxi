package flcxilove.auth.api.v1.rest;

import flcxilove.common.api.RestApi;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import flcxilove.auth.api.v1.request.ApplyTokenRequestMessage;
import flcxilove.auth.api.v1.response.ApplyTokenResponseMessage;
import flcxilove.auth.api.v1.response.data.TokenData;

/**
 * 认证API
 *
 * @author: sgole
 * @since: 2018/11/7
 * @version: 1.0
 */
public interface AuthRestV1 extends RestApi {

  /**
   * 申请令牌
   *
   * @param requestMessage 请求消息
   *
   * @return 响应消息::令牌数据
   *
   * @throws Exception
   */
  @RequestMapping(value = "/auth/token", method = RequestMethod.POST, produces = "application/vnd.api.v1+json")
  ApplyTokenResponseMessage<TokenData> applyToken(@RequestBody final ApplyTokenRequestMessage requestMessage) throws Exception;
}
