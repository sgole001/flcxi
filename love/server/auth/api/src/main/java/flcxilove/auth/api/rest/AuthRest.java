package flcxilove.auth.api.rest;

import flcxilove.common.api.RestApi;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import flcxilove.auth.api.request.FetchTokenRequestMessage;
import flcxilove.auth.api.response.FetchTokenResponseMessage;
import flcxilove.auth.api.response.data.FetchTokenData;

/**
 * 认证API
 *
 * @author: sgole
 * @since: 2018/11/7
 * @version: 1.0
 */
public interface AuthRest extends RestApi {

    /**
     * 获取JWT
     *
     * @author: sgole
     * @since: 2018/11/6
     * @version: 1.0
     */
    @RequestMapping(value = "/auth/token", method = RequestMethod.POST, produces = "application/vnd.api.v1.0.1+json")
    FetchTokenResponseMessage<FetchTokenData> accessTokenV101(@RequestBody final FetchTokenRequestMessage requestMessage) throws Exception;

    @RequestMapping(value = "/auth/token", method = RequestMethod.POST, produces = "application/vnd.api.v1.0.2+json")
    FetchTokenResponseMessage<FetchTokenData> accessTokenV102(@RequestBody final FetchTokenRequestMessage requestMessage) throws Exception;
}
