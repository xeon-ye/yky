package com.deloitte.platform.api.hr.employee.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-03
 * @Description :   EmployeeLastwork查询from对象
 * @Modified :
 */
@ApiModel("EmployeeLastwork查询表单")
@Data
public class EmployeeLastworkQueryForm extends BaseQueryForm<EmployeeLastworkQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

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
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateTime;
}
