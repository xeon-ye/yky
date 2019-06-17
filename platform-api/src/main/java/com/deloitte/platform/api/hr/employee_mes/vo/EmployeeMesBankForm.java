package com.deloitte.platform.api.hr.employee_mes.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-06-05
 * @Description : EmployeeMesBank新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeMesBank表单")
@Data
public class EmployeeMesBankForm extends BaseForm {
    private static final long serialVersionUID = 1L;


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

}
