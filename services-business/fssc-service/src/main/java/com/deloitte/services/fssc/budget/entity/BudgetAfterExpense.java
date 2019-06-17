package com.deloitte.services.fssc.budget.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 预算-事后报账
 * </p>
 *
 * @author jaws
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BUDGET_AFTER_EXPENSE")
@ApiModel(value="BudgetAfterExpense对象", description="预算-事后报账")
public class BudgetAfterExpense extends Model<BudgetAfterExpense> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单据ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "单据状态")
    @TableField("DOCUMENT_STATUS")
    private String documentStatus;

    @ApiModelProperty(value = "报销金额")
    @TableField("EXPENSE_AMOUNT")
    private BigDecimal expenseAmount;

    @ApiModelProperty(value = "关联事前申请ID")
    @TableField("ADVANCE_APPLY_FOR_ID")
    private Long advanceApplyForId;

    @ApiModelProperty(value = "使用事前申请保留金额")
    @TableField("USE_APPLY_FOR_AMOUNT")
    private BigDecimal useApplyForAmount;

    @ApiModelProperty(value = "预算类型")
    @TableField("BUDGET_TYPE")
    private String budgetType;

    @ApiModelProperty(value = "支出大类ID")
    @TableField("MAIN_TYPE_ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "基本预算科目ID")
    @TableField("BASIC_BUDGET_ACCOUNT_ID")
    private Long basicBudgetAccountId;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "项目预算科目ID")
    @TableField("PROJECT_BUDGET_ACCOUNT_ID")
    private Long projectBudgetAccountId;

    @ApiModelProperty(value = "预算保留金额")
    @TableField("BUDGET_REMAIN_AMOUNT")
    private BigDecimal budgetRemainAmount;

    @ApiModelProperty(value = "预算占用金额")
    @TableField("BUDGET_OCCUPY_AMOUNT")
    private BigDecimal budgetOccupyAmount;

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

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    @TableField("ORG_PATH")
    private String orgPath;


    public static final String ID = "ID";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String DOCUMENT_STATUS = "DOCUMENT_STATUS";

    public static final String EXPENSE_AMOUNT = "EXPENSE_AMOUNT";

    public static final String ADVANCE_APPLY_FOR_ID = "ADVANCE_APPLY_FOR_ID";

    public static final String BUDGET_OCCUPY_AMOUNT = "BUDGET_OCCUPY_AMOUNT";

    public static final String BUDGET_TYPE = "BUDGET_TYPE";

    public static final String MAIN_TYPE_ID = "MAIN_TYPE_ID";

    public static final String BASIC_BUDGET_ACCOUNT_ID = "BASIC_BUDGET_ACCOUNT_ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String PROJECT_BUDGET_ACCOUNT_ID = "PROJECT_BUDGET_ACCOUNT_ID";

    public static final String BUDGET_REMAIN_AMOUNT = "BUDGET_REMAIN_AMOUNT";

    public static final String USABLE_REMAIN_AMOUNT = "USABLE_REMAIN_AMOUNT";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
