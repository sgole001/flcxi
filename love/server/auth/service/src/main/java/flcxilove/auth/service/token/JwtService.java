package flcxilove.auth.service.token;

import flcxilove.auth.api.v1.response.data.TokenData;
import flcxilove.auth.service.dto.AuthDto;

/**
 * JWT算法逻辑服务接口
 *
 * @author: sgole
 * @since: 2018/11/8
 * @version: 1.0
 */
public interface JwtService {

  /**
   * 申请访问令牌
   *
   * @param authDto 令牌申请额外信息
   *
   * @return 访问令牌信息
   */
  TokenData applyToken(AuthDto authDto);

  /**
   * 生成资源访问令牌JWT
   *
   * @param authDto 令牌申请额外信息
   *
   * @return 资源访问令牌JWT
   */
  String generateAccessToken(AuthDto authDto);

  /**
   * 生成刷新资源访问令牌JWT
   *
   * @param authDto 令牌申请额外信息
   *
   * @return 刷新资源访问令牌JWT
   */
  String generateRefreshToken(AuthDto authDto);

  /**
   * 刷新资源访问令牌JWT
   *
   * @param refreshToken 刷新资源访问令牌JWT
   *
   * @return 资源访问令牌JWT
   */
  String refreshAccessToken(String refreshToken);
}
