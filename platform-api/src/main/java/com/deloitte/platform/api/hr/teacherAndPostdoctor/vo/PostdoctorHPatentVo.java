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
 * @Description : PostdoctorHPatent返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorHPatentVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "专利名称")
    private String patentName;

    @ApiModelProperty(value = "专利编号")
    private String patentCode;

    @ApiModelProperty(value = "授予国家")
    private String fromCountry;

    @ApiModelProperty(value = "授予时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromTime;

    @ApiModelProperty(value = "专利排名")
    private String patentRanking;

    @ApiModelProperty(value = "博士后姓名")
    private String postdoctorName;

    @ApiModelProperty(value = "专利附件URL")
    private String attachmentPatentUrl;

    @ApiModelProperty(value = "附件名称")
    private String attachmentName;


}
