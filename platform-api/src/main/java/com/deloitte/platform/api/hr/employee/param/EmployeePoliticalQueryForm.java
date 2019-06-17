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
 * @Description :   EmployeePolitical查询from对象
 * @Modified :
 */
@ApiModel("EmployeePolitical查询表单")
@Data
public class EmployeePoliticalQueryForm extends BaseQueryForm<EmployeePoliticalQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

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
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;
}
