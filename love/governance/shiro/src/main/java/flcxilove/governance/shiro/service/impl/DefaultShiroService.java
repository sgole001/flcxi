package flcxilove.governance.shiro.service.impl;

import flcxilove.common.tools.MessageUtil;
import flcxilove.governance.shiro.constant.ShiroConstant;
import flcxilove.governance.shiro.dao.entity.ShiroFilterChain;
import flcxilove.governance.shiro.dao.mapper.ShiroFilterChainMapper;
import flcxilove.governance.shiro.filter.JwtAuth2Filter;
import flcxilove.governance.shiro.service.ShiroService;
import java.util.List;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Shiro相关逻辑服务实现类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-19 16:03
 */
@Service(value = "shiroService")
public class DefaultShiroService implements ShiroService {

  @Resource
  private ShiroFilterChainMapper shiroFilterChainMapper;

  /**
   * 日志管理器
   */
  private Logger logger = LogManager.getLogger(JwtAuth2Filter.class.getName());

  @Override
  public List<ShiroFilterChain> findShiroFilterChain() {

    // 查询Shiro过滤规则信息
    List<ShiroFilterChain> shiroFilterChains = this.shiroFilterChainMapper.selectShiroFilterChain();

    if (CollectionUtils.isEmpty(shiroFilterChains)) {
      logger.warn(MessageUtil.accessor.getMessage(ShiroConstant.MSG_SHIRO_BIZ_00000));
    }

    return shiroFilterChains;
  }
}
