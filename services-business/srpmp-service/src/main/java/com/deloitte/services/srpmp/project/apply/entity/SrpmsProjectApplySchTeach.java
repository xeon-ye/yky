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
 * 项目申请表-校基科费-教师
 * </p>
 *
 * @author lixin
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_APPLY_SCH_TEACH")
@ApiModel(value="SrpmsProjectApplySchTeach对象", description="项目申请表-校基科费-教师")
public class SrpmsProjectApplySchTeach extends Model<SrpmsProjectApplySchTeach> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "项目类别")
    @TableField("BASE_SCIENCE_TCH_TYPE")
    private String baseScienceTchType;

    @ApiModelProperty(value = "一级学科")
    @TableField("FIRST_LEVEL_DISCIPLINE")
    private String firstLevelDiscipline;

    @ApiModelProperty(value = "二级学科")
    @TableField("TWO_LEVEL_DISCIPLINE")
    private String twoLevelDiscipline;

    @ApiModelProperty(value = "拟申请经费数")
    @TableField("APPLY_FUNDS")
    private Double applyFunds;

    @ApiModelProperty(value = "项目执行期限开始")
    @TableField("PROJECT_ACTION_DATE_START")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束")
    @TableField("PROJECT_ACTION_DATE_END")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "项目负责人ID")
    @TableField("PROJECT_LEADER_ID")
    private Long projectLeaderId;

    @ApiModelProperty(value = "关键字")
    @TableField("PROJECT_KEYWORD")
    private String projectKeyword;

    @ApiModelProperty(value = "项目摘要")
    @TableField("PROJECT_ABSTRACT")
    private String projectAbstract;

    @ApiModelProperty(value = "立项依据，研究意义")
    @TableField("APPROVAL_BASIS_MEANING")
    private String approvalBasisMeaning;

    @ApiModelProperty(value = "研究内容、研究目标以及拟解决的关键科学问题")
    @TableField("CONTENT_TARGET_PROBLEM")
    private String contentTargetProblem;

    @ApiModelProperty(value = "拟采取的研究方案及可行性分析")
    @TableField("RESEARCH_SCHEME_FEASIBILITY")
    private String researchSchemeFeasibility;

    @ApiModelProperty(value = "本项目的特色与创新之处")
    @TableField("PROJECT_FEATURE_INNOVATE")
    private String projectFeatureInnovate;

    @ApiModelProperty(value = "本项目的预期成果")
    @TableField("PROJECT_EXPECT_ACHIEVEMENT")
    private String projectExpectAchievement;

    @ApiModelProperty(value = "研究基础")
    @TableField("RESEARCH_FOUNDATION")
    private String researchFoundation;

    @ApiModelProperty(value = "工作条件")
    @TableField("WORKINF_CONDITIONS")
    private String workinfConditions;

    @ApiModelProperty(value = "项目负责人简介")
    @TableField("PROJECT_LEADER_INTRODUCTION")
    private String projectLeaderIntroduction;

    @ApiModelProperty(value = "预算明细")
    @TableField("PROJECT_BUDGET_DETAIL")
    private String projectBudgetDetail;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


    public static final String ID = "ID";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String PROJECT_CATEGORY = "PROJECT_CATEGORY";

    public static final String FIRST_LEVEL_DISCIPLINE = "FIRST_LEVEL_DISCIPLINE";

    public static final String TWO_LEVEL_DISCIPLINE = "TWO_LEVEL_DISCIPLINE";

    public static final String APPLY_FUNDS = "APPLY_FUNDS";

    public static final String PROJECT_ACTION_DATE_START = "PROJECT_ACTION_DATE_START";

    public static final String PROJECT_ACTION_DATE_END = "PROJECT_ACTION_DATE_END";

    public static final String PROJECT_LEADER_ID = "PROJECT_LEADER_ID";

    public static final String PROJECT_KEYWORD = "PROJECT_KEYWORD";

    public static final String PROJECT_ABSTRACT = "PROJECT_ABSTRACT";

    public static final String APPROVAL_BASIS_MEANING = "APPROVAL_BASIS_MEANING";

    public static final String CONTENT_TARGET_PROBLEM = "CONTENT_TARGET_PROBLEM";

    public static final String RESEARCH_SCHEME_FEASIBILITY = "RESEARCH_SCHEME_FEASIBILITY";

    public static final String PROJECT_FEATURE_INNOVATE = "PROJECT_FEATURE_INNOVATE";

    public static final String PROJECT_EXPECT_ACHIEVEMENT = "PROJECT_EXPECT_ACHIEVEMENT";

    public static final String RESEARCH_FOUNDATION = "RESEARCH_FOUNDATION";

    public static final String WORKINF_CONDITIONS = "WORKINF_CONDITIONS";

    public static final String PROJECT_LEADER_INTRODUCTION = "PROJECT_LEADER_INTRODUCTION";

    public static final String PROJECT_BUDGET_DETAIL = "PROJECT_BUDGET_DETAIL";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
