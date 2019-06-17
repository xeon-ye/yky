package com.deloitte.platform.api.hr.retireRehiring.param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("提醒查询表单")
@Data
public class RetireRehiringQueryForm  {


    @ApiModelProperty(value = "院校-0/部门-1")
    private String instOrDept;

    @ApiModelProperty(value = "年-0/月-1")
    private String yearOrMonth;

    @ApiModelProperty(value = "部门编号")
    private String deptCode;

    @ApiModelProperty(value = "部门名称")
    private String deptName;
}
