package com.deloitte.platform.api.srpmp.project.accept.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description :   SrpmsProjectAcceptAcademy查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectAcceptAcademy查询表单")
@Data
public class SrpmsProjectAcceptAcademyQueryForm extends BaseQueryForm<SrpmsProjectAcceptAcademyQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

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
