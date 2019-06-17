package com.deloitte.services.fssc.budget.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jaws
 * @since 2019-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BUDGET_BASIC_BUDGET")
@ApiModel(value="BudgetBasicBudget对象", description="")
public class BudgetBasicBudget extends Model<BudgetBasicBudget> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单位ID")
    @TableField("ORG_UNIT_ID")
    private Long orgUnitId;

    @ApiModelProperty(value = "单位编码")
    @TableField("ORG_UNIT_CODE")
    private String orgUnitCode;

    @ApiModelProperty(value = "组织路径")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "一级处室")
    @TableField("LEVEL1_OFFICE_CODE")
    private String level1OfficeCode;

    @ApiModelProperty(value = "二级处室")
    @TableField("LEVEL2_OFFICE_CODE")
    private String level2OfficeCode;

    @ApiModelProperty(value = "预算科目")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预算科目ID")
    @TableField("BUDGET_ACCOUNT_ID")
    private Long budgetAccountId;

    @ApiModelProperty(value = "预算期间")
    @TableField("BUDGET_PERIOD")
    private String budgetPeriod;

    @ApiModelProperty(value = "预算年度")
    @TableField("BUDGET_ANNUAL")
    private String budgetAnnual;

    @ApiModelProperty(value = "预算金额")
    @TableField("BUDGET_AMOUNT")
    private BigDecimal budgetAmount;

    @ApiModelProperty(value = "预算保留金额")
    @TableField("BUDGET_REMAIN_AMOUNT")
    private BigDecimal budgetRemainAmount;

    @ApiModelProperty(value = "预算占用金额")
    @TableField("BUDGET_OCCUPY_AMOUNT")
    private BigDecimal budgetOccupyAmount;

    @ApiModelProperty(value = "预算可用金额")
    @TableField("BUDGET_USABLE_AMOUNT")
    private BigDecimal budgetUsableAmount;

    @ApiModelProperty(value = "申请人")
    @TableField("APPLY_FOR_PERSON")
    private String applyForPerson;

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

    @ApiModelProperty(value = "合计标志",notes = "合计标志位Y,是按照年合计的预算数据,才是实际使用的预算控制的数据")
    @TableField("TOTAL_FLAG")
    private String totalFlag;

    public BudgetAmount getAmount(){
        BudgetAmount budgetAmount = new BudgetAmount();
        budgetAmount.setBudgetAmount(this.getBudgetAmount());
        budgetAmount.setBudgetRemainAmount(this.getBudgetRemainAmount());
        budgetAmount.setBudgetOccupyAmount(this.getBudgetOccupyAmount());
        budgetAmount.setBudgetUsableAmount(this.getBudgetUsableAmount());
        return budgetAmount;
    }

    public static final String ID = "ID";

    public static final String ORG_UNIT_ID = "ORG_UNIT_ID";

    public static final String ORG_UNIT_CODE = "ORG_UNIT_CODE";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String LEVEL1_OFFICE_CODE = "LEVEL1_OFFICE_CODE";

    public static final String LEVEL2_OFFICE_CODE = "LEVEL2_OFFICE_CODE";

    public static final String BUDGET_ACCOUNT_CODE = "BUDGET_ACCOUNT_CODE";

    public static final String BUDGET_ACCOUNT_ID = "BUDGET_ACCOUNT_ID";

    public static final String BUDGET_PERIOD = "BUDGET_PERIOD";

    public static final String BUDGET_ANNUAL = "BUDGET_ANNUAL";

    public static final String BUDGET_AMOUNT = "BUDGET_AMOUNT";

    public static final String BUDGET_REMAIN_AMOUNT = "BUDGET_REMAIN_AMOUNT";

    public static final String BUDGET_OCCUPY_AMOUNT = "BUDGET_OCCUPY_AMOUNT";

    public static final String BUDGET_USABLE_AMOUNT = "BUDGET_USABLE_AMOUNT";

    public static final String APPLY_FOR_PERSON = "APPLY_FOR_PERSON";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String TOTAL_FLAG = "TOTAL_FLAG";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
