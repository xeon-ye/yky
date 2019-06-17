package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   PostdoctorApplyInfo查询from对象
 * @Modified :
 */
@ApiModel("PostdoctorApplyInfo查询表单")
@Data
public class PostdoctorApplyInfoQueryForm extends BaseQueryForm<PostdoctorApplyInfoQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "自助申请信息ID")
    private Long postdoctorApplyId;

    @ApiModelProperty(value = "申请退站时间")
    private LocalDateTime applyPushTime;

    @ApiModelProperty(value = "退站档案寄存")
    private String pushArchivesInfo;

    @ApiModelProperty(value = "退站备注")
    private String pushRemark;

    @ApiModelProperty(value = "退站申请附件URL")
    private String attachmentPushUrl;

    @ApiModelProperty(value = "申请延期时间")
    private LocalDateTime applyDelayTime;

    @ApiModelProperty(value = "延期时长（天）")
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

    @ApiModelProperty(value = "更换导师附件URL")
    private String attachmentReplaceUrl;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "最后更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;
}
