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
public class PostdoctorApplyDelayVo extends PostdoctorApplyBaseVo {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "申请延期时间")
    private LocalDate applyDelayTime;

    @ApiModelProperty(value = "延期时长（月）")
    private String delayDuration;

    @ApiModelProperty(value = "延期备注 ")
    private String delayRemark;

    @ApiModelProperty(value = "延期申请附件URL ")
    private String attachmentDelayUrl;


}
