package flcxilove.governance.mybatis.plugin.strategy.impl;

import flcxilove.governance.mybatis.bean.CommonDbFieldFillStrategy;
import flcxilove.governance.mybatis.plugin.factory.ComFieldStrategyFactory;
import flcxilove.governance.mybatis.plugin.factory.impl.DefaultComFieldStrategyFactory;
import flcxilove.governance.mybatis.plugin.strategy.ComFieldStrategy;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

/**
 * 数据库共通字段处理上下文
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-15 11:58
 */
public class ComFieldContext {

  /**
   * 策略工厂接口
   */
  private ComFieldStrategyFactory strategyFactory;

  /**
   * 共通字段改造处理
   *
   * @param invocation Mybatis调用接口对象
   *
   * @return 改造后SQL信息对象
   */
  public BoundSql reform(Invocation invocation) {

    // 拦截对象获取
    StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
    // 获取拦截对象元对象（未考虑多层代理）
    MetaObject metaObject = SystemMetaObject.forObject(invocation.getTarget());
    // 获取MappedStatement
    MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
    // 参数处理对象获取
    ParameterHandler parameterHandler = statementHandler.getParameterHandler();
    // 改造前参数对象获取
    Object originalParameterObject = parameterHandler.getParameterObject();

    // 获取策略
    ComFieldStrategy comFieldStrategy = this.getStrategyFactory().createStrategy(mappedStatement.getSqlCommandType());

    return Optional.ofNullable(comFieldStrategy).map(strategy -> strategy.reform(mappedStatement, originalParameterObject)).orElse(null);
  }

  public ComFieldStrategyFactory getStrategyFactory() {

    // 没有配置的情况下，默认策略工厂设定
    if (this.strategyFactory == null) {
      this.strategyFactory = new DefaultComFieldStrategyFactory();
    }

    return this.strategyFactory;
  }

  public void setStrategyFactory(ComFieldStrategyFactory strategyFactory) {
    this.strategyFactory = strategyFactory;
  }
}
