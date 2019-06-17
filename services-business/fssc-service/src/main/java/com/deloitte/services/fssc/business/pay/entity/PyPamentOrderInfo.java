package com.deloitte.services.fssc.business.pay.entity;

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
 * 付款单头表
 * </p>
 *
 * @author qiliao
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PY_PAMENT_ORDER_INFO")
@ApiModel(value="PyPamentOrderInfo对象", description="付款单头表")
public class PyPamentOrderInfo extends Model<PyPamentOrderInfo> {

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

    @ApiModelProperty(value = "单据编号")
    @TableField("DOCUMENT_NUM")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    @TableField("DOCUMENT_STATUS")
    private String documentStatus;

    @ApiModelProperty(value = "归属单位code")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "归属单位ID")
    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "归属单位名称")
    @TableField("UNIT_NAME")
    private String unitName;

    @ApiModelProperty(value = "管理部门code")
    @TableField("DEPT_CODE")
    private String deptCode;

    @ApiModelProperty(value = "管理部门ID")
    @TableField("DEPT_ID")
    private Long deptId;

    @ApiModelProperty(value = "管理部门名称")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "备注摘要")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "币种")
    @TableField("CURRENCY_CODE")
    private String currencyCode;

    @ApiModelProperty(value = "费率")
    @TableField("COST")
    private BigDecimal cost;

    @ApiModelProperty(value = "合计金额")
    @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "合计金额外币")
    @TableField("TOTAL_AMOUNT_OTHER_CURRENCY")
    private BigDecimal totalAmountOtherCurrency;

    @ApiModelProperty(value = "已支付金额")
    @TableField("PAID_AMOUNT")
    private BigDecimal paidAmount;

    @ApiModelProperty(value = "未支付金额")
    @TableField("NO_PAID_AMOUNT")
    private BigDecimal noPaidAmount;

    @ApiModelProperty(value = "待支付单据编号")
    @TableField("PAY_DOCUMENT_NUM")
    private String payDocumentNum;

    @ApiModelProperty(value = "待支付单据类型")
    @TableField("PAY_DOCUMENT_TYPE")
    private String payDocumentType;

    @ApiModelProperty(value = "付款方式")
    @TableField("PAYMENT_TYPE")
    private String paymentType;

    @ApiModelProperty(value = "银行账号")
    @TableField("BANK_ACCOUNT")
    private String bankAccount;

    @ApiModelProperty(value = "账户性质")
    @TableField("BANK_TYPE")
    private String bankType;

    @ApiModelProperty(value = "是否事后补单")
    @TableField("IS_AFTER_PATCH")
    private String isAfterPatch;

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

    @ApiModelProperty(value = "付款状态")
    @TableField("PAY_STATUS")
    private  String payStatus;

    @ApiModelProperty(value = "是否从其他单据跳转")
    @TableField("IS_PAY_ORDER")
    private  String isPayOrder;

    @TableField("ACCOUNT_CODE")
    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

    @ApiModelProperty("银行ID")
    @TableField("BANK_ID")
    private Long bankId;



    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String DOCUMENT_STATUS = "DOCUMENT_STATUS";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String UNIT_NAME = "UNIT_NAME";

    public static final String DEPT_CODE = "DEPT_CODE";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String REMARK = "REMARK";

    public static final String CURRENCY_CODE = "CURRENCY_CODE";

    public static final String COST = "COST";

    public static final String TOTAL_AMOUNT = "TOTAL_AMOUNT";

    public static final String TOTAL_AMOUNT_OTHER_CURRENCY = "TOTAL_AMOUNT_OTHER_CURRENCY";

    public static final String PAID_AMOUNT = "PAID_AMOUNT";

    public static final String NO_PAID_AMOUNT = "NO_PAID_AMOUNT";

    public static final String PAY_DOCUMENT_NUM = "PAY_DOCUMENT_NUM";

    public static final String PAY_DOCUMENT_TYPE = "PAY_DOCUMENT_TYPE";

    public static final String PAYMENT_TYPE = "PAYMENT_TYPE";

    public static final String BANK_ACCOUNT = "BANK_ACCOUNT";

    public static final String BANK_TYPE = "BANK_TYPE";

    public static final String IS_AFTER_PATCH = "IS_AFTER_PATCH";

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
