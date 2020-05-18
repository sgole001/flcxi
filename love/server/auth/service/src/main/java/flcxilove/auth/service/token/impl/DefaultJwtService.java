package flcxilove.auth.service.token.impl;

import flcxilove.auth.api.v1.response.data.TokenData;
import flcxilove.auth.config.message.MessageConstant;
import flcxilove.auth.dao.redis.token.JwtDao;
import flcxilove.auth.service.dto.AuthDto;
import flcxilove.auth.service.token.JwtService;
import flcxilove.common.beans.JwtPayload;
import flcxilove.common.exception.BusinessException;
import flcxilove.common.tools.CryptoUtil;
import flcxilove.common.tools.JwtUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * JWT算法逻辑服务实现类
 *
 * @author: sgole
 * @since: 2018/11/8
 * @version: 1.0
 */
@Service("jwtService")
public class DefaultJwtService implements JwtService {

  @Resource(name = "cryptoUtilForAes256")
  private CryptoUtil cryptoUtil;

  @Resource
  private JwtUtil jwtUtil;

  @Resource
  private JwtDao jwtDao;

  @Resource(name = "accessTokenPayload")
  private JwtPayload accessTokenPayload;

  @Resource(name = "refreshTokenPayload")
  private JwtPayload refreshTokenPayload;

  @Override
  public TokenData applyToken(AuthDto authDto) {

    // 申请令牌信息
    TokenData tokenData = new TokenData();

    // 生成资源访问令牌JWT
    tokenData.setAccessToken(this.generateAccessToken(authDto));
    // 生成刷新资源访问令牌用令牌JWT
    tokenData.setRefreshToken(this.generateRefreshToken(authDto));

    return tokenData;
  }

  @Override
  public String generateAccessToken(AuthDto authDto) {

    try {
      // 构建JWT负载动态信息和自定义信息
      this.buildDynamicJwyPayLoad(authDto, this.accessTokenPayload);
      // 生成对称密钥
      SecretKeySpec keySpec = cryptoUtil.generateKey(authDto.getSecretKey());

      // 创建资源访问令牌JWT
      String accessToken = jwtUtil.createJWT(this.accessTokenPayload, keySpec);

      // 持久化JWT
      jwtDao.saveJwt(accessToken, keySpec, this.accessTokenPayload.getExp(), TimeUnit.MILLISECONDS);

      return accessToken;

    } catch (Exception e) {
      throw new BusinessException(MessageConstant.MSG_BIZ_00001, e);
    }
  }

  @Override
  public String generateRefreshToken(AuthDto authDto) {

    try {
      // 构建JWT负载动态信息和自定义信息
      this.buildDynamicJwyPayLoad(authDto, this.refreshTokenPayload);
      // 生成对称密钥
      SecretKeySpec keySpec = cryptoUtil.generateKey(authDto.getSecretKey());

      // 创建刷新资源访问令牌的令牌JWT
      String refreshToken = jwtUtil.createJWT(this.refreshTokenPayload, keySpec);

      // 持久化JWT
      jwtDao.saveJwt(refreshToken, keySpec, this.refreshTokenPayload.getExp(), TimeUnit.MILLISECONDS);

      return refreshToken;

    } catch (Exception e) {
      throw new BusinessException(MessageConstant.MSG_BIZ_00001, e);
    }
  }

  @Override
  public String refreshAccessToken(String refreshToken) {
    return null;
  }

  /**
   * 构建JWT负载动态信息和自定义信息
   *
   * @param authDto 令牌申请数据DTO
   * @param jwtPayload JWT负载
   */
  private void buildDynamicJwyPayLoad(AuthDto authDto, JwtPayload jwtPayload) {

    // 构建JWT负载动态信息和自定义信息
    // JWT所面向的用户
    jwtPayload.setSub(authDto.getUid());
    // 接收JWT的一方
    jwtPayload.setAud(authDto.getSource());
    // JWT的签发时间
    if (jwtPayload.getIat() == null) {
      jwtPayload.setIat(new Date());
    }
    // JWT的唯一身份标识(主要用来作为一次性token,从而回避重放攻击)
    if (StringUtils.isBlank(jwtPayload.getId())) {
      jwtPayload.setId(UUID.randomUUID().toString());
    }

    // 自定义信息
    Map<String, Object> customClaims = new HashMap<>(1);
    // 用户角色
    if (!CollectionUtils.isEmpty(authDto.getRoles())) {
      customClaims.put("sub-role", authDto.getRoles());
    }
    jwtPayload.setCustomClaims(customClaims);
  }
}
