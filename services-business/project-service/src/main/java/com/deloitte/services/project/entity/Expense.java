package com.deloitte.services.project.entity;

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
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目支出明细批复表
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_EXPENSE")
@ApiModel(value="Expense对象", description="项目支出明细批复表")
public class Expense extends Model<Expense> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "立项支出汇总ID")
    @TableField("EXPENSE_ID")
    private String expenseId;

    @ApiModelProperty(value = "项目批复书ID")
    @TableField("PROJECT_APPROVAL_ID")
    private String projectApprovalId;

    @ApiModelProperty(value = "预算年度")
    @TableField("BUDGET_YEAR")
    private String budgetYear;

    @ApiModelProperty(value = "支出计划")
    @TableField("EXPENSE_PLAN")
    private String expensePlan;

    @ApiModelProperty(value = "FIN_ALLOCATION_BEGINNING_YEAR 年初财政拨款")
    @TableField("FIN_ALLOCATION_EARLY")
    private String finAllocationEarly;

    @ApiModelProperty(value = "FIN_CARRY_OVER_BEGINNING_YEAR 年初结转资金")
    @TableField("FIN_CARRY_OVER_EARLY")
    private String finCarryOverEarly;

    @ApiModelProperty(value = "调整财政拨款")
    @TableField("ADJ_FINANCIAL_ALLOCATION")
    private String adjFinancialAllocation;

    @ApiModelProperty(value = "调整结转资金")
    @TableField("ADJ_FINANCIAL_CARRY_OVER")
    private String adjFinancialCarryOver;

    @ApiModelProperty(value = "全年财政拨款")
    @TableField("FIN_ALLOCATION_ALL_YEAR")
    private String finAllocationAllYear;

    @ApiModelProperty(value = "全年结转资金")
    @TableField("FIN_CARRY_OVER_ALL_YEAR")
    private String finCarryOverAllYear;

    @ApiModelProperty(value = "支出财政拨款")
    @TableField("FIN_ALLOCATION_BUDGET")
    private String finAllocationBudget;

    @ApiModelProperty(value = "支出结转资金")
    @TableField("FIN_CARRY_OVER_BUDGET")
    private String finCarryOverBudget;

    @ApiModelProperty(value = "FIN_ALLOCATION_REST_OF_YEAR 年度剩余财政拨款")
    @TableField("FIN_ALLOCATION_SURPLUS")
    private String finAllocationSurplus;

    @ApiModelProperty(value = "FIN_CARRY_OVER_REST_OF_YEAR 年度剩余结转资金")
    @TableField("FIN_CARRY_OVER_SURPLUS")
    private String finCarryOverSurplus;

    @ApiModelProperty(value = "FIN_ALLOCATION_REST_OF_FACT_YEAR 实际年度剩余财政拨款")
    @TableField("FIN_ALLOCATION_FACT_SURPLUS")
    private String finAllocationFactSurplus;

    @ApiModelProperty(value = "FIN_CARRY_OVER_REST_OF_FACT_YEAR 实际年度剩余结转资金")
    @TableField("FIN_CARRY_OVER_FACT_SURPLUS")
    private String finCarryOverFactSurplus;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "数据权限维度字段ORG_ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "数据权限维度字段ORG_PATH")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "拓展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    @TableField("APPLICATION_ID")
    private String applicationId;


    public static final String ID = "ID";

    public static final String EXPENSE_ID = "EXPENSE_ID";

    public static final String PROJECT_APPROVAL_ID = "PROJECT_APPROVAL_ID";

    public static final String BUDGET_YEAR = "BUDGET_YEAR";

    public static final String EXPENSE_PLAN = "EXPENSE_PLAN";

    public static final String FIN_ALLOCATION_EARLY = "FIN_ALLOCATION_EARLY";

    public static final String FIN_CARRY_OVER_EARLY = "FIN_CARRY_OVER_EARLY";

    public static final String ADJ_FINANCIAL_ALLOCATION = "ADJ_FINANCIAL_ALLOCATION";

    public static final String ADJ_FINANCIAL_CARRY_OVER = "ADJ_FINANCIAL_CARRY_OVER";

    public static final String FIN_ALLOCATION_ALL_YEAR = "FIN_ALLOCATION_ALL_YEAR";

    public static final String FIN_CARRY_OVER_ALL_YEAR = "FIN_CARRY_OVER_ALL_YEAR";

    public static final String FIN_ALLOCATION_BUDGET = "FIN_ALLOCATION_BUDGET";

    public static final String FIN_CARRY_OVER_BUDGET = "FIN_CARRY_OVER_BUDGET";

    public static final String FIN_ALLOCATION_SURPLUS = "FIN_ALLOCATION_SURPLUS";

    public static final String FIN_CARRY_OVER_SURPLUS = "FIN_CARRY_OVER_SURPLUS";

    public static final String FIN_ALLOCATION_FACT_SURPLUS = "FIN_ALLOCATION_FACT_SURPLUS";

    public static final String FIN_CARRY_OVER_FACT_SURPLUS = "FIN_CARRY_OVER_FACT_SURPLUS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
