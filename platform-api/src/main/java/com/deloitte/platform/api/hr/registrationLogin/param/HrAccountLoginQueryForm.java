package com.deloitte.platform.api.hr.registrationLogin.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-19
 * @Description :   HrAccount查询from对象
 * @Modified :
 */
@ApiModel("HrAccount查询表单")
@Data
public class HrAccountLoginQueryForm extends BaseQueryForm<HrAccountLoginQueryParam>  {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String verificationCode;

    @ApiModelProperty(value = "随机码")
    private String randomCode;

}
