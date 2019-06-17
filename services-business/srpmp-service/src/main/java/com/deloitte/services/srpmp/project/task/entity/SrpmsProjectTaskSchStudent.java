package com.deloitte.services.srpmp.project.task.entity;

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
 * 任务书-校基科费-学生项目
 * </p>
 *
 * @author Apeng
 * @since 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_TASK_SCH_STUDENT")
@ApiModel(value="SrpmsProjectTaskSchStudent对象", description="任务书-校基科费-学生项目")
public class SrpmsProjectTaskSchStudent extends Model<SrpmsProjectTaskSchStudent> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "项目组织单位")
    @TableField("PROJECT_ORGANIZING_UNIT")
    private String projectOrganizingUnit;

    @ApiModelProperty(value = "项目承担单位")
    @TableField("PROJECT_COMMITMENT_UNIT")
    private String projectCommitmentUnit;

    @ApiModelProperty(value = "项目类别")
    @TableField("BASE_SCIENCE_STU_TYPE")
    private String baseScienceStuType;

    @ApiModelProperty(value = "项目执行开始时间")
    @TableField("PROJECT_ACTION_DATE_START")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行结束时间 ")
    @TableField("PROJECT_ACTION_DATE_END")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "项目摘要")
    @TableField("PROJECT_ABSTRACT")
    private String projectAbstract;

    @ApiModelProperty(value = "提交成果考核指标")
    @TableField("RESULT_ASSESSMENT_INDICATORS")
    private String resultAssessmentIndicators;

    @ApiModelProperty(value = "年度计划")
    @TableField("RESEARCH_YEAR_PLAN")
    private String researchYearPlan;

    @ApiModelProperty(value = "经费预算")
    @TableField("APPLY_FUNDS")
    private Double applyFunds;

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

    public static final String PROJECT_ORGANIZING_UNIT = "PROJECT_ORGANIZING_UNIT";

    public static final String PROJECT_COMMITMENT_UNIT = "PROJECT_COMMITMENT_UNIT";

    public static final String BASE_SCIENCE_STU_TYPE = "BASE_SCIENCE_STU_TYPE";

    public static final String PROJECT_ACTION_DATE_START = "PROJECT_ACTION_DATE_START";

    public static final String PROJECT_ACTION_DATE_END = "PROJECT_ACTION_DATE_END";

    public static final String PROJECT_ABSTRACT = "PROJECT_ABSTRACT";

    public static final String RESULT_ASSESSMENT_INDICATORS = "RESULT_ASSESSMENT_INDICATORS";

    public static final String YEAR_ASSESSMENT_INDICATORS = "RESEARCH_YEAR_PLAN";

    public static final String APPLY_FUNDS = "APPLY_FUNDS";

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
