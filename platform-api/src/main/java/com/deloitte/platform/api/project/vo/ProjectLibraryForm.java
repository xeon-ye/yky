package com.deloitte.platform.api.project.vo;

import com.deloitte.platform.api.project.param.ProjectsQueryParam;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/7 14:20
 * @Description :
 * @Modified:
 */
@ApiModel("项目库查询Form")
@Data
public class ProjectLibraryForm extends BaseQueryForm<ProjectsQueryParam> {

    private static final Long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "计划执行年度")
    private String playYear;

    @ApiModelProperty(value = "项目周期（年）")
    private String projectCycle;

    @ApiModelProperty(value = "项目单位")
    private String operationOu;

    @ApiModelProperty(value = "项目状态Code")
    private String projectStatusCode;

    @ApiModelProperty(value = "项目状态")
    private String projectStatusName;
    
}
