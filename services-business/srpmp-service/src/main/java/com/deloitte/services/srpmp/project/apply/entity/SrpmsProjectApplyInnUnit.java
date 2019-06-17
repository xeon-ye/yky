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

/**
 * <p>
 * 
 * </p>
 *
 * @author lixin
 * @since 2019-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_APPLY_INN_UNIT")
@ApiModel(value="SrpmsProjectApplyInnUnit对象", description="")
public class SrpmsProjectApplyInnUnit extends Model<SrpmsProjectApplyInnUnit> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "英文名称")
    @TableField("PROJECT_EG_NAME")
    private String projectEgName;

    @ApiModelProperty(value = "学科门类")
    @TableField("SUBJECT_CATEGORY")
    private String subjectCategory;

    @ApiModelProperty(value = "学科名称")
    @TableField("SUBJECT_NAME")
    private String subjectName;

    @ApiModelProperty(value = "活动类型")
    @TableField("ACHIEVEMENT_TYPE")
    private String achievementType;

    @ApiModelProperty(value = "教育经历")
    @TableField("LEAD_PERSON_EDUCATION_EXP")
    private String leadPersonEducationExp;

    @ApiModelProperty(value = "科研经历")
    @TableField("LEAD_PERSON_RESEARCH_EXP")
    private String leadPersonResearchExp;

    @ApiModelProperty(value = "主要研究方向与内容简介")
    @TableField("LEAD_PERSON_INTRO")
    private String leadPersonIntro;

    @ApiModelProperty(value = "建设的目的和意义")
    @TableField("PROJECT_TARGET")
    private String projectTarget;

    @ApiModelProperty(value = "现有研究工作的基础、水平、标准情况")
    @TableField("SITUATION_AND_BENIFIT")
    private String situationAndBenifit;

    @ApiModelProperty(value = "国内外本领域发展现状及趋势")
    @TableField("DOMAIN_SITUATION")
    private String domainSituation;

    @ApiModelProperty(value = "对标情况")
    @TableField("BENCH_MARKING")
    private String benchMarking;

    @ApiModelProperty(value = "发展前景")
    @TableField("PROSPECT")
    private String prospect;

    @ApiModelProperty(value = "主要研究方向、研究内容及研究团队构成")
    @TableField("TEAM_DIRECTION")
    private String teamDirection;

    @ApiModelProperty(value = "未来5年创新单元建设发展目标")
    @TableField("DEVELOPMENT_GOAL")
    private String developmentGoal;

    @ApiModelProperty(value = "未来5年创新单元研发投入计划")
    @TableField("BUDGET_PLAN")
    private String budgetPlan;

    @ApiModelProperty(value = "目前已具备科研条件")
    @TableField("CURRENT_CONDITIONS")
    private String currentConditions;

    @ApiModelProperty(value = "未来5年内科研条件和配套设施改善计划")
    @TableField("FUTURE_CONDITIONS")
    private String futureConditions;

    @ApiModelProperty(value = "创新单元主任、主要骨干简介")
    @TableField("TEAM_INTRO")
    private String teamIntro;

    @ApiModelProperty(value = "项目总数")
    @TableField("OTHER_PROJECT_AMOUT")
    private String otherProjectAmout;

    @ApiModelProperty(value = "经费总数")
    @TableField("OTHER_BUDGET_AMOUT")
    private String otherBudgetAmout;

    @ApiModelProperty(value = "年度经费")
    @TableField("OTHER_YEAR_BUDGET")
    private String otherYearBudget;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


    public static final String ID = "ID";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String PROJECT_EG_NAME = "PROJECT_EG_NAME";

    public static final String SUBJECT_CATEGORY = "SUBJECT_CATEGORY";

    public static final String SUBJECT_NAME = "SUBJECT_NAME";

    public static final String ACHIEVEMENT_TYPE = "ACHIEVEMENT_TYPE";

    public static final String LEAD_PERSON_EDUCATION_EXP = "LEAD_PERSON_EDUCATION_EXP";

    public static final String LEAD_PERSON_RESEARCH_EXP = "LEAD_PERSON_RESEARCH_EXP";

    public static final String LEAD_PERSON_INTRO = "LEAD_PERSON_INTRO";

    public static final String PROJECT_TARGET = "PROJECT_TARGET";

    public static final String SITUATION_AND_BENIFIT = "SITUATION_AND_BENIFIT";

    public static final String DOMAIN_SITUATION = "DOMAIN_SITUATION";

    public static final String BENCH_MARKING = "BENCH_MARKING";

    public static final String PROSPECT = "PROSPECT";

    public static final String TEAM_DIRECTION = "TEAM_DIRECTION";

    public static final String DEVELOPMENT_GOAL = "DEVELOPMENT_GOAL";

    public static final String BUDGET_PLAN = "BUDGET_PLAN";

    public static final String CURRENT_CONDITIONS = "CURRENT_CONDITIONS";

    public static final String FUTURE_CONDITIONS = "FUTURE_CONDITIONS";

    public static final String TEAM_INTRO = "TEAM_INTRO";

    public static final String ATTACHMENT_COMMITTEE = "ATTACHMENT_COMMITTEE";

    public static final String ATTACHMENT_AUDIT = "ATTACHMENT_AUDIT";

    public static final String ATTACHMENT_STATEMENT = "ATTACHMENT_STATEMENT";

    public static final String ATTACHMENT_DEPT = "ATTACHMENT_DEPT";

    public static final String OTHER_PROJECT_AMOUT = "OTHER_PROJECT_AMOUT";

    public static final String OTHER_BUDGET_AMOUT = "OTHER_BUDGET_AMOUT";

    public static final String OTHER_YEAR_BUDGET = "OTHER_YEAR_BUDGET";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
