package flcxilove.common.feign.impl;

import feign.Feign;
import flcxilove.common.api.RestApi;
import flcxilove.common.exception.SystemException;
import flcxilove.common.feign.FeignClientBuilder;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

/**
 * Feign服务调用客户端默认实现类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-01-22 18:37
 */
@Component(value = "feignClientBuilder")
public class DefaultFeignClientBuilder implements FeignClientBuilder {

  @Resource(name = "feignBuilder")
  private Feign.Builder builder;

  @Resource
  private LoadBalancerClient loadBalancerClient;

  @Override
  public <T extends RestApi> T build(Class<T> apiClass, String serviceName) {

    // 负载均衡发现注册服务实例
    ServiceInstance serviceInstance = loadBalancerClient.choose(serviceName);

    if (serviceInstance == null) {
      throw new SystemException("没有发现服务！");
    }
    // 构建服务提供方BaseUrl
    String baseUrl = serviceInstance.getUri().toString();
    String contextPath = serviceInstance.getMetadata().get("management.context-path");

    if (StringUtils.isNotBlank(contextPath)) {
      baseUrl = baseUrl.concat(contextPath);
    }

    return builder.target(apiClass, baseUrl);
  }
}
