package com.deloitte.platform.api.hr.teacherAndPostdoctor.vo;
import com.deloitte.platform.api.hr.common.util.DateFarmatUtil;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description : PostdoctorApply新增修改form对象
 * @Modified :
 */
@ApiModel("新增博士后自助申请表单")
@Data
public class PostdoctorApplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "申请人ID")
    private Long postdoctorId;

    @ApiModelProperty(value = "申请信息ID")
    private String appId;

    @NotNull(message = "申请类型不能为空")
    @Range(min = 1,max = 3,message = "申请类型参数值错误")
    @ApiModelProperty(value = "类型（1导师更换申请，2延期申请，3退站申请）")
    private Integer type;

    @NotNull(message = "保存类型不能为空")
    @Range(min = 1,max = 2,message = "保存类型值错误")
    @ApiModelProperty(value = "保存类型（1保存，2提交）")
    private Integer keep;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "申请退站时间(格式：2019-05-20)")
    private LocalDate applyPushTime;

    @ApiModelProperty(value = "退站档案寄存")
    private String pushArchivesInfo;

    @ApiModelProperty(value = "退站备注")
    private String pushRemark;

    @ApiModelProperty(value = "退站申请附件URL")
    private String attachmentPushUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "申请延期时间(格式：2019-05-20)")
    private LocalDate applyDelayTime;

    @ApiModelProperty(value = "延期时长（月）")
    private String delayDuration;

    @ApiModelProperty(value = "延期备注 ")
    private String delayRemark;

    @ApiModelProperty(value = "延期申请附件URL")
    private String attachmentDelayUrl;

    @ApiModelProperty(value = "新导师code")
    private String newTeacherId;

    @ApiModelProperty(value = "新导师姓名")
    private String newTeacherName;

    @ApiModelProperty(value = "原导师code")
    private String oldTeacherId;

    @ApiModelProperty(value = "原导师姓名")
    private String oldTeacherName;

    @ApiModelProperty(value = "更换导师理由")
    private String replaceRemark;

    @ApiModelProperty(value = "更换导师附件URL")
    private String attachmentReplaceUrl;

}
