package com.deloitte.platform.api.hr.registrationResultEnquiry.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-04
 * @Description : HrAccount返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HrAccountEnquiryVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String verificationCode;

}
