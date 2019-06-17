package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckTemplate新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckTemplate表单")
@Data
public class CheckTemplateForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核模板名称")
    private String checkTemplateName;

    @ApiModelProperty(value = "考核表类型")
    private Long checkTableTypeId;

    @ApiModelProperty(value = "评估方式")
    private String assessMode;


    @ApiModelProperty(value = "权重")
    private Long weight;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "评估方式列表")
    private List<CheckEvaluateModeForm> evaluateModeList;




}
