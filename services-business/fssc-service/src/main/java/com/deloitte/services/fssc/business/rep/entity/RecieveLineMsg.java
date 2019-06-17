package com.deloitte.services.fssc.business.rep.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 收款单收款信息
 * </p>
 *
 * @author qiliao
 * @since 2019-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("REP_RECIEVE_LINE_MSG")
@ApiModel(value="RecieveLineMsg对象", description="收款单收款信息")
public class RecieveLineMsg extends Model<RecieveLineMsg> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "创建人ID申请人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名申请人")
    @TableField("CREATE_USER_NAME")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    @Version
    private Long version;

    @ApiModelProperty(value = "单据ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "交易编码")
    @TableField("TRANSACTION_CODE")
    private String transactionCode;

    @ApiModelProperty(value = "收款单位")
    @TableField("RECIEVE_UNIT_NAME")
    private String recieveUnitName;

    @ApiModelProperty(value = "收款银行名称")
    @TableField("RECIEVE_BANK_NAME")
    private String recieveBankName;

    @ApiModelProperty(value = "账户类型")
    @TableField("RECIEVE_BANK_TYPE")
    private String recieveBankType;

    @ApiModelProperty(value = "收款银行账号")
    @TableField("RECIEVE_BANK_ACCOUNT")
    private String recieveBankAccount;

    @ApiModelProperty(value = "交易金额")
    @TableField("TRANSACTION_AMOUNT")
    private BigDecimal transactionAmount;

    @ApiModelProperty(value = "款项到账时间")
    @TableField("RECIEVE_TIME")
    private LocalDateTime recieveTime;

    @ApiModelProperty(value = "付款单位")
    @TableField("PAY_UNIT_NAME")
    private String payUnitName;

    @ApiModelProperty(value = "付款银行账号")
    @TableField("PAY_BANK_ACCOUNT")
    private String payBankAccount;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "预留字段1")
    @TableField("EX1")
    private String ex1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("EX2")
    private String ex2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("EX3")
    private String ex3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("EX4")
    private String ex4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("EX5")
    private String ex5;

    @ApiModelProperty(value = "预留字段6")
    @TableField("EX6")
    private String ex6;

    @ApiModelProperty(value = "预留字段7")
    @TableField("EX7")
    private String ex7;

    @ApiModelProperty(value = "预留字段8")
    @TableField("EX8")
    private String ex8;

    @ApiModelProperty(value = "预留字段9")
    @TableField("EX9")
    private String ex9;

    @ApiModelProperty(value = "预留字段10")
    @TableField("EX10")
    private String ex10;

    @ApiModelProperty(value = "预留字段11")
    @TableField("EX11")
    private String ex11;

    @ApiModelProperty(value = "预留字段12")
    @TableField("EX12")
    private String ex12;

    @ApiModelProperty(value = "预留字段13")
    @TableField("EX13")
    private String ex13;

    @ApiModelProperty(value = "预留字段14")
    @TableField("EX14")
    private String ex14;

    @ApiModelProperty(value = "预留字段15")
    @TableField("EX15")
    private String ex15;

    @ApiModelProperty(value = "行号")
    @TableField("LINE_NUMBER")
    private Long lineNumber;

    @ApiModelProperty(value = "款项收入小类ID")
    @TableField("IN_COME_SUB_TYPE_ID")
    private Long inComeSubTypeId;

    @ApiModelProperty(value = "款项收入小类CODE")
    @TableField("IN_COME_SUB_TYPE_CODE")
    private String inComeSubTypeCode;

    @ApiModelProperty(value = "款项收入小类名称")
    @TableField("IN_COME_SUB_TYPE_NAME")
    private String inComeSubTypeName;

    @ApiModelProperty(value = "款项收入小类科目CODE")
    @TableField("ACCOUNT_CODE")
    private String accountCode;

    @ApiModelProperty(value = "款项收入小类预算科目CODE")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "付款银行信息")
    @TableField("PAYMENT_BANK_INFORMATION")
    private String paymentBankName;

    @ApiModelProperty(value = "是否锁定")
    @TableField("WHETHER_LOCK")
    private String whetherLock;

    @ApiModelProperty(value = "收款金额")
    @TableField("AMOUNT_COLLECTED")
    private BigDecimal amountCollected;

    @ApiModelProperty(value = "收入确认金额")
    @TableField("REVENUE_AMOUNT")
    private BigDecimal revenueAmount;

    @TableField("RECIEVE_UNIT_ID")
    @ApiModelProperty(value = "收款单位ID")
    private Long recieveUnitId;

    @TableField("PAY_UNIT_ID")
    @ApiModelProperty(value = "付款单位ID")
    private Long payUnitId;

    @TableField("PAYMENT_LINE_DETAIL_ID")
    @ApiModelProperty(value = "款项明细行ID")
    private Long paymentLineDetailId;

    @ApiModelProperty(value = "不含税金额")
    @TableField("NO_TAX_AMOUNT")
    private BigDecimal noTaxAmount;

    @ApiModelProperty(value = "税率")
    @TableField("TAX")
    private BigDecimal tax;

    @ApiModelProperty(value = "税额")
    @TableField("TAX_AMOUNT")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "银行账号ID")
    @TableField("RECIEVE_BANK_UNIT_ID")
    @NotNull
    private Long recieveBankUnitId;

    @ApiModelProperty(value = "银行账户会计科目代码")
    @TableField("BANK_SUBJECT_CODE")
    private String bankSubjectCode;

    @ApiModelProperty(value = "银行账户预算会计科目代码")
    @TableField(value = "BUDGET_BANK_SUBJECT_CODE")
    private String budgetBankSubjectCode;

    @ApiModelProperty(value = "交易流水ID")
    @TableField("TRADE_INFORMATION_ID")
    private Long tradeInformationId;

    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String TRANSACTION_CODE = "TRANSACTION_CODE";

    public static final String RECIEVE_UNIT_NAME = "RECIEVE_UNIT_NAME";

    public static final String RECIEVE_BANK_NAME = "RECIEVE_BANK_NAME";

    public static final String RECIEVE_BANK_TYPE = "RECIEVE_BANK_TYPE";

    public static final String RECIEVE_BANK_ACCOUNT = "RECIEVE_BANK_ACCOUNT";

    public static final String TRANSACTION_AMOUNT = "TRANSACTION_AMOUNT";

    public static final String RECIEVE_TIME = "RECIEVE_TIME";

    public static final String PAY_UNIT_NAME = "PAY_UNIT_NAME";

    public static final String PAY_BANK_ACCOUNT = "PAY_BANK_ACCOUNT";

    public static final String REMARK = "REMARK";

    public static final String EX1 = "EX1";

    public static final String EX2 = "EX2";

    public static final String EX3 = "EX3";

    public static final String EX4 = "EX4";

    public static final String EX5 = "EX5";

    public static final String EX6 = "EX6";

    public static final String EX7 = "EX7";

    public static final String EX8 = "EX8";

    public static final String EX9 = "EX9";

    public static final String EX10 = "EX10";

    public static final String EX11 = "EX11";

    public static final String EX12 = "EX12";

    public static final String EX13 = "EX13";

    public static final String EX14 = "EX14";

    public static final String EX15 = "EX15";

    public static final String LINE_NUMBER = "LINE_NUMBER";

    public static final String IN_COME_SUB_TYPE_ID = "IN_COME_SUB_TYPE_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
