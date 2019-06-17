package com.deloitte.platform.api.hr.employee_mes.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-06-04
 * @Description :   EmployeeMesQuit查询from对象
 * @Modified :
 */
@ApiModel("EmployeeMesQuit查询表单")
@Data
public class EmployeeMesQuitQueryForm extends BaseQueryForm<EmployeeMesQuitQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
