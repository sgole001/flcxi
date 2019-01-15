package flcxilove.auth.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import flcxilove.auth.api.request.FetchTokenRequestMessage;
import flcxilove.auth.api.response.FetchTokenResponseMessage;
import flcxilove.auth.api.response.data.FetchTokenData;
import flcxilove.auth.api.rest.AuthRest;
import flcxilove.auth.config.message.MessageConstant;
import flcxilove.auth.service.token.JwtService;
import flcxilove.common.controller.RestApiController;
import flcxilove.common.exception.BaseException;

@RestController
public class AuthRestController extends RestApiController implements AuthRest {

    @Resource(name = "jwtService")
    private JwtService jwtService;

    @Override
    public FetchTokenResponseMessage<FetchTokenData> accessTokenV101(FetchTokenRequestMessage requestMessage) throws Exception {

        try {

            // 根据请求方信息生成JWT
            FetchTokenData tokenData = jwtService.generateJwt(requestMessage);

            return super.processResult(HttpStatus.OK, MessageConstant.MSG_SYS_00000, null, FetchTokenResponseMessage.class, tokenData);

        } catch (BaseException e) {
            return super.processResult(HttpStatus.INTERNAL_SERVER_ERROR, e, FetchTokenResponseMessage.class);
        }
    }

    @Override
    public FetchTokenResponseMessage<FetchTokenData> accessTokenV102(FetchTokenRequestMessage requestMessage) throws Exception {
        try {

            // 根据请求方信息生成JWT
            FetchTokenData tokenData = jwtService.generateJwt(requestMessage);

            return super.processResult(HttpStatus.OK, MessageConstant.MSG_SYS_00001, null, FetchTokenResponseMessage.class, tokenData);

        } catch (BaseException e) {
            return super.processResult(HttpStatus.INTERNAL_SERVER_ERROR, e, FetchTokenResponseMessage.class);
        }
    }
}
