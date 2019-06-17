package com.deloitte.platform.api.hr.employee_mes.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-06-05
 * @Description :   EmployeeMesBank查询from对象
 * @Modified :
 */
@ApiModel("EmployeeMesBank查询表单")
@Data
public class EmployeeMesBankQueryForm extends BaseQueryForm<EmployeeMesBankQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "账户状态")
    private String status;

    @ApiModelProperty(value = "账户类型")
    private String type;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "开户行")
    private String bankOpen;

    @ApiModelProperty(value = "银行账号")
    private String bankNumber;

    @ApiModelProperty(value = "银行账户")
    private String bankAccount;

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
