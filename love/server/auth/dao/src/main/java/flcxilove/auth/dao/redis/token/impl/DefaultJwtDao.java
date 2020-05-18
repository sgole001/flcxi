package flcxilove.auth.dao.redis.token.impl;

import flcxilove.auth.dao.redis.token.JwtDao;
import flcxilove.common.constant.CommonConstant;
import flcxilove.governance.redis.tools.RedisUtil;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;


@Service
public class DefaultJwtDao implements JwtDao {

  @Resource
  private RedisUtil redisUtil;

  @Override
  public int saveJwt(String jwt, SecretKeySpec keySpec, long timeout, TimeUnit unit) {

    // 服务器端密钥保存
    redisUtil.set(jwt, keySpec);
    // 指定失效时间
    redisUtil.expire(jwt, timeout, unit);

    return CommonConstant.SUCCESS;
  }
}
