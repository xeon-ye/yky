package com.deloitte.platform.api.hr.reportManagement.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-08
 * @Description :   HrAccount查询from对象
 * @Modified :
 */
@ApiModel("HrAccount查询表单")
@Data
public class HrAccountReportQueryForm extends BaseQueryForm<HrAccountReportQueryParam>  {


    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String verificationCode;

    @ApiModelProperty(value = "随机码")
    private String randomCode;

}
