package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : jetvae
 * @Date : Create in 2019-05-09
 * @Description : PostdoctorHHonor返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorHHonorVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "博士后姓名")
    private String postdoctorName;

    @ApiModelProperty(value = "进站时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate pullTime;

    @ApiModelProperty(value = "奖励及荣誉称号名称")
    private String honorName;

    @ApiModelProperty(value = "批准时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate approvalTime;

    @ApiModelProperty(value = "荣誉附件URL")
    private String attachmentHonorUrl;

    @ApiModelProperty(value = "附件名称")
    private String attachmentName;


}
