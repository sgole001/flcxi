package flcxilove.governance.mybatis.bean.entity;

import flcxilove.governance.mybatis.annotation.TableField;
import flcxilove.governance.mybatis.plugin.strategy.impl.datafill.DateFillStrategy;
import flcxilove.governance.mybatis.plugin.strategy.impl.datafill.OperatorFillStrategy;
import flcxilove.governance.mybatis.plugin.strategy.impl.datafill.PkFillStrategy;
import flcxilove.governance.mybatis.plugin.strategy.impl.datafill.VersionFillStrategy;
import java.util.Date;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * 数据库领域共通模型
 *
 * @author sgole
 * @version v1.0
 * @since 2019-03-26 18:32
 */
public class CommonEntity implements Entity {

  /**
   * 数据表物理PK（UUID）
   */
  @TableField(col = "ID", cmd = SqlCommandType.INSERT, handle = PkFillStrategy.class)
  private String id;

  /**
   * 数据创建操作者
   */
  @TableField(col = "CREATED_BY", cmd = SqlCommandType.INSERT, handle = OperatorFillStrategy.class)
  private String created_by;

  /**
   * 数据创建时间
   */
  @TableField(col = "CREATED_AT", cmd = SqlCommandType.INSERT, handle = DateFillStrategy.class)
  private Date created_at;

  /**
   * 数据更新操作者
   */
  @TableField(col = "UPDATED_BY", cmd = SqlCommandType.UPDATE, handle = OperatorFillStrategy.class)
  private String updated_by;

  /**
   * 数据更新时间
   */
  @TableField(col = "UPDATED_AT", cmd = SqlCommandType.UPDATE, handle = DateFillStrategy.class)
  private Date updated_at;

  /**
   * 数据版本（乐观锁）
   */
  @TableField(col = "VERSION", cmd = SqlCommandType.INSERT, handle = VersionFillStrategy.class)
  private Integer version;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCreated_by() {
    return created_by;
  }

  public void setCreated_by(String created_by) {
    this.created_by = created_by;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public String getUpdated_by() {
    return updated_by;
  }

  public void setUpdated_by(String updated_by) {
    this.updated_by = updated_by;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
}
