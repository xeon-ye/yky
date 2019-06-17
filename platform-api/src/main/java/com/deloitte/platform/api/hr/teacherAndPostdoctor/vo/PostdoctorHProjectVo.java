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
 * @Description : PostdoctorHProject返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorHProjectVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目课题名称")
    private String projectName;

    @ApiModelProperty(value = "项目来源")
    private String source;

    @ApiModelProperty(value = "科研项目经费（万元）")
    private Long projectFunds;

    @ApiModelProperty(value = "项目开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectStartTime;

    @ApiModelProperty(value = "项目结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectEndTime;

    @ApiModelProperty(value = "项目级别")
    private String projectLevel;

    @ApiModelProperty(value = "担任角色（负责人/主要参与/一般参与）")
    private String assumeRole;

    @ApiModelProperty(value = "项目附件URL")
    private String attachmentProjectUrl;

    @ApiModelProperty(value = "博士后姓名")
    private String postdoctorName;

    @ApiModelProperty(value = "附件名称")
    private String attachmentName;

}
