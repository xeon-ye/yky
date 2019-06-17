package com.deloitte.platform.api.hr.reportManagement.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-08
 * @Description : HrAccount新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrAccount表单")
@Data
public class HrAccountReportForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "注册类型：1.应届毕业生 2.社会人员")
    private String type;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "员工编号")
    private String staffCode;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

    @ApiModelProperty(value = "确认密码")
    private String confirmPassword;

    @ApiModelProperty(value = "验证码")
    private String verificationCode;

    @ApiModelProperty(value = "随机码")
    private String randomCode;

}
