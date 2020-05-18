package flcxilove.governance.mybatis.bean;

/**
 * 共通数据字段填充策略信息Bean
 *
 * @author sgole
 * @version v1.0
 * @since 2019-04-16 11:44
 */
public class CommonDbFieldFillStrategy {

  /**
   * 填充字段对应数据对象属性名
   */
  private String name;

  /**
   * 填充字段对应数据库列名
   */
  private String column;

  /**
   * 填充字段动态数据处理类路径
   * <p>优先级高于常量设定值</p>
   */
  private Class<?> handleCls;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColumn() {
    return column;
  }

  public void setColumn(String column) {
    this.column = column;
  }

  public Class<?> getHandleCls() {
    return handleCls;
  }

  public void setHandleCls(Class<?> handleCls) {
    this.handleCls = handleCls;
  }
}
