package flcxilove.governance.mybatis.plugin;

import java.util.Map;
import java.util.Properties;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * 乐观锁插件
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-08 09:47
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class OptimisticLockerInterceptor implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {

    // 获取Executor#update参数信息
    Object[] args = invocation.getArgs();

    // 参数[MappedStatement]
    MappedStatement mappedStatement = (MappedStatement) args[0];
    // 参数[Object]
    Object param = args[1];

    // SQL执行类型非Update的时候跳过，继续执行后续任务
    if (SqlCommandType.UPDATE != mappedStatement.getSqlCommandType()) {
      return invocation.proceed();
    }

    if (param instanceof Map) {

      // 获取参数映射
      Map map = (Map) param;
    }

    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target) {
    if (target instanceof Executor) {
      return Plugin.wrap(target, this);
    }
    return target;
  }

  @Override
  public void setProperties(Properties properties) {
    // 不做任何操作
  }
}
