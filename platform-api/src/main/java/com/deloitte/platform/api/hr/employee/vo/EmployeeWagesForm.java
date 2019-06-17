package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-05-20
 * @Description : EmployeeWages新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeWages表单")
@Data
public class EmployeeWagesForm extends BaseForm {
    private static final long serialVersionUID = 1L;


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

}
