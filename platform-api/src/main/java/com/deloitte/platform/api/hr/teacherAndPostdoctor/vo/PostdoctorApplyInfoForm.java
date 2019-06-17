package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : PostdoctorApplyInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增PostdoctorApplyInfo表单")
@Data
public class PostdoctorApplyInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "自助申请信息ID")
    private Long postdoctorApplyId;

    @ApiModelProperty(value = "申请退站时间")
    private LocalDate applyPushTime;

    @ApiModelProperty(value = "退站档案寄存")
    private String pushArchivesInfo;

    @ApiModelProperty(value = "退站备注")
    private String pushRemark;

    @ApiModelProperty(value = "退站申请附件URL")
    private String attachmentPushUrl;

    @ApiModelProperty(value = "申请延期时间")
    private LocalDate applyDelayTime;

    @ApiModelProperty(value = "延期时长（月）")
    private String delayDuration;

    @ApiModelProperty(value = "延期备注 ")
    private String delayRemark;

    @ApiModelProperty(value = "延期申请附件URL")
    private String attachmentDelayUrl;

    @ApiModelProperty(value = "新导师ID")
    private Long newTeacherId;

    @ApiModelProperty(value = "新导师姓名")
    private String newTeacherName;

    @ApiModelProperty(value = "更换导师理由")
    private String replaceRemark;

    @ApiModelProperty(value = "更换导师附件URL ")
    private String attachmentReplaceUrl;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;

}
