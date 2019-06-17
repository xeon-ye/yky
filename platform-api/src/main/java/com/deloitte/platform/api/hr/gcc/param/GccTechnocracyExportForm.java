package com.deloitte.platform.api.hr.gcc.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("GccTechnocracy导出表单")
@Data
public class GccTechnocracyExportForm {

    @ApiModelProperty(value = "学员编号")
    private Long userId;

    @ApiModelProperty(value = "学员姓名")
    private String userName;

    @ApiModelProperty(value = "所在单位")
    private String unit;

    @ApiModelProperty(value = "申请年度")
    private String year;

    @ApiModelProperty(value = "项目")
    private String project;

    @ApiModelProperty(value = "类型")
    private String type;
}
