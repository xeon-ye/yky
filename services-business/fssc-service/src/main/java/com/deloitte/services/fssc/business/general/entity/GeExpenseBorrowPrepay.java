package com.deloitte.services.fssc.business.general.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 通用报账单关联借款或预付款
 * </p>
 *
 * @author jaws
 * @since 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("GE_EXPENSE_BORROW_PREPAY")
@ApiModel(value="GeExpenseBorrowPrepay对象", description="通用报账单关联借款或预付款")
public class GeExpenseBorrowPrepay extends Model<GeExpenseBorrowPrepay> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
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

    @ApiModelProperty(value = "单据编号")
    @TableField("DOCUMENT_NUM")
    private String documentNum;

    @ApiModelProperty(value = "单据类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "单据类型名称")
    @TableField("DOCUMENT_TYPE_NAME")
    private String documentTypeName;

    @ApiModelProperty(value = "关联的单据ID")
    @TableField("BORROW_OR_PREPAY_ID")
    private Long borrowOrPrepayId;

    @ApiModelProperty(value = "关联的单据类型 借款or对公付款")
    @TableField("CONNECT_DOCUMENT_TYPE")
    private String connectDocumentType;


    @ApiModelProperty(value = "关联的单据号 借款or对公付款")
    @TableField("CONNECT_DOCUMENT_NUM")
    private String connectDocumentNum;



    @ApiModelProperty(value = "支出大类ID")
    @TableField("MAIN_TYPE_ID")
    private Long mainTypeId;

    @TableField("MAIN_TYPE_CODE")
    private String mainTypeCode;

    @ApiModelProperty(value = "支出大类名称")
    @TableField("MAIN_TYPE_NAME")
    private String mainTypeName;

    @ApiModelProperty(value = "核销日期")
    @TableField("VERFICATION_DATE")
    private LocalDateTime verficationDate;

    @ApiModelProperty(value = "借款或预付金额")
    @TableField("BP_AMOUNT")
    private BigDecimal bpAmount;

    @ApiModelProperty(value = "已核销金额")
    @TableField("VERFICATED_AMOUNT")
    private BigDecimal verficatedAmount;

    @ApiModelProperty(value = "本次核销金额")
    @TableField("CURRENT_VER_AMOUNT")
    private BigDecimal currentVerAmount;

    @ApiModelProperty(value = "未核销金额")
    @TableField("NO_VER_AMOUNT")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "核销说明")
    @TableField("VERFICATION_REMARK")
    private String verficationRemark;

    @ApiModelProperty(value = "支出小类ID")
    @TableField("SUB_TYPE_ID")
    private Long subTypeId;

    @TableField("SUB_TYPE_CODE")
    private String subTypeCode;

    @ApiModelProperty(value = "支出小类名称")
    @TableField("SUB_TYPE_NAME")
    private String subTypeName;

    @TableField("ACCOUNT_CODE")
    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

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

    @TableField("LINE_NUMBER")
    @ApiModelProperty(value = "行号")
    private Long lineNumber;

    @ApiModelProperty(value = "对方签约主体")
    @TableField("SUBJECT_SUPERFICIL")
    private String subjectSuperficil;

    @ApiModelProperty(value = "约定付款批次")
    @TableField("AGREED_PAYMENT_LOT")
    private String agreedPaymentLot;

    @ApiModelProperty(value = "履行计划ID")
    @TableField("TRAVEL_PLAN_ID")
    private Long travelPlanId;

    @ApiModelProperty(value = "付款方式")
    @TableField("PAYMENT_TYPE")
    private String paymentType;

    @ApiModelProperty(value = "付款单预算科目code")
    @TableField("PAYMENT_BUDGET_ACCOUNT_CODE")
    private String paymentBudgetAccountCode;

    @ApiModelProperty(value = "付款单银行账户财务会计科目")
    @TableField("PAYMENT_ACCOUNT_CODE")
    private String paymentAccountCode;

    @ApiModelProperty(value = "付款银行账号")
    @TableField("BANK_ACCOUNT")
    private String bankAccount;

    @ApiModelProperty(value = "付款银行账户")
    @TableField("BANK_TYPE")
    private String bankType;



    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String DOCUMENT_TYPE_NAME = "DOCUMENT_TYPE_NAME";

    public static final String MAIN_TYPE_ID = "MAIN_TYPE_ID";

    public static final String MAIN_TYPE_NAME = "MAIN_TYPE_NAME";

    public static final String VERFICATION_DATE = "VERFICATION_DATE";

    public static final String BP_AMOUNT = "BP_AMOUNT";

    public static final String VERFICATED_AMOUNT = "VERFICATED_AMOUNT";

    public static final String CURRENT_VER_AMOUNT = "CURRENT_VER_AMOUNT";

    public static final String NO_VER_AMOUNT = "NO_VER_AMOUNT";

    public static final String VERFICATION_REMARK = "VERFICATION_REMARK";

    public static final String SUB_TYPE_ID = "SUB_TYPE_ID";

    public static final String SUB_TYPE_NAME = "SUB_TYPE_NAME";

    public static final String INVOICE_CODE = "INVOICE_CODE";

    public static final String INVOICE_NUMBER = "INVOICE_NUMBER";

    public static final String INVOICE_AMOUNT = "INVOICE_AMOUNT";

    public static final String TAX = "TAX";

    public static final String TAX_AMOUNT = "TAX_AMOUNT";

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
