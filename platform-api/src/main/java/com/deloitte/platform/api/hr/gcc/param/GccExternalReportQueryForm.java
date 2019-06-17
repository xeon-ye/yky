package com.deloitte.platform.api.hr.gcc.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("GccExternalHelp项目统计表单")
@Data
public class GccExternalReportQueryForm {

    @ApiModelProperty(value = "援助类型 0西部之光，1 援疆 ，2 博士服务团")
    private String aidType;

    @ApiModelProperty(value = "开始年份")
    private String startYear;

    @ApiModelProperty(value = "结束年份")
    private String endYear;
}
