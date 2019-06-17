package com.deloitte.services.fssc.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 支出小类-组织关系
 * </p>
 *
 * @author hjy
 * @since 2019-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_EXPENSE_SUB_TYPE_UNIT")
@ApiModel(value="BaseExpenseSubTypeUnit对象", description="支出小类-组织关系")
public class BaseExpenseSubTypeUnit extends Model<BaseExpenseSubTypeUnit> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "组织单位ID")
    @TableField("ORG_UNIT_ID")
    private Long orgUnitId;

    @ApiModelProperty(value = "支出小类ID")
    @TableField("EXPENSE_SUB_TYPE_ID")
    private Long expenseSubTypeId;

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

    @ApiModelProperty(value = "组织单位名称")
    @TableField("ORG_UNIT_NAME")
    private String orgUnitName;

    @TableField("VALID_FLAG")
    private String validFlag;

    @ApiModelProperty(value = "组织单位编码")
    @TableField("ORG_UNIT_CODE")
    private String orgUnitCode;


    public static final String ID = "ID";

    public static final String ORG_UNIT_ID = "ORG_UNIT_ID";

    public static final String EXPENSE_SUB_TYPE_ID = "EXPENSE_SUB_TYPE_ID";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String ORG_UNIT_NAME = "ORG_UNIT_NAME";

    public static final String VALID_FLAG = "VALID_FLAG";

    public static final String ORG_UNIT_CODE = "ORG_UNIT_CODE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
