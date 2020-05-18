package flcxilove.common.tools;


import flcxilove.common.beans.JwtPayload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

/**
 * JWT工具类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-25 17:50
 */
@Component
public class JwtUtil {

  /**
   * 创建JWT
   *
   * @param payload jwt创建条件信息
   * @param key 签名用密钥
   *
   * @return jwt
   */
  public String createJWT(JwtPayload payload, SecretKeySpec key) {

    // 签名加密算法(HMAC-SHA256)
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    // JWT构建对象
    JwtBuilder builder = Jwts.builder();

    // JWT签发者
    if (StringUtils.isNotBlank(payload.getIss())) {
      builder.setIssuer(payload.getIss());
    }
    // JWT所面向的用户
    builder.setSubject(payload.getSub());
    // 接收JWT的一方
    builder.setAudience(payload.getAud());
    // JWT的签发时间
    Date issuedAt = new Date();
    if (payload.getIat() != null) {
      builder.setIssuedAt(payload.getIat());
    }
    builder.setIssuedAt(issuedAt);
    // JWT的过期时间，这个过期时间必须要大于签发时间
    if (payload.getExp() != null) {
      builder.setExpiration(DateUtils.addMilliseconds(issuedAt, payload.getExp().intValue()));
    }
    // 定义在什么时间之前，该JWT都是不可用的
    if (payload.getNbf() != null) {
      builder.setNotBefore(DateUtils.addMilliseconds(issuedAt, payload.getNbf().intValue()));
    }
    // JWT的唯一身份标识(主要用来作为一次性token,从而回避重放攻击)
    // 针对重访攻击，可以使用nonstr，每次请求中添加UUID，在token有效期中缓存所有nonstr，如果有重复，则视为重放攻击
    builder.setId(payload.getId());
    // 额外自定义信息
    if (payload.getCustomClaims() != null) {
      builder.addClaims(payload.getCustomClaims());
    }
    // 签名
    builder.signWith(signatureAlgorithm, key);

    return builder.compact();
  }

  /**
   * 解析JWT
   *
   * @param jwt JWT
   * @param key 解析密钥
   *
   * @return JWT解析后信息
   */
  public Claims parseJWT(String jwt, SecretKeySpec key) {

    return Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
  }
}
