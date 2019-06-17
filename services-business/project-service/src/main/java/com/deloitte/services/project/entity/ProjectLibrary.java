package com.deloitte.services.project.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/7 10:02
 * @Description :
 * @Modified
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProjectLibrary对象", description = "项目库查询对象")
public class ProjectLibrary {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目类型Code")
    private String projectTypeCode;

    @ApiModelProperty(value = "项目类型")
    private String projectTypeName;

    @ApiModelProperty(value = "项目申报年度")
    private String theApplicationYear;

    @ApiModelProperty(value = "项目执行计划年度")
    private String planYear;

    @ApiModelProperty(value = "项目周期")
    private String projectCycle;

    @ApiModelProperty(value = "单位名称")
    private String operationOu;

    @ApiModelProperty(value = "项目状态Code")
    private String projectStatusCode;

    @ApiModelProperty(value = "项目状态")
    private String projectStatusName;

    @ApiModelProperty(value = "院校排序（REVIEW表中查询得到）")
    private String schoolPriority;

    @ApiModelProperty(value = "总预算（PROJECT_BUDGET表中查询计算得到）")
    private String totalBudget;

    @ApiModelProperty(value = "支出计划（PROJECT_BUDGET）")
    private String planPay;
}
