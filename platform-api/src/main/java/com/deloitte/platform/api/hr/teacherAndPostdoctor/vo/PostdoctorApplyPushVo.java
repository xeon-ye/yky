package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : FlowStation返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorApplyPushVo extends PostdoctorApplyBaseVo {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "申请退站时间")
    private LocalDate applyPushTime;

    @ApiModelProperty(value = "退站档案寄存")
    private String pushArchivesInfo;

    @ApiModelProperty(value = "退站备注")
    private String pushRemark;

    @ApiModelProperty(value = "退站申请附件URL ")
    private String attachmentPushUrl;


}
