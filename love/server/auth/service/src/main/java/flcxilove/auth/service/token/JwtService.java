package flcxilove.auth.service.token;

import flcxilove.auth.api.request.FetchTokenRequestMessage;
import flcxilove.auth.api.response.data.FetchTokenData;

/**
 * JWT算法逻辑服务接口
 *
 * @author: sgole
 * @since: 2018/11/8
 * @version: 1.0
 */
public interface JwtService {

    /**
     * 根据请求方信息生成JWT
     *
     * @param requestMessage 请求方信息
     * @return 生成JWT信息
     */
    FetchTokenData generateJwt(FetchTokenRequestMessage requestMessage);
}
