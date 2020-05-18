package flcxilove.auth.dao.redis.token;

import java.util.concurrent.TimeUnit;
import javax.crypto.spec.SecretKeySpec;

public interface JwtDao {

  /**
   * 保存JWT
   *
   * @param jwt JWT字符串
   * @param keySpec 密钥
   * @param timeout 超时时间
   * @param unit 时间单位
   *
   * @return 处理状态
   */
  int saveJwt(String jwt, SecretKeySpec keySpec, long timeout, TimeUnit unit);
}
