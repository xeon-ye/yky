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
 * @Description : PostdoctorHBook返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorHBookVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "著作名称")
    private String bookName;

    @ApiModelProperty(value = "出版单位")
    private String press;

    @ApiModelProperty(value = "著作本人排名")
    private String bookRanking;

    @ApiModelProperty(value = "著作出版时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookPublishTime;

    @ApiModelProperty(value = "博士后姓名")
    private String postdoctorName;

    @ApiModelProperty(value = "著作附件URL")
    private String attachmentBookUrl;

    @ApiModelProperty(value = "附件名称")
    private String attachmentName;


}
