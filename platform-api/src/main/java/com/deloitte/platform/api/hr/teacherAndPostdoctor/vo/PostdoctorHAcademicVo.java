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
 * @Description : PostdoctorHAcademic返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorHAcademicVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学术报告（论文）名称")
    private String academicReportName;

    @ApiModelProperty(value = "会议名称")
    private String meetingName;

    @ApiModelProperty(value = "会议时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate meetingTime;

    @ApiModelProperty(value = "会议地点")
    private String meetingPlace;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "博士后姓名")
    private String postdoctorName;

    @ApiModelProperty(value = "学术报告附件URL")
    private String attachmentReportUrl;

    @ApiModelProperty(value = "附件名称")
    private String attachmentName;


}
