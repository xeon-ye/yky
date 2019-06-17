package com.deloitte.services.fssc.business.ito.entity;

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
 * 
 * </p>
 *
 * @author qiliao
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ITO_DETAILS_OF_FUNDS")
@ApiModel(value="DetailsOfFunds对象", description="")
public class DetailsOfFunds extends Model<DetailsOfFunds> {

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

    @ApiModelProperty(value = "预留字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "预留字段6")
    @TableField("EXT6")
    private String ext6;

    @ApiModelProperty(value = "预留字段7")
    @TableField("EXT7")
    private String ext7;

    @ApiModelProperty(value = "预留字段8")
    @TableField("EXT8")
    private String ext8;

    @ApiModelProperty(value = "预留字段9")
    @TableField("EXT9")
    private String ext9;

    @ApiModelProperty(value = "预留字段10")
    @TableField("EXT10")
    private String ext10;

    @ApiModelProperty(value = "预留字段11")
    @TableField("EXT11")
    private String ext11;

    @ApiModelProperty(value = "预留字段12")
    @TableField("EXT12")
    private String ext12;

    @ApiModelProperty(value = "预留字段13")
    @TableField("EXT13")
    private String ext13;

    @ApiModelProperty(value = "预留字段14")
    @TableField("EXT14")
    private String ext14;

    @ApiModelProperty(value = "预留字段15")
    @TableField("EXT15")
    private String ext15;

    @ApiModelProperty(value = "收入小类ID")
    @TableField("INCOME_SUB_TYPE_ID")
    private Long inComeSubTypeId;

    @ApiModelProperty(value = "收入小类CODE")
    @TableField("INCOME_SUB_TYPE_CODE")
    private String incomeSubTypeCode;

    @ApiModelProperty(value = "收入小类NAME")
    @TableField("INCOME_SUB_TYPE_NAME")
    private String incomeSubTypeName;

    @ApiModelProperty(value = "收入大类ID")
    @TableField("INCOME_TYPE_ID")
    private Long incomeTypeId;

    @ApiModelProperty(value = "收入大类名称")
    @TableField("INCOME_TYPE_NAME")
    private String incomeTypeName;

    @ApiModelProperty(value = "收入大类编码")
    @TableField("INCOME_TYPE_CODE")
    private String incomeTypeCode;

    @ApiModelProperty(value = "会计科目代码")
    @TableField("ACCOUNT_CODE")
    private String accountCode;

    @ApiModelProperty(value = "预算会计科目代码")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "行号")
    @TableField("LINE_NUMBER")
    private Long lineNumber;

    @ApiModelProperty(value = "收款单行Id")
    @TableField("RECEIPT_LINE_ID")
    private Long receiptLineId;

    @ApiModelProperty(value = "收款单编号")
    @TableField("RECEIPT_NUMBER")
    private String receiptNumber;

    @ApiModelProperty(value = "上缴单位")
    @TableField("PAID_UNIT")
    private String paidUnit;

    @ApiModelProperty(value = "缴款银行信息")
    @TableField("PAYMENT_BANK_INFO")
    private String paymentBankInfo;

    @ApiModelProperty(value = "账户类型")
    @TableField("ACCOUNT_TYPE")
    private String accountType;

    @ApiModelProperty(value = "缴款银行账户号")
    @TableField("PAYMENT_BANK_ACCOUNT")
    private String paymentBankAccount;

    @ApiModelProperty(value = "上缴金额")
    @TableField("AMOUNT_PAID")
    private BigDecimal amountPaid;

    @ApiModelProperty(value = "上缴时间")
    @TableField("PAID_TIME")
    private LocalDateTime paidTime;

    @ApiModelProperty(value = "收款账户")
    @TableField("COLLECTION_ACCOUNT")
    private String collectionAccount;

    @ApiModelProperty(value = "收款账户号")
    @TableField("COLLECTION_NUMBER")
    private String collectionNumber;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "单据id")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "会计科目代码")
    @TableField("BANK_SUBJECT_CODE")
    private String bankSubjectCode;

    @ApiModelProperty(value = "预算会计科目代码")
    @TableField(value = "BUDGET_BANK_SUBJECT_CODE")
    private String budgetBankSubjectCode;

    @ApiModelProperty(value = "银行ID")
    @TableField("BANK_ID")
    private Long bankId;

    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String EXT6 = "EXT6";

    public static final String EXT7 = "EXT7";

    public static final String EXT8 = "EXT8";

    public static final String EXT9 = "EXT9";

    public static final String EXT10 = "EXT10";

    public static final String EXT11 = "EXT11";

    public static final String EXT12 = "EXT12";

    public static final String EXT13 = "EXT13";

    public static final String EXT14 = "EXT14";

    public static final String EXT15 = "EXT15";

    public static final String INCOME_SUB_TYPE_ID = "INCOME_SUB_TYPE_ID";

    public static final String INCOME_SUB_TYPE_CODE = "INCOME_SUB_TYPE_CODE";

    public static final String INCOME_SUB_TYPE_NAME = "INCOME_SUB_TYPE_NAME";

    public static final String INCOME_TYPE_ID = "INCOME_TYPE_ID";

    public static final String INCOME_TYPE_NAME = "INCOME_TYPE_NAME";

    public static final String INCOME_TYPE_CODE = "INCOME_TYPE_CODE";

    public static final String ACCOUNT_CODE = "ACCOUNT_CODE";

    public static final String BUDGET_ACCOUNT_CODE = "BUDGET_ACCOUNT_CODE";

    public static final String LINE_NUMBER = "LINE_NUMBER";

    public static final String RECEIPT_NUMBER = "RECEIPT_NUMBER";

    public static final String PAID_UNIT = "PAID_UNIT";

    public static final String PAYMENT_BANK_INFO = "PAYMENT_BANK_INFO";

    public static final String ACCOUNT_TYPE = "ACCOUNT_TYPE";

    public static final String PAYMENT_BANK_ACCOUNT = "PAYMENT_BANK_ACCOUNT";

    public static final String AMOUNT_PAID = "AMOUNT_PAID";

    public static final String PAID_TIME = "PAID_TIME";

    public static final String COLLECTION_ACCOUNT = "COLLECTION_ACCOUNT";

    public static final String COLLECTION_NUMBER = "COLLECTION_NUMBER";

    public static final String REMARK = "REMARK";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
