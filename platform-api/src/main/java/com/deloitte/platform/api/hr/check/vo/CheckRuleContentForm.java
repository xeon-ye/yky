package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description : CheckRuleContent新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckRuleContent表单")
@Data
public class CheckRuleContentForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "内容id修改时传参")
    private String id;

    @ApiModelProperty(value = "考核等级规则id")
    private Long checkRuleId;

    @ApiModelProperty(value = "等级名称")
    private String levelName;

    @ApiModelProperty(value = "最小分数")
    private Long minScore;

    @ApiModelProperty(value = "最大分数")
    private Long maxScore;

    @ApiModelProperty(value = "比例")
    private Double ratio;

    @ApiModelProperty(value = "人数")
    private Long peopleNumber;

    @ApiModelProperty(value = "排序")
    private Long orderNumber;

    @ApiModelProperty(value = "计算系数")
    private Double computeRatio;

    @ApiModelProperty(value = "描述")
    private String describe;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
