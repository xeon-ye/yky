package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-25
 * @Description : HrZpcpAssessment新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrZpcpAssessment表单")
@Data
public class HrZpcpAssessmentForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "考核类型")
    private String assessmentType;

    @ApiModelProperty(value = "考核时间")
    private LocalDate assessmentTime;

    @ApiModelProperty(value = "考核结果")
    private String assessmentResult;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "当前状态")
    private String status;

    @ApiModelProperty(value = "在聘信息表id")
    private String infoId;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentForm attachmentForm;
}
