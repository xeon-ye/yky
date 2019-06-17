package com.deloitte.platform.api.hr.employee.param;


import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-05-20
 * @Description :   EmployeeWages查询from对象
 * @Modified :
 */
@ApiModel("EmployeeWages查询表单")
@Data
public class EmployeeWagesQueryForm extends BaseQueryForm<EmployeeWagesQueryParam> {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "员工编号")
    private String employeeCode;

    @ApiModelProperty(value = "工资年份")
    private String yearDate;

    @ApiModelProperty(value = "工资月份")
    private String monthDate;

    @ApiModelProperty(value = "项目名称")
    private String label;

    @ApiModelProperty(value = "金额（元）")
    private Double money;

    @ApiModelProperty(value = "项目类型")
    private String labelType;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
