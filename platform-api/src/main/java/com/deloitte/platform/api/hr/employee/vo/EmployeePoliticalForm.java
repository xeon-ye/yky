package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-03
 * @Description : EmployeePolitical新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeePolitical表单")
@Data
public class EmployeePoliticalForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "员工表ID")
    private Long empId;

    @ApiModelProperty(value = "政治面貌")
    private String politicalOutlook;

    @ApiModelProperty(value = "入党/团时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "转正日期")
    private LocalDateTime turnFormal;

    @ApiModelProperty(value = "介绍人")
    private String introducer;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

}
