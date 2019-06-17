package com.deloitte.services.srpmp.project.base.entity;

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
 * 项目预算关联表
 * </p>
 *
 * @author lixin
 * @since 2019-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_PROJECT_BUDGET_DETAIL")
@ApiModel(value="SrpmsProjectBudgetDetail对象", description="项目预算关联表")
public class SrpmsProjectBudgetDetail extends Model<SrpmsProjectBudgetDetail> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编号")
    @TableField("PROJECT_NUM")
    private String projectNum;

    @ApiModelProperty(value = "预算类型CODE")
    @TableField("BUDGET_CATEGORY")
    private String budgetCategory;

    @ApiModelProperty(value = "预算年度")
    @TableField("BUDGET_YEAR")
    private Integer budgetYear;

    @ApiModelProperty(value = "任务名称")
    @TableField("TASK_NAME")
    private String taskName;

    @ApiModelProperty(value = "序号")
    @TableField("SERIAL")
    private Integer serial;

    @ApiModelProperty(value = "参与任务的单位名称")
    @TableField("TASK_DEPT_NAME")
    private String taskDeptName;

    @ApiModelProperty(value = "任务负责人ID")
    @TableField("TASK_LEAD_PERSON_ID")
    private Long taskLeadPersonId;

    @ApiModelProperty(value = "任务参与人员ID")
    @TableField("TASK_JOIN_PERSON_ID")
    private Long taskJoinPersonId;

    @ApiModelProperty(value = "预算金额")
    @TableField("BUDGET_AMOUNT")
    private Double budgetAmount;

    @ApiModelProperty(value = "单位名称")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "组织机构代码")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "承担单位类型")
    @TableField("DEPT_CATEGORY")
    private String deptCategory;

    @ApiModelProperty(value = "单位承担的任务分工")
    @TableField("DEPT_TASK_CONTENT")
    private String deptTaskContent;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "预算明细")
    @TableField("BUDGET_DETAIL")
    private String budgetDetail;

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

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String PROJECT_NUM = "PROJECT_NUM";

    public static final String BUDGET_CATEGORY = "BUDGET_CATEGORY";

    public static final String BUDGET_YEAR = "BUDGET_YEAR";

    public static final String TASK_NAME = "TASK_NAME";

    public static final String SERIAL = "SERIAL";

    public static final String TASK_DEPT_NAME = "TASK_DEPT_NAME";

    public static final String TASK_LEAD_PERSON_ID = "TASK_LEAD_PERSON_ID";

    public static final String TASK_JOIN_PERSON_ID = "TASK_JOIN_PERSON_ID";

    public static final String BUDGET_AMOUNT = "BUDGET_AMOUNT";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String ORG_CODE = "ORG_CODE";

    public static final String DEPT_CATEGORY = "DEPT_CATEGORY";

    public static final String DEPT_TASK_CONTENT = "DEPT_TASK_CONTENT";

    public static final String REMARK = "REMARK";

    public static final String BUDGET_DETAIL = "BUDGET_DETAIL";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
