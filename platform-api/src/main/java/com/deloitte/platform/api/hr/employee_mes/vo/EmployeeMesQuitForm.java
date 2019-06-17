package com.deloitte.platform.api.hr.employee_mes.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-06-04
 * @Description : EmployeeMesQuit新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeMesQuit表单")
@Data
public class EmployeeMesQuitForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "辞职原因")
    private String quitReason;

    @ApiModelProperty(value = "辞职说明")
    private String quitExplain;

    @ApiModelProperty(value = "最后工作日期")
    private LocalDate quitDate;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
