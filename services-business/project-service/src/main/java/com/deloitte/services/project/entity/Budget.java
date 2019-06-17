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
 * 项目预算
 * </p>
 *
 * @author zhengchun
 * @since 2019-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_BUDGET")
@ApiModel(value="Budget对象", description="项目预算")
public class Budget extends Model<Budget> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "预算ID")
    @TableField("BUDGET_ID")
    private String budgetId;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "支出大类CODE")
    @TableField("EXPENSE_PROJECT_CODE")
    private String expenseProjectCode;

    @ApiModelProperty(value = "支出大类")
    @TableField("EXPENSE_PROJECT")
    private String expenseProject;

    @ApiModelProperty(value = "预算年度")
    @TableField("BUDGETARY_YEAR")
    private String budgetaryYear;

    @ApiModelProperty(value = "申请合计")
    @TableField("APPLY_TOTAL")
    private String applyTotal;

    @ApiModelProperty(value = "申报中央财政")
    @TableField("CENTRAL_FIN")
    private String centralFin;

    @ApiModelProperty(value = "申报主管部门")
    @TableField("DEPARTMENT")
    private String department;

    @ApiModelProperty(value = "申报其他")
    @TableField("OTHER")
    private String other;

    @ApiModelProperty(value = "申请经费测算依据")
    @TableField("BASIS_ESTIMATING_APP_FUNDS")
    private String basisEstimatingAppFunds;

    @ApiModelProperty(value = "评审ID")
    @TableField("REVIEW_ID")
    private String reviewId;

    @ApiModelProperty(value = "评审中央财政金额")
    @TableField("REVIEW_CENTRAL_FINANCE")
    private String reviewCentralFinance;

    @ApiModelProperty(value = "评审主管部门金额")
    @TableField("REVIEW_DEPARTEMNT_FUND")
    private String reviewDepartemntFund;

    @ApiModelProperty(value = "评审其他金额")
    @TableField("REVIEW_OTHERS")
    private String reviewOthers;

    @ApiModelProperty(value = "立项批复ID")
    @TableField("REPLAY_ID")
    private String replayId;

    @ApiModelProperty(value = "批复其它")
    @TableField("REPLAY_OTHER")
    private String replayOther;

    @ApiModelProperty(value = "批复中央财政")
    @TableField("REPLAY_CENTER")
    private String replayCenter;

    @ApiModelProperty(value = "批复部门")
    @TableField("REPLAY_DEP")
    private String replayDep;

    @ApiModelProperty(value = "结转资金")
    @TableField("FOUNDING_FORWARD")
    private String foundingForward;

    @ApiModelProperty(value = "创建")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "类型")
    @TableField("EXPENSE_CODES")
    private String expenseCodes;


    public static final String ID = "ID";

    public static final String BUDGET_ID = "BUDGET_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String EXPENSE_PROJECT_CODE = "EXPENSE_PROJECT_CODE";

    public static final String EXPENSE_PROJECT = "EXPENSE_PROJECT";

    public static final String BUDGETARY_YEAR = "BUDGETARY_YEAR";

    public static final String APPLY_TOTAL = "APPLY_TOTAL";

    public static final String CENTRAL_FIN = "CENTRAL_FIN";

    public static final String DEPARTMENT = "DEPARTMENT";

    public static final String OTHER = "OTHER";

    public static final String BASIS_ESTIMATING_APP_FUNDS = "BASIS_ESTIMATING_APP_FUNDS";

    public static final String REVIEW_ID = "REVIEW_ID";

    public static final String REVIEW_CENTRAL_FINANCE = "REVIEW_CENTRAL_FINANCE";

    public static final String REVIEW_DEPARTEMNT_FUND = "REVIEW_DEPARTEMNT_FUND";

    public static final String REVIEW_OTHERS = "REVIEW_OTHERS";

    public static final String REPLAY_ID = "REPLAY_ID";

    public static final String REPLAY_OTHER = "REPLAY_OTHER";

    public static final String REPLAY_CENTER = "REPLAY_CENTER";

    public static final String REPLAY_DEP = "REPLAY_DEP";

    public static final String FOUNDING_FORWARD = "FOUNDING_FORWARD";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String EXPENSE_CODES = "EXPENSE_CODES";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
