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
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : PostdoctorApplyInfo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostdoctorApplyInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "自助申请信息ID")
    private Long postdoctorApplyId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "申请退站时间")
    private LocalDate applyPushTime;

    @ApiModelProperty(value = "退站档案寄存")
    private String pushArchivesInfo;

    @ApiModelProperty(value = "退站备注")
    private String pushRemark;

    @ApiModelProperty(value = "退站申请附件URL ")
    private String attachmentPushUrl;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "申请延期时间")
    private LocalDate applyDelayTime;

    @ApiModelProperty(value = "延期时长（月）")
    private String delayDuration;

    @ApiModelProperty(value = "延期备注 ")
    private String delayRemark;

    @ApiModelProperty(value = "延期申请附件URL ")
    private String attachmentDelayUrl;

    @ApiModelProperty(value = "新导师ID")
    private Long newTeacherId;

    @ApiModelProperty(value = "新导师姓名")
    private String newTeacherName;

    @ApiModelProperty(value = "更换导师理由")
    private String replaceRemark;

    @ApiModelProperty(value = "更换导师附件URL  ")
    private String attachmentReplaceUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "最后更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;

}
