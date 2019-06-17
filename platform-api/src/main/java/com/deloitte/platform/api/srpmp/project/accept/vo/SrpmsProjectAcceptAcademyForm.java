package com.deloitte.platform.api.srpmp.project.accept.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description : SrpmsProjectAcceptAcademy新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectAcceptAcademy表单")
@Data
public class SrpmsProjectAcceptAcademyForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "验收ID")
    private Long acceptId;

    @ApiModelProperty(value = "项目考核指标")
    private String assessmentIndicators;

    @ApiModelProperty(value = "研究内容考核指标完成情况")
    private String projectCompletionCase;

    @ApiModelProperty(value = "分析未完成原因")
    private String projectUnfinishReason;

    @ApiModelProperty(value = "项目实施经验")
    private String projectImplementExperience;

}
