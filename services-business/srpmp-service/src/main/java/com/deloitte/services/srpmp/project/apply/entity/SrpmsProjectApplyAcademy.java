package com.deloitte.services.srpmp.project.apply.entity;

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
 * 项目申请表-院基科费
 * </p>
 *
 * @author lixin
 * @since 2019-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_APPLY_ACADEMY")
@ApiModel(value="SrpmsProjectApplyAcademy对象", description="项目申请表-院基科费")
public class SrpmsProjectApplyAcademy extends Model<SrpmsProjectApplyAcademy> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "拟申请经费数")
    @TableField("APPLY_FUNDS")
    private Double applyFunds;

    @ApiModelProperty(value = "项目执行期限开始")
    @TableField("PROJECT_ACTION_DATE_START")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束 ")
    @TableField("PROJECT_ACTION_DATE_END")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "申请人ID ")
    @TableField("PROJECT_APPLICANT_ID")
    private Long projectApplicantId;

    @ApiModelProperty(value = "项目摘要 ")
    @TableField("PROJECT_ABSTRACT")
    private String projectAbstract;

    @ApiModelProperty(value = "立项依据，研究意义")
    @TableField("APPROVAL_BASIS_MEANING")
    private String approvalBasisMeaning;

    @ApiModelProperty(value = "研究内容、研究目标以及拟解决的关键科学问题")
    @TableField("CONTENT_TARGET_PROBLEM")
    private String contentTargetProblem;

    @ApiModelProperty(value = "拟采取的研究方案及可行性分析 ")
    @TableField("RESEARCH_SCHEME_FEASIBILITY")
    private String researchSchemeFeasibility;

    @ApiModelProperty(value = "本项目的预期目标 ")
    @TableField("PROJECT_EXPECT_TARGET")
    private String projectExpectTarget;

    @ApiModelProperty(value = "主要技术特点和创新点  ")
    @TableField("PROJECT_TECHNICAL_INNOVATION")
    private String projectTechnicalInnovation;

    @ApiModelProperty(value = "研究基础 ")
    @TableField("RESEARCH_FOUNDATION")
    private String researchFoundation;

    @ApiModelProperty(value = "工作条件")
    @TableField("WORKINF_CONDITIONS")
    private String workinfConditions;

    @ApiModelProperty(value = "申请人简介 ")
    @TableField("PROJECT_APPLICANT_INTRODUCTION")
    private String projectApplicantIntroduction;

    @ApiModelProperty(value = "项目组织单位")
    @TableField("PROJECT_ORGANIZING_UNIT")
    private String projectOrganizingUnit;

    @ApiModelProperty(value = "项目承担单位")
    @TableField("PROJECT_COMMITMENT_UNIT")
    private String projectCommitmentUnit;

    @ApiModelProperty(value = "项目经费来源-总经费")
    @TableField("FUND_SOURCE_AMOUNT")
    private Double fundSourceAmount;

    @ApiModelProperty(value = "项目经费来源-国家财政拨款")
    @TableField("FUND_SOURCE_FINANCIAL")
    private Double fundSourceFinancial;

    @ApiModelProperty(value = "项目经费来源-单位自筹经费")
    @TableField("FUND_SOURCE_SELF_RAISED")
    private Double fundSourceSelfRaised;

    @ApiModelProperty(value = "项目经费来源-其他经费 ")
    @TableField("FUND_SOURCE_OTHER")
    private Double fundSourceOther;

    @ApiModelProperty(value = "经费支出预算明细 ")
    @TableField("EXPENDITURE_BUDGET_DETAIL")
    private String expenditureBudgetDetail;

    @ApiModelProperty(value = "创建时间 ")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人 ")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间 ")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人 ")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "预算说明")
    @TableField("BUDGET_DESCRIPTION")
    private String budgetDescription;

    @ApiModelProperty(value = "项目类别")
    @TableField("PROJECT_CLASS")
    private String projectClass;


    public static final String ID = "ID";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String APPLY_FUNDS = "APPLY_FUNDS";

    public static final String PROJECT_ACTION_DATE_START = "PROJECT_ACTION_DATE_START";

    public static final String PROJECT_ACTION_DATE_END = "PROJECT_ACTION_DATE_END";

    public static final String PROJECT_APPLICANT_ID = "PROJECT_APPLICANT_ID";

    public static final String PROJECT_ABSTRACT = "PROJECT_ABSTRACT";

    public static final String APPROVAL_BASIS_MEANING = "APPROVAL_BASIS_MEANING";

    public static final String CONTENT_TARGET_PROBLEM = "CONTENT_TARGET_PROBLEM";

    public static final String RESEARCH_SCHEME_FEASIBILITY = "RESEARCH_SCHEME_FEASIBILITY";

    public static final String PROJECT_EXPECT_TARGET = "PROJECT_EXPECT_TARGET";

    public static final String PROJECT_TECHNICAL_INNOVATION = "PROJECT_TECHNICAL_INNOVATION";

    public static final String RESEARCH_FOUNDATION = "RESEARCH_FOUNDATION";

    public static final String WORKINF_CONDITIONS = "WORKINF_CONDITIONS";

    public static final String PROJECT_APPLICANT_INTRODUCTION = "PROJECT_APPLICANT_INTRODUCTION";

    public static final String PROJECT_ORGANIZING_UNIT = "PROJECT_ORGANIZING_UNIT";

    public static final String PROJECT_COMMITMENT_UNIT = "PROJECT_COMMITMENT_UNIT";

    public static final String FUND_SOURCE_AMOUNT = "FUND_SOURCE_AMOUNT";

    public static final String FUND_SOURCE_FINANCIAL = "FUND_SOURCE_FINANCIAL";

    public static final String FUND_SOURCE_SELF_RAISED = "FUND_SOURCE_SELF_RAISED";

    public static final String FUND_SOURCE_OTHER = "FUND_SOURCE_OTHER";

    public static final String EXPENDITURE_BUDGET_DETAIL = "EXPENDITURE_BUDGET_DETAIL";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String BUDGET_DESCRIPTION = "BUDGET_DESCRIPTION";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
