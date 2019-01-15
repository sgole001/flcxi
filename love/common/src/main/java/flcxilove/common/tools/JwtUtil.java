package flcxilove.common.tools;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import flcxilove.common.beans.JwtBean;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    /**
     * 创建JWT
     *
     * @param jwt jwt创建条件信息
     * @param key 签名用密钥
     * @return jwt
     */
    public String createJWT(JwtBean jwt, SecretKeySpec key) {

        // 签名加密算法(HMAC-SHA256)
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // JWT构建对象
        JwtBuilder builder = Jwts.builder();

        // JWT签发者
        if (StringUtils.isNotBlank(jwt.getIss())) {
            builder.setIssuer(jwt.getIss());
        }
        // JWT所面向的用户
        builder.setSubject(jwt.getSub());
        // 接收JWT的一方
        builder.setAudience(jwt.getAud());
        // JWT的过期时间，这个过期时间必须要大于签发时间
        if (jwt.getExp() != null) {
            builder.setExpiration(jwt.getExp());
        }
        // 定义在什么时间之前，该JWT都是不可用的
        if (jwt.getNbf() != null) {
            builder.setNotBefore(jwt.getNbf());
        }
        // JWT的签发时间
        if (jwt.getIat() != null) {
            builder.setIssuedAt(jwt.getIat());
        } else {
            builder.setIssuedAt(new Date());
        }
        // JWT的唯一身份标识(主要用来作为一次性token,从而回避重放攻击)
        builder.setId(jwt.getId());
        // 签名
        builder.signWith(signatureAlgorithm, key);

        return builder.compact();
    }

    public Claims parseJWT(String jwt, SecretKeySpec key) {

        return Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
    }
}
