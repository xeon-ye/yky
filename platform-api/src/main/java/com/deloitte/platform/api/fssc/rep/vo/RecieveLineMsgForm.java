package com.deloitte.platform.api.fssc.rep.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description : RecieveLineMsg新增修改form对象
 * @Modified :
 */
@ApiModel("新增RecieveLineMsg表单")
@Data
public class RecieveLineMsgForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "不含税金额")
    private BigDecimal noTaxAmount;

    @ApiModelProperty(value = "税率")
    private BigDecimal tax;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    private Long id;

    @ApiModelProperty(value = "创建人姓名申请人")
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "交易编码")
    private String transactionCode;

    @ApiModelProperty(value = "收款单位")
    private String recieveUnitName;

    @ApiModelProperty(value = "收款银行名称")
    private String recieveBankName;

    @ApiModelProperty(value = "账户类型")
    private String recieveBankType;

    @ApiModelProperty(value = "收款银行账号")
    private String recieveBankAccount;

    @ApiModelProperty(value = "交易金额")
    private BigDecimal transactionAmount;

    @ApiModelProperty(value = "款项到账时间")
    private LocalDateTime recieveTime;

    @ApiModelProperty(value = "付款单位")
    private String payUnitName;

    @ApiModelProperty(value = "付款单位id")
    private Long payUnitId;

    @ApiModelProperty(value = "付款银行账号")
    private String payBankAccount;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;

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

    @ApiModelProperty(value = "付款银行信息")
    private String paymentBankName;

    @ApiModelProperty(value = "是否锁定")
    private String whetherLock;

    @ApiModelProperty(value = "收款金额")
    private BigDecimal amountCollected;

    @ApiModelProperty(value = "收入确认金额")
    private BigDecimal revenueAmount;

    @ApiModelProperty(value = "款项明细行ID")
    private Long paymentLineDetailId;

    @ApiModelProperty(value = "收款单位ID")
    private Long recieveUnitId;

    @ApiModelProperty(value = "银行账号ID")
    @NotNull
    private Long recieveBankUnitId;

    @ApiModelProperty(value = "交易流水ID")
    private Long tradeInformationId;
}
