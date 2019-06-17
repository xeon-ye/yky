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
 * 通用报账单行明细
 * </p>
 *
 * @author jaws
 * @since 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("GE_GENERAL_EXPENSE_LINE")
@ApiModel(value="GeGeneralExpenseLine对象", description="通用报账单行明细")
public class GeGeneralExpenseLine extends Model<GeGeneralExpenseLine> {

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

    @ApiModelProperty(value = "单据类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "支出小类ID")
    @TableField("SUB_TYPE_ID")
    private Long subTypeId;

    @TableField("SUB_TYPE_CODE")
    @ApiModelProperty(value = "支出小类CODE")
    private String subTypeCode;

    @TableField("ACCOUNT_CODE")
    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "支出小类名称")
    @TableField("SUB_TYPE_NAME")
    private String subTypeName;

    @ApiModelProperty(value = "发票代码")
    @TableField("INVOICE_CODE")
    private String invoiceCode;

    @ApiModelProperty(value = "发票号")
    @TableField("INVOICE_NUMBER")
    private String invoiceNumber;

    @ApiModelProperty(value = "发票金额")
    @TableField("INVOICE_AMOUNT")
    private BigDecimal invoiceAmount;

    @ApiModelProperty(value = "税率")
    @TableField("TAX")
    private BigDecimal tax;

    @ApiModelProperty(value = "税额")
    @TableField("TAX_AMOUNT")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "不含税金额")
    @TableField("NO_TAX_AMOUNT")
    private BigDecimal noTaxAmount;

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

    @ApiModelProperty(value = "付款方式")
    @TableField("PAYMENT_TYPE")
    private String paymentType;


    @ApiModelProperty(value = "已核销金额")
    @TableField("HAS_VER_AMOUNT")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    @TableField("NO_VER_AMOUNT")
    private BigDecimal noVerAmount;


    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

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
