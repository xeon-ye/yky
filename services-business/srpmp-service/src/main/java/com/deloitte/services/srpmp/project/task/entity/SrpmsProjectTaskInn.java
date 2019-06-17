package com.deloitte.services.srpmp.project.task.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * 项目任务书-创新工程
 * </p>
 *
 * @author pengchao
 * @since 2019-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_TASK_INN")
@ApiModel(value="SrpmsProjectTaskInn对象", description="项目任务书-创新工程")
public class SrpmsProjectTaskInn extends Model<SrpmsProjectTaskInn> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "项目负责人每年工作时间")
    @TableField("LEAD_PERSON_WORK_TIME")
    private Long leadPersonWorkTime;

    @ApiModelProperty(value = "共同首席专家每年工作时间")
    @TableField("BOTH_TOP_WORK_TIME")
    private Long bothTopWorkTime;

    @ApiModelProperty(value = "研究队伍人数")
    @TableField("RESEARCH_MEMBER_SIZE")
    private Long researchMemberSize;

    @ApiModelProperty(value = "全时工作时间")
    @TableField("RESEARCH_WORK_TIME")
    private Double researchWorkTime;

    @ApiModelProperty(value = "任务负责人简介")
    @TableField("LEAD_PERSON_NOTE")
    private String leadPersonNote;

    @ApiModelProperty(value = "共同首席专家任务负责人简介")
    @TableField("BOTH_TOP_NOTE")
    private String bothTopNote;

    @ApiModelProperty(value = "立项依据")
    @TableField("APPROVAL_NECESSAY")
    private String approvalNecessay;

    @ApiModelProperty(value = "研究内容")
    @TableField("RESEARCH_CONTENT_MAIN")
    private String researchContentMain;

    @ApiModelProperty(value = "研究目标")
    @TableField("RESEARCH_TARGET")
    private String researchTarget;

    @ApiModelProperty(value = "任务总体考核指标及测评方式方法")
    @TableField("TASK_MASTER_METHOD")
    private String taskMasterMethod;

    @ApiModelProperty(value = "项目成果的呈现形式及描述")
    @TableField("ACHIEVEMENT_FORM")
    private String achievementForm;

    @ApiModelProperty(value = "主要示范或产业化内容")
    @TableField("MAIN_CONTENTS")
    private String mainContents;

    @ApiModelProperty(value = "国际合作和交流方案")
    @TableField("EXCHANGE_PROGRAMME")
    private String exchangeProgramme;

    @ApiModelProperty(value = "项目成果的预期经济、社会效益")
    @TableField("ACHIEVEMENT_BENEFIT")
    private String achievementBenefit;

    @ApiModelProperty(value = "拟采取的研究方法、技术路线及其可行性分析")
    @TableField("RESEARCH_CONTENT_METHOD")
    private String researchContentMethod;

    @ApiModelProperty(value = "任务组织管理机制、产学研结合、创新人才队伍的凝聚和培养等")
    @TableField("TASK_ORG_MANAGE_MODE")
    private String taskOrgManageMode;

    @ApiModelProperty(value = "知识产权对策、成果管理及合作权益分配")
    @TableField("KNOWLEDGE_RESULT_MANAGE")
    private String knowledgeResultManage;

    @ApiModelProperty(value = "风险分析及对策")
    @TableField("RISK_ANALYZE_MANAGE")
    private String riskAnalyzeManage;

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

    @ApiModelProperty(value = "所属领域CODE")
    @TableField("BELONG_DOMAIN")
    private String belongDomain;

    @ApiModelProperty(value = "活动类型CODE")
    @TableField("ACTIVE_TYPE")
    private String activeType;

    @ApiModelProperty(value = "预期成果类型CODE")
    @TableField("ACHIEVEMENT_TYPE")
    private String achievementType;

    @ApiModelProperty(value = "申请经费")
    @TableField("APPLY_FUNDS")
    private String applyFunds;

    public static final String ID = "ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String LEAD_PERSON_WORK_TIME = "LEAD_PERSON_WORK_TIME";

    public static final String BOTH_TOP_WORK_TIME = "BOTH_TOP_WORK_TIME";

    public static final String RESEARCH_MEMBER_SIZE = "RESEARCH_MEMBER_SIZE";

    public static final String RESEARCH_WORK_TIME = "RESEARCH_WORK_TIME";

    public static final String LEAD_PERSON_NOTE = "LEAD_PERSON_NOTE";

    public static final String BOTH_TOP_NOTE = "BOTH_TOP_NOTE";

    public static final String APPROVAL_NECESSAY = "APPROVAL_NECESSAY";

    public static final String RESEARCH_CONTENT_MAIN = "RESEARCH_CONTENT_MAIN";

    public static final String RESEARCH_TARGET = "RESEARCH_TARGET";

    public static final String TASK_MASTER_METHOD = "TASK_MASTER_METHOD";

    public static final String ACHIEVEMENT_FORM = "ACHIEVEMENT_FORM";

    public static final String MAIN_CONTENTS = "MAIN_CONTENTS";

    public static final String EXCHANGE_PROGRAMME = "EXCHANGE_PROGRAMME";

    public static final String ACHIEVEMENT_BENEFIT = "ACHIEVEMENT_BENEFIT";

    public static final String RESEARCH_CONTENT_METHOD = "RESEARCH_CONTENT_METHOD";

    public static final String TASK_ORG_MANAGE_MODE = "TASK_ORG_MANAGE_MODE";

    public static final String KNOWLEDGE_RESULT_MANAGE = "KNOWLEDGE_RESULT_MANAGE";

    public static final String RISK_ANALYZE_MANAGE = "RISK_ANALYZE_MANAGE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String BELONG_DOMAIN = "BELONG_DOMAIN";

    public static final String ACTIVE_TYPE = "ACTIVE_TYPE";

    public static final String ACHIEVEMENT_TYPE = "ACHIEVEMENT_TYPE";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
