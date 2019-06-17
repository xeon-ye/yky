package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-11
 * @Description : CheckEvaluateMode新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckEvaluateMode表单")
@Data
public class CheckEvaluateModeForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "修改时id")
    private String id;

    @ApiModelProperty(value = "考核模板id")
    private Long checkTemplateId;

    @ApiModelProperty(value = "评估类别")
    private String modeType;

    @ApiModelProperty(value = "选项名称")
    private String optionName;

    @ApiModelProperty(value = "最小值")
    private String minScore;

    @ApiModelProperty(value = "最大值")
    private String maxScore;

    @ApiModelProperty(value = "步长")
    private String stepLength;

    @ApiModelProperty(value = "排序")
    private Long orderNumber;

    @ApiModelProperty(value = "")
    private String optionType;

    @ApiModelProperty(value = "分值")
    private String optionScore;

    @ApiModelProperty(value = "是否默认")
    private String isDefault;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
