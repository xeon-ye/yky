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
 * @Description : EmployeeLastwork新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeLastwork表单")
@Data
public class EmployeeLastworkForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "${field.comment}")
    private Long empId;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "${field.comment}")
    private String company;

    @ApiModelProperty(value = "${field.comment}")
    private String dep;

    @ApiModelProperty(value = "${field.comment}")
    private String position;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

}
