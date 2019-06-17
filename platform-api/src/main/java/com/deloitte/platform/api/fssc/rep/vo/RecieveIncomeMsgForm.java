package com.deloitte.platform.api.fssc.rep.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description : RecieveIncomeMsg新增修改form对象
 * @Modified :
 */
@ApiModel("新增RecieveIncomeMsg表单")
@Data
public class RecieveIncomeMsgForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "创建人姓名申请人")
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "款项收入小类ID")
    private Long inComeSubTypeId;

    @ApiModelProperty(value = "款项收入小类CODE")
    private String inComeSubTypeCode;

    @ApiModelProperty(value = "款项收入小类名称")
    private String inComeSubTypeName;

    @ApiModelProperty(value = "款项收入小类科目CODE")
    private String accountCode;

    @ApiModelProperty(value = "款项收入小类预算科目CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "税率")
    private BigDecimal tax;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "总额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;

}
