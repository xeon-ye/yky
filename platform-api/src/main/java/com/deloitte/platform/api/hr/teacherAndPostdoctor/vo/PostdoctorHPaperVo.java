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
 * @Description : PostdoctorHPaper返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorHPaperVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "论文题目")
    private String paperName;

    @ApiModelProperty(value = "发表刊物名称")
    private String publishPress;

    @ApiModelProperty(value = "影响因子")
    private String influenceFactor;

    @ApiModelProperty(value = "SCI/EI收录/核心期刊")
    private String record;

    @ApiModelProperty(value = "论文出版时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate paperPublishTime;

    @ApiModelProperty(value = "论文排名")
    private String paperRanking;

    @ApiModelProperty(value = "博士后姓名")
    private String postdoctorName;

    @ApiModelProperty(value = "论文附件URL")
    private String attachmentPaperUrl;

    @ApiModelProperty(value = "附件名称")
    private String attachmentName;


}
