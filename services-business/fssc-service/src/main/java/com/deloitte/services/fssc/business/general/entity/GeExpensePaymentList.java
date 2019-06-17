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
 * 对公付款清单表
 * </p>
 *
 * @author jaws
 * @since 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("GE_EXPENSE_PAYMENT_LIST")
@ApiModel(value="GeExpensePaymentList对象", description="对公付款清单表")
public class GeExpensePaymentList extends Model<GeExpensePaymentList> {

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

    @ApiModelProperty(value = "单据类型表名")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "付款金额")
    @TableField("PAY_AMOUNT")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "供应商ID")
    @TableField("VENDOR_ID")
    private Long vendorId;

    @TableField("VENDOR_CODE")
    private String vendorCode;

    @ApiModelProperty(value = "供应商名称")
    @TableField("VENDOR_NAME")
    private String vendorName;

    @ApiModelProperty(value = "银行ID 无用字段")
    @TableField("BANK_ID")
    private Long bankId;

    @ApiModelProperty(value = "银行账户ID")
    @TableField("BANK_UNIT_ID")
    private Long bankUnitId;

    @TableField("BANK_CODE")
    private String bankCode;

    @ApiModelProperty(value = "付款状态")
    @TableField("PAY_STATUS")
    private String payStatus;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "预留字段1，用于关联是否被付款单关联")
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

    @ApiModelProperty(value = "统一社会信息代码")
    @TableField("COMMON_CREDIT_CODE")
    private String commonCreditCode;

    @ApiModelProperty(value = "银行名称")
    @TableField("BANK_NAME")
    private String bankName;

    @ApiModelProperty(value = "账户名称")
    @TableField("BANK_ACCOUNT")
    private String bankAccount ;

    @TableField("ACCOUNT_NAME")
    @ApiModelProperty(value = "账户名称")
    private String accountName;

    @ApiModelProperty(value = "联行号")
    @TableField("INTER_BRANCH_NUMBER")
    private String interBranchNumber;

    @TableField("LINE_NUMBER")
    @ApiModelProperty(value = "行号")
    private Long lineNumber;

    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String PAY_AMOUNT = "PAY_AMOUNT";

    public static final String VENDOR_ID = "VENDOR_ID";

    public static final String VENDOR_NAME = "VENDOR_NAME";

    public static final String BANK_ID = "BANK_ID";

    public static final String PAY_STATUS = "PAY_STATUS";

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

    public static final String COMMON_CREDIT_CODE="COMMON_CREDIT_CODE";

    public static final String BANK_NAME="BANK_NAME";

    public static final String BANK_ACCOUNT="BANK_ACCOUNT";

    public static final String INTER_BRANCH_NUMBER ="INTER_BRANCH_NUMBER";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
