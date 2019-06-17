package com.deloitte.services.fssc.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 支出大类-组织关系
 * </p>
 *
 * @author hjy
 * @since 2019-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_EXPENSE_MAIN_TYPE_UNIT")
@ApiModel(value="BaseExpenseMainTypeUnit对象", description="支出大类-组织关系")
public class BaseExpenseMainTypeUnit extends Model<BaseExpenseMainTypeUnit> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "支出大类ID")
    @TableField("EXPENSE_MAIN_TYPE_ID")
    private Long expenseMainTypeId;

    @ApiModelProperty(value = "组织单位ID")
    @TableField("ORG_UNIT_ID")
    private Long orgUnitId;

    @ApiModelProperty(value = "组织单位编码")
    @TableField("ORG_UNIT_CODE")
    private String orgUnitCode;

    @ApiModelProperty(value = "组织单位名称")
    @TableField("ORG_UNIT_NAME")
    private String orgUnitName;

    @ApiModelProperty(value = "生失效标志")
    @TableField("VALID_FLAG")
    private String validFlag;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    public static final String ID = "ID";

    public static final String EXPENSE_MAIN_TYPE_ID = "EXPENSE_MAIN_TYPE_ID";

    public static final String ORG_UNIT_ID = "ORG_UNIT_ID";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
