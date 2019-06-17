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
 * @Description : PostdoctorHFund返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorHFundVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "基金名称")
    private String fundName;

    @ApiModelProperty(value = "获资助金额(万元)")
    private Long supportFunds;

    @ApiModelProperty(value = "获取时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate getTime;

    @ApiModelProperty(value = "获资助等级（一等、二等、三等）")
    private String getLevel;

    @ApiModelProperty(value = "是否结题（1是，2否）")
    private String isOver;

    @ApiModelProperty(value = "基金附件URL")
    private String attachmentFundUrl;

    @ApiModelProperty(value = "附件名称")
    private String attachmentName;

}
