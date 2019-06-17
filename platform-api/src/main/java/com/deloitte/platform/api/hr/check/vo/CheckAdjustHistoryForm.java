package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckAdjustHistory新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckAdjustHistory表单")
@Data
public class CheckAdjustHistoryForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核结果id")
    private Long checkResultId;

    @ApiModelProperty(value = "调整等级")
    private String adjustLevel;

    @ApiModelProperty(value = "调整原因")
    private String adjustReason;

    @ApiModelProperty(value = "系统等级")
    private String systemLevel;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
