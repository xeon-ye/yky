package com.deloitte.services.fssc.budget.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@TableName("BUDGET_PROJECT")
@ApiModel(value="BudgetProject对象", description="")
public class BudgetProject extends Model<BudgetProject> {

    private static final long serialVersionUID = 81L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编码")
    @TableField("PROJECT_CODE")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "项目类型")
    @TableField("PROJECT_TYPE")
    private String projectType;

    @ApiModelProperty(value = "类型名称")
    @TableField("TYPE_NAME")
    private String typeName;

    @ApiModelProperty(value = "项目大类")
    @TableField("PROJECT_BIG_TYPE")
    private String projectBigType;

    @ApiModelProperty(value = "大类名称")
    @TableField("BIG_TYPE_NAME")
    private String bigTypeName;

    @ApiModelProperty(value = "项目中类")
    @TableField("PROJECT_MIDDLE_TYPE")
    private String projectMiddleType;

    @ApiModelProperty(value = "中类名称")
    @TableField("MIDDLE_TYPE_NAME")
    private String middleTypeName;

    @ApiModelProperty(value = "项目小类")
    @TableField("PROJECT_SMALL_TYPE")
    private String projectSmallType;

    @ApiModelProperty(value = "小类名称")
    @TableField("SMALL_TYPE_NAME")
    private String smallTypeName;

    @ApiModelProperty(value = "项目负责人")
    @TableField("PROJECT_DUTY")
    private String projectDuty;

    @ApiModelProperty(value = "项目负责人Id")
    @TableField("PROJECT_DUTY_ID")
    private String projectDutyId;

    @ApiModelProperty(value = "外部承担单位ID")
    @TableField("RESPONSIBLE_UNIT_ID")
    private Long responsibleUnitId;

    @ApiModelProperty(value = "外部承担单位")
    @TableField("RESPONSIBLE_UNIT_CODE")
    private String responsibleUnitCode;

    @ApiModelProperty(value = "外部承担单位名称")
    @TableField("RESPONSIBLE_UNIT_NAME")
    private String responsibleUnitName;

    @ApiModelProperty(value = "归属部门")
    @TableField("BELONG_TO_DEPART_ID")
    private Long belongToDepartId;

    @ApiModelProperty(value = "是否父项目")
    @TableField("PARENT_FLAG")
    private String parentFlag;

    @ApiModelProperty(value = "父项目ID")
    @TableField("PARENT_ID")
    private Long parentId;

    @ApiModelProperty(value = "内部单位ID")
    @TableField("ORG_UNIT_ID")
    private Long orgUnitId;

    @ApiModelProperty(value = "内部单位")
    @TableField("ORG_UNIT_CODE")
    private String orgUnitCode;

    @ApiModelProperty(value = "内部单位名称")
    @TableField("ORG_UNIT_NAME")
    private String orgUnitName;

    @ApiModelProperty(value = "组织路径")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "状态")
    @TableField("PROJECT_STATUS")
    private String projectStatus;

    @ApiModelProperty(value = "预算科目编码")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预算科目ID")
    @TableField("BUDGET_ACCOUNT_ID")
    private Long budgetAccountId;

//    @ApiModelProperty(value = "创建人")
//    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
//    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

//    @ApiModelProperty(value = "更新人")
//    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
//    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "项目开始时间")
    @TableField("PROJECT_START_TIME")
    private LocalDateTime projectStartTime;

    @ApiModelProperty(value = "项目结束时间")
    @TableField("PROJECT_END_TIME")
    private LocalDateTime projectEndTime;


    //新增财务自己的字段
    @ApiModelProperty(value = "财务code")
    @TableField("FSSC_CODE")
    private String fsscCode;

    @ApiModelProperty(value = "经费类型")
    @TableField("MONEY_TYPE")
    private String moneyType;

    @ApiModelProperty(value = "计划号")
    @TableField("PLAN_NUM")
    private String planNum;

    @ApiModelProperty(value = "关联号")
    @TableField("CONNECT_NUM")
    private String connectNum;

    @ApiModelProperty(value = "是否允许报账")
    @TableField("IS_ALLOW_EXPENSE")
    private String isAllowExpense;

    @ApiModelProperty(value = "项目总金额")
    @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;


    @ApiModelProperty(value = "款项大类id")
    @TableField("IN_COME_MAIN_TYPE_ID")
    private Long inComeMainTypeId;

    @ApiModelProperty(value = "款项小类id")
    @TableField("IN_COME_SUB_TYPE_ID")
    private Long inComeSubTypeId;


    @ApiModelProperty(value = "扩展字段1 项目来源系统")
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

    @ApiModelProperty(value = "扩展字段5,对应科研项目最新的预算年度")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "收入结转方式")
    @TableField("PAYMENT_CONFIRM_TYPE")
    private String paymentConfirmType;

    @ApiModelProperty(value = "财务-会计科目编码")
    @TableField("ACCOUNT_CODE")
    private String accountCode;

    public static final String ID = "ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String PROJECT_CODE = "PROJECT_CODE";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String PROJECT_TYPE = "PROJECT_TYPE";

    public static final String TYPE_NAME = "TYPE_NAME";

    public static final String PROJECT_BIG_TYPE = "PROJECT_BIG_TYPE";

    public static final String BIG_TYPE_NAME = "BIG_TYPE_NAME";

    public static final String PROJECT_MIDDLE_TYPE = "PROJECT_MIDDLE_TYPE";

    public static final String MIDDLE_TYPE_NAME = "MIDDLE_TYPE_NAME";

    public static final String PROJECT_SMALL_TYPE = "PROJECT_SMALL_TYPE";

    public static final String SMALL_TYPE_NAME = "SMALL_TYPE_NAME";

    public static final String PROJECT_DUTY = "PROJECT_DUTY";

    public static final String RESPONSIBLE_UNIT_CODE = "RESPONSIBLE_UNIT_CODE";

    public static final String RESPONSIBLE_UNIT_NAME = "RESPONSIBLE_UNIT_NAME";

    public static final String BELONG_TO_DEPART_ID = "BELONG_TO_DEPART_ID";

    public static final String PARENT_FLAG = "PARENT_FLAG";

    public static final String PARENT_ID = "PARENT_ID";

    public static final String ORG_UNIT_ID = "ORG_UNIT_ID";

    public static final String ORG_UNIT_CODE = "ORG_UNIT_CODE";

    public static final String ORG_UNIT_NAME = "ORG_UNIT_NAME";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String PROJECT_STATUS = "PROJECT_STATUS";

    public static final String BUDGET_ACCOUNT_CODE = "BUDGET_ACCOUNT_CODE";

    public static final String BUDGET_ACCOUNT_ID = "BUDGET_ACCOUNT_ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String PROJECT_START_TIME = "PROJECT_START_TIME";

    public static final String TOTAL_AMOUNT = "TOTAL_AMOUNT";

    public static final String CONNECT_NUM = "CONNECT_NUM";

    public static final String PLAN_NUM = "PLAN_NUM";

    public static final String FSSC_CODE = "FSSC_CODE";

    public static final String ACCOUNT_CODE = "ACCOUNT_CODE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
