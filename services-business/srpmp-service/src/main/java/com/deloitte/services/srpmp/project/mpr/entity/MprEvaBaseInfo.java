package com.deloitte.services.srpmp.project.mpr.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.sql.Clob;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 中期绩效考评信息表项目基本情况表
 * </p>
 *
 * @author LIJUN
 * @since 2019-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MPR_EVA_BASE_INFO")
@ApiModel(value="MprEvaBaseInfo对象", description="中期绩效考评信息表项目基本情况表")
public class MprEvaBaseInfo extends Model<MprEvaBaseInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NO")
    private String projectNo;

    @ApiModelProperty(value = "项目名称")
    @TableField("PROJECT_NAME")
    private String projectName;

    @ApiModelProperty(value = "项目目标")
    @TableField("PROJECT_OBJECTIVES")
    private String projectObjectives;

    @ApiModelProperty(value = "岗位数")
    @TableField("JOB_QUANTITY")
    private String jobQuantity;

    @ApiModelProperty(value = "经费预算")
    @TableField("BUDGET")
    private String budget;

    @ApiModelProperty(value = "执行周期（开始时间）")
    @TableField("EXECUTION_START_TIME")
    private LocalDateTime executionStartTime;

    @ApiModelProperty(value = "执行周期（结束时间）")
    @TableField("EXECUTION_END_TIME")
    private LocalDateTime executionEndTime;

    @ApiModelProperty(value = "项目类别")
    @TableField("PROJECT_CATEGORY")
    private String projectCategory;

    @ApiModelProperty(value = "项目分类")
    @TableField("PROJECT_CLASSIFICATION")
    private String projectClassification;

    @ApiModelProperty(value = "牵头单位")
    @TableField("LEAD_UNIT")
    private String leadUnit;

    @ApiModelProperty(value = "参加单位")
    @TableField("TAKE_UNIT")
    private String takeUnit;

    @ApiModelProperty(value = "首席专家")
    @TableField("CHIEF_SPECIALIST")
    private String chiefSpecialist;

    @ApiModelProperty(value = "共同首席专家")
    @TableField("JOINT_CHIEF_SPECIALIST")
    private String jointChiefSpecialist;

    @ApiModelProperty(value = "进展情况")
    @TableField("PROGRESS_STATE")
    private String progressState;

    @ApiModelProperty(value = "实施情况")
    @TableField("WORK_STATE")
    private String workState;

    @ApiModelProperty(value = "博士后人数")
    @TableField("POSTDOCTORAL_NUM")
    private String postdoctoralNum;

    @ApiModelProperty(value = "博士生人数")
    @TableField("DOCTORAL_NUM")
    private String doctoralNum;

    @ApiModelProperty(value = "硕士生人数")
    @TableField("MASTER_NUM")
    private String masterNum;

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

    @ApiModelProperty(value = "附件一统计信息")
    @TableField("ANNEX_STAT")
    private String annexStat;

    @ApiModelProperty(value = "中期绩效报告处理状态")
    @TableField("PROCESS_STATUS")
    private String processStatus;

    @ApiModelProperty(value = "项目类型")
    @TableField("PROJECT_TYPE")
    private String projectType;

    @ApiModelProperty(value = "项目类型code，跟PROJECT_TYPE无关")
    @TableField("PROJECT_TYPE_CODE")
    private String projectTypeCode;

    @ApiModelProperty(value = "报告类型")
    @TableField("REPORT_TYPE")
    private String reportType;

    @ApiModelProperty(value = "申请人所属单位CODE")
    @TableField("APPLY_DEPT_CODE")
    private String applyDeptCode;

    @ApiModelProperty(value = "其他情况")
    @TableField("OTHER_CASE")
    private String otherCase;

    @ApiModelProperty(value = "报告ID")
    @TableField("REPORT_ID")
    private Long reportId;

    @ApiModelProperty(value = "报告年份")
    @TableField("REPORT_YEAR")
    private Long reportYear;

    @ApiModelProperty(value = "报告标题")
    @TableField("REPORT_TITLE")
    private String reportTitle;

    @ApiModelProperty(value = "其它类型名称")
    @TableField("REP_OTHER_TYPE")
    private String repOtherType;

    public static final String ID = "ID";

    public static final String PROJECT_NO = "PROJECT_NO";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String PROJECT_OBJECTIVES = "PROJECT_OBJECTIVES";

    public static final String JOB_QUANTITY = "JOB_QUANTITY";

    public static final String BUDGET = "BUDGET";

    public static final String EXECUTION_START_TIME = "EXECUTION_START_TIME";

    public static final String EXECUTION_END_TIME = "EXECUTION_END_TIME";

    public static final String PROJECT_CATEGORY = "PROJECT_CATEGORY";

    public static final String PROJECT_CLASSIFICATION = "PROJECT_CLASSIFICATION";

    public static final String LEAD_UNIT = "LEAD_UNIT";

    public static final String TAKE_UNIT = "TAKE_UNIT";

    public static final String CHIEF_SPECIALIST = "CHIEF_SPECIALIST";

    public static final String JOINT_CHIEF_SPECIALIST = "JOINT_CHIEF_SPECIALIST";

    public static final String PROGRESS_STATE = "PROGRESS_STATE";

    public static final String WORK_STATE = "WORK_STATE";

    public static final String POSTDOCTORAL_NUM = "POSTDOCTORAL_NUM";

    public static final String DOCTORAL_NUM = "DOCTORAL_NUM";

    public static final String MASTER_NUM = "MASTER_NUM";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String ANNEX_STAT = "ANNEX_STAT";

    public static final String PROCESS_STATUS = "PROCESS_STATUS";

    public static final String PROJECT_TYPE = "PROJECT_TYPE";

    public static final String PROJECT_TYPE_CODE = "PROJECT_TYPE_CODE";

    public static final String REPORT_TYPE = "REPORT_TYPE";

    public static final String APPLY_DEPT_CODE = "APPLY_DEPT_CODE";

    public static final String OTHER_CASE = "OTHER_CASE";

    public static final String REPORT_ID = "REPORT_ID";

    public static final String REPORT_YEAR = "REPORT_YEAR";

    public static final String REPORT_TITLE = "REPORT_TITLE";

    public static final String REP_OTHER_TYPE = "REP_OTHER_TYPE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
