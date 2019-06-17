package com.deloitte.platform.api.fssc.basecontract.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description : BaseContractInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增BaseContractInfo表单")
@Data
public class BaseContractInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "是否缴纳印花税")
    private String isPayStampDuty;

    @ApiModelProperty(value = "合同类型(印花税)")
    private String contractTypeStampDuty;

    @ApiModelProperty(value = "应税凭证名称")
    private String nameOfTaxable;

    @ApiModelProperty(value = "印花税税额")
    private BigDecimal stampDuty;

    @ApiModelProperty(value = "通用印花税税率")
    private BigDecimal generalStampDutyRate;

    @ApiModelProperty(value = "计税金额或件数")
    private BigDecimal taxAmountOrPieces;



}
