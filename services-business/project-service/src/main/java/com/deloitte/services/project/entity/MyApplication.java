package com.deloitte.services.project.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/9 10:20
 * @Description :
 * @Modified:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MyApplication", description = "我的申请MyApplication实体")
public class MyApplication {


    @ApiModelProperty(value = "项目ID")
    private String id;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类型Code")
    private String projectTypeCode;

    @ApiModelProperty(value = "项目类型")
    private String projectTypeName;

    @ApiModelProperty(value = "项目计划执行年度")
    private String planYear;

    @ApiModelProperty(value = "项目状态Code")
    private String projectStatusCode;

    @ApiModelProperty(value = "项目状态")
    private String projectStatusName;

    @ApiModelProperty(value = "评审状态Code")
    private String reviewState;

    @ApiModelProperty(value = "评审状态")
    private String reviewResult;

    @ApiModelProperty(value = "申报年度")
    private String theApplicationYear;

}
