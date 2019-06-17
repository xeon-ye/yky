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
 * 项目申请表-创新工程-重大协同创新
 * </p>
 *
 * @author pengchao
 * @since 2019-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_APPLY_INN_BCOO")
@ApiModel(value="SrpmsProjectApplyInnBcoo对象", description="项目申请表-创新工程-重大协同创新")
public class SrpmsProjectApplyInnBcoo extends Model<SrpmsProjectApplyInnBcoo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "所属领域CODE")
    @TableField("BELONG_DOMAIN")
    private String belongDomain;

    @ApiModelProperty(value = "活动类型CODE")
    @TableField("ACTIVE_TYPE")
    private String activeType;

    @ApiModelProperty(value = "预期成果类型CODE")
    @TableField("ACHIEVEMENT_TYPE")
    private String achievementType;

    @ApiModelProperty(value = "拟申请岗位数")
    @TableField("APPLY_POST_NUMBER")
    private Long applyPostNumber;

    @ApiModelProperty(value = "拟申请经费数")
    @TableField("APPLY_FUNDS")
    private Double applyFunds;

    @ApiModelProperty(value = "项目摘要")
    @TableField("PROJECT_ABSTRACT")
    private String projectAbstract;

    @ApiModelProperty(value = "立项必要性")
    @TableField("APPROVAL_NECESSAY")
    private String approvalNecessay;

    @ApiModelProperty(value = "项目成果的呈现形式及描述")
    @TableField("ACHIEVEMENT_FORM")
    private String achievementForm;

    @ApiModelProperty(value = "项目成果的预期经济、社会效益")
    @TableField("ACHIEVEMENT_BENEFIT")
    private String achievementBenefit;

    @ApiModelProperty(value = "主要研究内容")
    @TableField("RESEARCH_CONTENT_MAIN")
    private String researchContentMain;

    @ApiModelProperty(value = "拟采取的研究方法、技术路线及其可行性分析")
    @TableField("RESEARCH_CONTENT_METHOD")
    private String researchContentMethod;

    @ApiModelProperty(value = "国际合作与交流方案")
    @TableField("RESEARCH_CONTENT_INTERFLOW")
    private String researchContentInterflow;

    @ApiModelProperty(value = "成果转化应用和科普方案")
    @TableField("RESEARCH_CONTENT_USE_PLAN")
    private String researchContentUsePlan;

    @ApiModelProperty(value = "项目预期的主要创新点")
    @TableField("RESEARCH_CONTENT_INNOVATE")
    private String researchContentInnovate;

    @ApiModelProperty(value = "项目总体目标、考核指标及测评方式/方法")
    @TableField("PROJECT_TARGET")
    private String projectTarget;

    @ApiModelProperty(value = "目参与单位的选择原因及其优势")
    @TableField("SUPERIORITY_DEPT_CHOOSE")
    private String superiorityDeptChoose;

    @ApiModelProperty(value = "项目牵头和参加单位与课题实施相关的实力和基础")
    @TableField("SUPERIORITY_DEPT_BASIC")
    private String superiorityDeptBasic;

    @ApiModelProperty(value = "申报单位相关科研条件支撑状况")
    @TableField("SUPERIORITY_DEPT_SUPORT")
    private String superiorityDeptSuport;

    @ApiModelProperty(value = "国际合作单位及团队情况")
    @TableField("SUPERIORITY_DEPT_ABROAD")
    private String superiorityDeptAbroad;

    @ApiModelProperty(value = "项目首席专家及骨干成员的情况")
    @TableField("MAINSTAY_MEMBER_NOTE")
    private String mainstayMemberNote;

    @ApiModelProperty(value = "项目组织管理、协同机制和保障措施")
    @TableField("MANAGE_GUARANTEE")
    private String manageGuarantee;

    @ApiModelProperty(value = "知识产权对策、成果管理及合作权益分配")
    @TableField("MANAGE_KNOWLEDGE_RIGHT")
    private String manageKnowledgeRight;

    @ApiModelProperty(value = "风险分析及对策")
    @TableField("MANAGE_RISK")
    private String manageRisk;

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

    public static final String LEAD_PERSON_WORK_TIME = "LEAD_PERSON_WORK_TIME";

    public static final String BOTH_TOP_WORK_TIME = "BOTH_TOP_WORK_TIME";

    public static final String ID = "ID";

    public static final String BELONG_DOMAIN = "BELONG_DOMAIN";

    public static final String ACTIVE_TYPE = "ACTIVE_TYPE";

    public static final String ACHIEVEMENT_TYPE = "ACHIEVEMENT_TYPE";

    public static final String APPLY_POST_NUMBER = "APPLY_POST_NUMBER";

    public static final String APPLY_FUNDS = "APPLY_FUNDS";

    public static final String PROJECT_ABSTRACT = "PROJECT_ABSTRACT";

    public static final String APPROVAL_NECESSAY = "APPROVAL_NECESSAY";

    public static final String ACHIEVEMENT_FORM = "ACHIEVEMENT_FORM";

    public static final String ACHIEVEMENT_BENEFIT = "ACHIEVEMENT_BENEFIT";

    public static final String RESEARCH_CONTENT_MAIN = "RESEARCH_CONTENT_MAIN";

    public static final String RESEARCH_CONTENT_METHOD = "RESEARCH_CONTENT_METHOD";

    public static final String RESEARCH_CONTENT_INTERFLOW = "RESEARCH_CONTENT_INTERFLOW";

    public static final String RESEARCH_CONTENT_USE_PLAN = "RESEARCH_CONTENT_USE_PLAN";

    public static final String RESEARCH_CONTENT_INNOVATE = "RESEARCH_CONTENT_INNOVATE";

    public static final String PROJECT_TARGET = "PROJECT_TARGET";

    public static final String SUPERIORITY_DEPT_CHOOSE = "SUPERIORITY_DEPT_CHOOSE";

    public static final String SUPERIORITY_DEPT_BASIC = "SUPERIORITY_DEPT_BASIC";

    public static final String SUPERIORITY_DEPT_SUPORT = "SUPERIORITY_DEPT_SUPORT";

    public static final String SUPERIORITY_DEPT_ABROAD = "SUPERIORITY_DEPT_ABROAD";

    public static final String MAINSTAY_MEMBER_NOTE = "MAINSTAY_MEMBER_NOTE";

    public static final String MANAGE_GUARANTEE = "MANAGE_GUARANTEE";

    public static final String MANAGE_KNOWLEDGE_RIGHT = "MANAGE_KNOWLEDGE_RIGHT";

    public static final String MANAGE_RISK = "MANAGE_RISK";

    public static final String FUND_SOURCE_AMOUNT = "FUND_SOURCE_AMOUNT";

    public static final String FUND_SOURCE_PROJECT = "FUND_SOURCE_PROJECT";

    public static final String FUND_SOURCE_SELF = "FUND_SOURCE_SELF";

    public static final String FUND_SOURCE_OTHER = "FUND_SOURCE_OTHER";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String TASK_DIAGRAM = "TASK_DIAGRAM";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
