package com.deloitte.platform.api.fssc.ppc.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description : ContractInformation新增修改form对象
 * @Modified :
 */
@ApiModel("新增ContractInformation表单")
@Data
public class ContractInformationForm extends BaseForm {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "无ID新增有id修改")
    private Long id;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "合同ID",required = true)
    private Long contractId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "履行计划ID")
    private Long travelPlanId;

}
