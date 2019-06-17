package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckRule新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckRule表单")
@Data
public class CheckRuleForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "等级规则名称")
    private String ruleName;

    @ApiModelProperty(value = "等级规则类型")
    private String ruleType;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "等级规则内容")
    private List<CheckRuleContentForm> checkRuleContentList;


}
