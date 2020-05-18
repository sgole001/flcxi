package flcxilove.common.tools;

import flcxilove.common.constant.CommonConstant;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Servlet工具类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-26 16:20
 */
public class ServletUtil {

  /**
   * 日志管理器
   */
  private static Logger logger = LogManager.getLogger(ServletUtil.class.getName());

  /**
   * 获取当前HttpServletRequest对象信息
   *
   * @return 当前HttpServletRequest对象信息
   */
  public static HttpServletRequest getHttpServletRequest() {

    // 获取请求属性信息
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    return requestAttributes.getRequest();
  }

  /**
   * 获取当前HttpServletResponse对象信息
   *
   * @return 当前HttpServletResponse对象信息
   */
  public static HttpServletResponse getHttpServletResponse() {

    // 获取请求属性信息
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    return requestAttributes.getResponse();
  }

  /**
   * 获取HTTP请求方法
   *
   * @param request HTTP请求信息
   *
   * @return HTTP请求方法
   */
  public static String getRequestMethod(ServletRequest request) {

    if (request instanceof HttpServletRequest) {
      return ((HttpServletRequest) request).getMethod();
    }

    return null;
  }

  /**
   * 获取请求头部Token信息
   *
   * @return Token信息
   */
  public static String getToken() {

    // 获取当前HttpServletRequest对象信息
    HttpServletRequest servletRequest = getHttpServletRequest();

    return servletRequest.getHeader("jwt");
  }

  /**
   * 获取远程调用IP地址
   *
   * @return 远程调用IP地址
   */
  public static String getRemoteIPAddress() {

    // 获取当前HttpServletRequest对象信息
    HttpServletRequest servletRequest = getHttpServletRequest();

    // 根据X-Forwarded-For（XFF）获取IP地址（通过HTTP代理或负载均衡方式连接）
    String remoteIPAddress = servletRequest.getHeader("x-forwarded-for");

    if (StringUtils.isNotBlank(remoteIPAddress)) {
      // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
      // 格式：X-Forwarded-For: client1, proxy1, proxy2, proxy3
      String[] ips = remoteIPAddress.split(",");
      remoteIPAddress = ips[0];
    }

    // 在apache+WebLogic整合系统中，apache会对request对象进行再包装，附加一些WLS要用的头信息
    // 代理客服端IP
    if (StringUtils.isBlank(remoteIPAddress) || CommonConstant.SQUID_OFF_IP_ADDRESS.equalsIgnoreCase(remoteIPAddress)) {
      remoteIPAddress = servletRequest.getHeader("Proxy-Client-IP");
    }
    // WebLogic代理客服端IP（webLogic设置了才会有这个参数）
    if (StringUtils.isBlank(remoteIPAddress) || CommonConstant.SQUID_OFF_IP_ADDRESS.equalsIgnoreCase(remoteIPAddress)) {
      remoteIPAddress = servletRequest.getHeader("WL-Proxy-Client-IP");
    }

    // 没有代理的情况
    if (StringUtils.isBlank(remoteIPAddress) || CommonConstant.SQUID_OFF_IP_ADDRESS.equalsIgnoreCase(remoteIPAddress)) {
      remoteIPAddress = servletRequest.getRemoteAddr();

      if (CommonConstant.LOOPBACK_IP_ADDRESS.equalsIgnoreCase(remoteIPAddress)) {
        try {
          // 根据网卡取本机配置的IP
          remoteIPAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
          logger.error(e);
        }
      }
    }

    return remoteIPAddress;
  }
}
