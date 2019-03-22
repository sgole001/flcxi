package flcxilove.auth.dao.redis.token.impl;

import flcxilove.governance.redis.tools.RedisUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;

import flcxilove.auth.dao.redis.token.JwtDao;
import flcxilove.common.constant.CommonConstant;


@Service
public class DefaultJwtDao implements JwtDao {

  @Resource
  private RedisUtil redisUtil;

  @Override
  public int saveJwt(String jwt, SecretKeySpec keySpec) {

    // 服务器端密钥保存
    redisUtil.set(jwt, keySpec);
    // 指定失效时间
    redisUtil.expire(jwt, 5 * 60 * 1000, TimeUnit.MILLISECONDS);

    return CommonConstant.SUCCESS;
  }
}
