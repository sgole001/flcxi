package flcxilove.auth.service.token.impl;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;

import flcxilove.auth.api.request.FetchTokenRequestMessage;
import flcxilove.auth.api.response.data.FetchTokenData;
import flcxilove.auth.config.message.MessageConstant;
import flcxilove.auth.dao.redis.token.JwtDao;
import flcxilove.auth.service.token.JwtService;
import flcxilove.common.beans.JwtBean;
import flcxilove.common.exception.BusinessException;
import flcxilove.common.tools.CryptoUtil;
import flcxilove.common.tools.JwtUtil;

/**
 * JWT算法逻辑服务实现类
 *
 * @author: sgole
 * @since: 2018/11/8
 * @version: 1.0
 */
@Service("jwtService")
public class DefaultJwtService implements JwtService {

    @Resource(name = "AES256Util")
    private CryptoUtil cryptoUtil;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private JwtDao jwtDao;

    @Override
    public FetchTokenData generateJwt(FetchTokenRequestMessage requestMessage) {

        // 消息主体：获取JWT
        FetchTokenData tokenData = new FetchTokenData();

        try {

            // 生成对称密钥
            SecretKeySpec keySpec = cryptoUtil.generateKey(requestMessage.getSecretKey());

            // 生成JWT
            String jwt = jwtUtil.createJWT(this.buildJwtBean(requestMessage), keySpec);

            // 持久化JWT
            jwtDao.saveJwt(jwt, keySpec);

            // 构建消息主体：获取JWT
            return this.buildTokenData(jwt);

        } catch (Exception e) {
            throw new BusinessException(MessageConstant.MSG_BIZ_00001, e);
        }
    }

    /**
     * 构建消息主体：获取JWT
     *
     * @param jwt JWT
     * @return 消息主体：获取JWT
     */
    private FetchTokenData buildTokenData(String jwt) {

        // 消息主体：获取JWT
        FetchTokenData tokenData = new FetchTokenData();

        // JWT
        tokenData.setJwt(jwt);

        return tokenData;
    }

    /**
     * 构建JWT信息Bean
     *
     * @param requestMessage API请求信息
     * @return JWT信息Bean
     */
    private JwtBean buildJwtBean(FetchTokenRequestMessage requestMessage) {

        // JWT信息Bean
        JwtBean jwtBean = new JwtBean();

        // JWT签发者
        jwtBean.setIss("msscn");
        // JWT所面向的用户
        jwtBean.setSub("获取登陆令牌");
        // 接收JWT的一方
        jwtBean.setAud(requestMessage.getClientId());
        // JWT的过期时间，这个过期时间必须要大于签发时间
        jwtBean.setExp(DateUtils.addDays(new Date(), 7));
        // 定义在什么时间之前，该JWT都是不可用的
        jwtBean.setNbf(DateUtils.addDays(new Date(), -7));
        // JWT的签发时间
        jwtBean.setIat(new Date());
        // JWT的唯一身份标识(主要用来作为一次性token,从而回避重放攻击)
        jwtBean.setId(UUID.randomUUID().toString());

        return jwtBean;
    }
}
