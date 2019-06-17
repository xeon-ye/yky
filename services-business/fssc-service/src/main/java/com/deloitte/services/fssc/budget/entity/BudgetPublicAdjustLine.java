package com.deloitte.services.fssc.budget.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
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
 *  预算调整-公费调整行表
 * </p>
 *
 * @author jaws
 * @since 2019-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BUDGET_PUBLIC_ADJUST_LINE")
@ApiModel(value="BudgetPublicAdjustLine对象", description="预算调整-公费调整行表")
public class BudgetPublicAdjustLine extends Model<BudgetPublicAdjustLine> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "单据ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "预算科目ID")
    @TableField("BUDGET_ACCOUNT_ID")
    private Long budgetAccountId;

    @ApiModelProperty(value = "预算科目编码")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预算科目名称")
    @TableField(exist = false)
    private String budgetAccountName;

    @ApiModelProperty(value = "当期预算金额")
    @TableField("PERIOD_BUDGET_AMOUNT")
    private BigDecimal periodBudgetAmount;

    @ApiModelProperty(value = "当期已用金额")
    @TableField("PERIOD_USED_AMOUNT")
    private BigDecimal periodUsedAmount;

    @ApiModelProperty(value = "当期可用金额")
    @TableField("PERIOD_USABLE_AMOUNT")
    private BigDecimal periodUsableAmount;

    @ApiModelProperty(value = "调整金额")
    @TableField("ADJUST_AMOUNT")
    private BigDecimal adjustAmount;

    @ApiModelProperty(value = "调整后当期预算金额")
    @TableField("ADJUSTED_PERIOD_BUDGET_AMOUNT")
    private BigDecimal adjustedPeriodBudgetAmount;

    @ApiModelProperty(value = "调整后当期可用金额")
    @TableField("ADJUSTED_PERIOD_USABLE_AMOUNT")
    private BigDecimal adjustedPeriodUsableAmount;

    @ApiModelProperty(value = "说明")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "申请人")
    @TableField("APPLY_FOR_PERSON")
    private String applyForPerson;

    @ApiModelProperty(value = "申请人名称")
    @TableField("APPLY_FOR_PERSON_NAME")
    private String applyForPersonName;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "扩展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    private Long version;


    public static final String ID = "ID";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String BUDGET_ACCOUNT_ID = "BUDGET_ACCOUNT_ID";

    public static final String BUDGET_ACCOUNT_CODE = "BUDGET_ACCOUNT_CODE";

    public static final String PERIOD_BUDGET_AMOUNT = "PERIOD_BUDGET_AMOUNT";

    public static final String PERIOD_USED_AMOUNT = "PERIOD_USED_AMOUNT";

    public static final String PERIOD_USABLE_AMOUNT = "PERIOD_USABLE_AMOUNT";

    public static final String ADJUST_AMOUNT = "ADJUST_AMOUNT";

    public static final String ADJUSTED_PERIOD_BUDGET_AMOUNT = "ADJUSTED_PERIOD_BUDGET_AMOUNT";

    public static final String ADJUSTED_PERIOD_USABLE_AMOUNT = "ADJUSTED_PERIOD_USABLE_AMOUNT";

    public static final String REMARK = "REMARK";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String APPLY_FOR_PERSON = "APPLY_FOR_PERSON";

    public static final String APPLY_FOR_PERSON_NAME = "APPLY_FOR_PERSON_NAME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String VERSION = "VERSION";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
