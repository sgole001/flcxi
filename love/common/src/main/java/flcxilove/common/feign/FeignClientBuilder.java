package flcxilove.common.feign;

import flcxilove.common.api.RestApi;

/**
 * Feign服务调用客户端接口
 *
 * @author sgole
 * @version v1.0
 * @since 2019-01-22 18:37
 */
public interface FeignClientBuilder {

  /**
   * 构建Feign服务提供者
   *
   * @param serviceName 服务提供者服务名
   *
   * @return Feign服务提供者客户端
   */
  <T extends RestApi> T build(final Class<T> apiClass, final String serviceName);

}
