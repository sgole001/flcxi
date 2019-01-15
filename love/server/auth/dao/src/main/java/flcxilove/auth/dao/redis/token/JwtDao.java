package flcxilove.auth.dao.redis.token;

import javax.crypto.spec.SecretKeySpec;

public interface JwtDao {

    /**
     * 保存JWT
     *
     * @param jwt     JWT字符串
     * @param keySpec 密钥
     * @return 处理状态
     */
    int saveJwt(String jwt, SecretKeySpec keySpec);
}
