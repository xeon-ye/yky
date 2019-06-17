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
 * 支出小类
 * </p>
 *
 * @author hjy
 * @since 2019-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_EXPENSE_SUB_TYPE")
@ApiModel(value="支出小类对象", description="支出小类")
public class BaseExpenseSubType extends Model<BaseExpenseSubType> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "编码")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "支出大类ID")
    @TableField("EXPENSE_MAIN_TYPE_ID")
    private Long expenseMainTypeId;

    @ApiModelProperty(value = "财-会计科目编码")
    @TableField("C_ACCOUNT_CODE")
    private String cAccountCode;

    @ApiModelProperty(value = "预-会计科目编码")
    @TableField("Y_ACCOUNT_CODE")
    private String yAccountCode;

    @ApiModelProperty(value = "是否有效")
    @TableField("VALID_FLAG")
    private String validFlag;

    @ApiModelProperty(value = "生效日期")
    @TableField("VALID_DATE")
    private LocalDateTime validDate;

    @ApiModelProperty(value = "失效日期")
    @TableField("INVALID_DATE")
    private LocalDateTime invalidDate;

    @ApiModelProperty(value = "单位编码")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "费用类型")
    @TableField("COST_TYPE")
    private String costType;

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

    public static final String CODE = "CODE";

    public static final String NAME = "NAME";

    public static final String EXPENSE_MAIN_TYPE_ID = "EXPENSE_MAIN_TYPE_ID";

    public static final String C_ACCOUNT_CODE = "C_ACCOUNT_CODE";

    public static final String Y_ACCOUNT_CODE = "Y_ACCOUNT_CODE";

    public static final String VALID_FLAG = "VALID_FLAG";

    public static final String VALID_DATE = "VALID_DATE";

    public static final String INVALID_DATE = "INVALID_DATE";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
