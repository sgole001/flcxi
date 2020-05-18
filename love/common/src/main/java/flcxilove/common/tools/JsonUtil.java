package flcxilove.common.tools;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

/**
 * JSON数据解析与转换工具类
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-29 15:21
 */
public class JsonUtil {

  /**
   * 解析JSON字符串转换为POJO对象
   *
   * @param jsonStr JSON字符串
   * @param type POJO对象类型
   * @param <T> 对象类型范型
   *
   * @return JSON转换后POJO对象
   */
  public static <T> T parse(final String jsonStr, final Class<T> type) {

    // 解析Json字符串转换为POJO
    // PS: 使用Ali的fastjson，性能考虑优与Gson
    final T result = JSON.parseObject(jsonStr, type);

    return result;
  }

  /**
   * 解析JSON字符串转换为POJO对象数组
   *
   * @param jsonStr JSON字符串
   * @param type POJO对象类型
   * @param <T> 对象类型范型
   *
   * @return JSON转换后POJO对象数组
   */
  public static <T> List<T> parseArray(final String jsonStr, final Class<T> type) {

    // 解析Json字符串转换为POJO
    // PS: 使用Ali的fastjson，性能考虑优与Gson
    final List<T> result = JSON.parseArray(jsonStr, type);

    return result;
  }

  /**
   * POJO对象转换成JSON字符串
   *
   * @param object POJO对象
   * @param <T> 对象类型范型
   *
   * @return 转换后JSON字符串
   */
  public static <T> String toString(final T object) {

    // Google工具类
    final Gson gson = new Gson();

    // POJO对象转换成JSON字符串
    final String result = gson.toJson(object);

    return result;
  }

  /**
   * POJO对象转换成JSON字符串
   *
   * @param object POJO对象
   * @param <T> 对象类型范型
   *
   * @return 转换后JSON字符串
   */
  public static <T> String toStringDisableHtmlEscaping(final T object) {

    // Google工具类
    final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    // POJO对象转换成JSON字符串
    final String result = gson.toJson(object);

    return result;
  }
}
