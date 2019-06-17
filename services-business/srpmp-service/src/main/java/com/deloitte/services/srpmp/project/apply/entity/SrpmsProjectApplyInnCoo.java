package com.deloitte.services.srpmp.project.apply.entity;

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
 * 项目申请表-创新工程-协同
 * </p>
 *
 * @author pengchao
 * @since 2019-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_APPLY_INN_COO")
@ApiModel(value="SrpmsProjectApplyInnCoo对象", description="项目申请表-创新工程-协同")
public class SrpmsProjectApplyInnCoo extends Model<SrpmsProjectApplyInnCoo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "研究方向CODE")
    @TableField("DIRECTION")
    private String direction;

    @TableField("LEAD_DEPT_NAME")
    private String leadDeptName;

    @ApiModelProperty(value = "团队组成情况-研究方向")
    @TableField("TEAM_DIRECTION")
    private String teamDirection;

    @ApiModelProperty(value = "团队组成情况-英文名称")
    @TableField("TEAM_EN_NAME")
    private String teamEnName;

    @ApiModelProperty(value = "团队的组成情况")
    @TableField("TEAM_CONSTITUTE")
    private String teamConstitute;

    @ApiModelProperty(value = "首席专家简介")
    @TableField("TOP_EXPERT_PERSON_INTRO")
    private String topExpertPersonIntro;

    @ApiModelProperty(value = "团队主要成员简介")
    @TableField("TEAM_MEMBER_INTRO")
    private String teamMemberIntro;

    @ApiModelProperty(value = "近五年取得的主要学术成绩、创新点及其科学意义")
    @TableField("PERFORMANCE_LATELY")
    private String performanceLately;

    @ApiModelProperty(value = "项目总体目标、考核指标及测评方式/方法")
    @TableField("PROJECT_TARGET")
    private String projectTarget;

    @ApiModelProperty(value = "述拟开展的研究工作方案及其可行性")
    @TableField("RESEARCH_PLAN")
    private String researchPlan;

    @ApiModelProperty(value = "团队国际合作与交流计划等")
    @TableField("RESEARCH_CONTENT_INTERFLOW")
    private String researchContentInterflow;

    @ApiModelProperty(value = "项目预期的主要创新点")
    @TableField("RESEARCH_CONTENT_INNOVATE")
    private String researchContentInnovate;

    @ApiModelProperty(value = "预期取得的重大成果及其意义")
    @TableField("ACHIEVEMENT_FORM")
    private String achievementForm;

    @ApiModelProperty(value = "项目成果的预期经济、社会效益")
    @TableField("ACHIEVEMENT_BENEFIT")
    private String achievementBenefit;

    @ApiModelProperty(value = "项目组织管理、协同机制和保障措施")
    @TableField("MANAGE_GUARANTEE")
    private String manageGuarantee;

    @ApiModelProperty(value = "知识产权对策、成果管理及合作权益分配")
    @TableField("MANAGE_KNOWLEDGE_RIGHT")
    private String manageKnowledgeRight;

    @ApiModelProperty(value = "风险分析及对策")
    @TableField("MANAGE_RISK")
    private String manageRisk;

    @ApiModelProperty(value = "保障条件")
    @TableField("GUARANTEE_PLAN")
    private String guaranteePlan;

    @ApiModelProperty(value = "项目经费来源-总经费")
    @TableField("FUND_SOURCE_AMOUNT")
    private Double fundSourceAmount;

    @ApiModelProperty(value = "项目经费来源-创新工程经费")
    @TableField("FUND_SOURCE_PROJECT")
    private Double fundSourceProject;

    @ApiModelProperty(value = "项目经费来源-单位自筹经费")
    @TableField("FUND_SOURCE_SELF")
    private Double fundSourceSelf;

    @ApiModelProperty(value = "项目经费来源-其他经费")
    @TableField("FUND_SOURCE_OTHER")
    private Double fundSourceOther;

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

    @ApiModelProperty(value = "项目负责人每年工作时间")
    @TableField("LEAD_PERSON_WORK_TIME")
    private Long leadPersonWorkTime;

    @ApiModelProperty(value = "共同首席专家每年工作时间")
    @TableField("BOTH_TOP_WORK_TIME")
    private Long bothTopWorkTime;

    @ApiModelProperty(value = "任务分解逻辑关系图解")
    @TableField("TASK_DIAGRAM")
    private String taskDiagram;

    @ApiModelProperty(value = "所属领域CODE")
    @TableField("BELONG_DOMAIN")
    private String belongDomain;

    @ApiModelProperty(value = "申请经费")
    @TableField("APPLY_FUNDS")
    private String applyFunds;
    
    public static final String LEAD_PERSON_WORK_TIME = "LEAD_PERSON_WORK_TIME";

    public static final String BOTH_TOP_WORK_TIME = "BOTH_TOP_WORK_TIME";

    public static final String ID = "ID";

    public static final String DIRECTION = "DIRECTION";

    public static final String LEAD_DEPT_NAME = "LEAD_DEPT_NAME";

    public static final String TEAM_DIRECTION = "TEAM_DIRECTION";

    public static final String TEAM_EN_NAME = "TEAM_EN_NAME";

    public static final String TEAM_CONSTITUTE = "TEAM_CONSTITUTE";

    public static final String TOP_EXPERT_PERSON_INTRO = "TOP_EXPERT_PERSON_INTRO";

    public static final String TEAM_MEMBER_INTRO = "TEAM_MEMBER_INTRO";

    public static final String PERFORMANCE_LATELY = "PERFORMANCE_LATELY";

    public static final String PROJECT_TARGET = "PROJECT_TARGET";

    public static final String RESEARCH_PLAN = "RESEARCH_PLAN";

    public static final String RESEARCH_CONTENT_INTERFLOW = "RESEARCH_CONTENT_INTERFLOW";

    public static final String RESEARCH_CONTENT_INNOVATE = "RESEARCH_CONTENT_INNOVATE";

    public static final String ACHIEVEMENT_FORM = "ACHIEVEMENT_FORM";

    public static final String ACHIEVEMENT_BENEFIT = "ACHIEVEMENT_BENEFIT";

    public static final String MANAGE_GUARANTEE = "MANAGE_GUARANTEE";

    public static final String MANAGE_KNOWLEDGE_RIGHT = "MANAGE_KNOWLEDGE_RIGHT";

    public static final String MANAGE_RISK = "MANAGE_RISK";

    public static final String GUARANTEE_PLAN = "GUARANTEE_PLAN";

    public static final String FUND_SOURCE_AMOUNT = "FUND_SOURCE_AMOUNT";

    public static final String FUND_SOURCE_PROJECT = "FUND_SOURCE_PROJECT";

    public static final String FUND_SOURCE_SELF = "FUND_SOURCE_SELF";

    public static final String FUND_SOURCE_OTHER = "FUND_SOURCE_OTHER";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String TASK_DIAGRAM = "TASK_DIAGRAM";

    public static final String BELONG_DOMAIN = "BELONG_DOMAIN";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
