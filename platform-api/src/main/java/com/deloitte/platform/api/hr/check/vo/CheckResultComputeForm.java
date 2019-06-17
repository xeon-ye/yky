package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckResult新增修改form对象
 * @Modified :
 */
@ApiModel("CheckResultComputeForm")
@Data
public class CheckResultComputeForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核关系id")
    private Long checkRelationId;

    @ApiModelProperty(value = "计算方式")
    private String computeType;

    @ApiModelProperty(value = "业绩考核测评通知id")
    private String checkEvaluateNotifyId;

}
