package com.deloitte.platform.api.srpmp.project.task.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : zhouchen
 * @Date : Create in 2019-04-17
 * @Description :   SrpmsProjectTaskReform查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectTaskReform查询表单")
@Data
public class SrpmsProjectTaskReformQueryForm extends BaseQueryForm<SrpmsProjectTaskReformQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "经费预算")
    private Double applyFunds;

    @ApiModelProperty(value = "项目执行开始时间")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行结束时间 ")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "项目申请人ID")
    private Long projectApplicantId;

    @ApiModelProperty(value = "研究内容")
    private String contentTargetProblem;

    @ApiModelProperty(value = "预期目标")
    private String projectExpectTarget;

    @ApiModelProperty(value = "主要技术特点和创新点")
    private String projectTechnicalInnovation;

    @ApiModelProperty(value = "考核指标")
    private String assessmentIndicators;

    @ApiModelProperty(value = "年度计划")
    private String researchYearPlan;

    @ApiModelProperty(value = "项目摘要")
    private String projectAbstract;

    @ApiModelProperty(value = "创建时间 ")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人 ")
    private String createBy;

    @ApiModelProperty(value = "更新时间 ")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人 ")
    private String updateBy;

    @ApiModelProperty(value = "预算说明")
    private String budgetDescription;
}
