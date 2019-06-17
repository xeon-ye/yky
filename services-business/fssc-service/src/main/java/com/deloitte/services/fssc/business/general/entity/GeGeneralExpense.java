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
 * 通用报账单主表
 * </p>
 *
 * @author jaws
 * @since 2019-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("GE_GENERAL_EXPENSE")
@ApiModel(value="GeGeneralExpense对象", description="通用报账单主表")
public class GeGeneralExpense extends Model<GeGeneralExpense> {

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

    @ApiModelProperty(value = "单据编号")
    @TableField("DOCUMENT_NUM")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    @TableField("DOCUMENT_STATUS")
    private String documentStatus;

    @ApiModelProperty(value = "付款状态")
    @TableField("PAY_STATUS")
    private String payStatus;

    @ApiModelProperty(value = "归属单位ID")
    @TableField("UNIT_ID")
    private Long unitId;


    @TableField("UNIT_CODE")
    @ApiModelProperty(value = "部门code")
    private String unitCode;


    @ApiModelProperty(value = "归属部门ID")
    @TableField("DEPT_ID")
    private Long deptId;

    @TableField("DEPT_CODE")
    @ApiModelProperty(value = "部门CODE")
    private String deptCode;

    @ApiModelProperty(value = "归属单位名称")
    @TableField("UNIT_NAME")
    private String unitName;

    @ApiModelProperty(value = "归属部门名称")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty(value = "付款方式")
    @TableField("PAYMENT_TYPE")
    private String paymentType;

    @ApiModelProperty(value = "项目名称")
    @TableField(value = "PROJECT_NAME",strategy = FieldStrategy.IGNORED)
    private String projectName;

    @ApiModelProperty(value = "项目ID")
    @TableField(value = "PROJECT_ID",strategy = FieldStrategy.IGNORED)
    private Long projectId;

    @TableField(value = "PROJECT_CODE",strategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "项目code")
    private String projectCode;

    @TableField("FSSC_CODE")
    private String fsscCode;

    @TableField("PROJECT_UNIT_ID")
    @ApiModelProperty(value = "项目承担单位id")
    private Long projectUnitId;

    @TableField("PROJECT_UNIT_CODE")
    @ApiModelProperty(value = "项目承担单位code")
    private String projectUnitCode;

    @TableField("PROJECT_UNIT_NAME")
    @ApiModelProperty(value = "项目承担单位名称")
    private String projectUnitName;



    @ApiModelProperty(value = "关联事项申请ID")
    @TableField("APPLY_FOR_ID")
    private Long applyForId;

    @ApiModelProperty(value = "关联事项名称")
    @TableField("APPLY_FOR_NAME")
    private String applyForName;

    @ApiModelProperty(value = "大类名称")
    @TableField("MAIN_TYPE_NAME")
    private String mainTypeName;

    @ApiModelProperty(value = "支出大类ID")
    @TableField("MAIN_TYPE_ID")
    private Long mainTypeId;

    @TableField("MAIN_TYPE_CODE")
    @ApiModelProperty(value = "支出大类CODE")
    private String mainTypeCode;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "币种")
    @TableField("CURRENCY_CODE")
    private String currencyCode;

    @ApiModelProperty(value = "费率")
    @TableField("COST")
    private BigDecimal cost;

    @ApiModelProperty(value = "支持性附件数量")
    @TableField("ATTACH_COUNT")
    private Long attachCount;

    @ApiModelProperty(value = "报账合计金额")
    @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "报账合计金额(外币)")
    @TableField("TOTAL_AMOUNT_OTHER_CURRENCY")
    private BigDecimal totalAmountOtherCurrency;

    @ApiModelProperty(value = "报销总金额")
    @TableField("EXPENSE_AMOUNT")
    private BigDecimal expenseAmount;

    @ApiModelProperty(value = "核销金额")
    @TableField("VERIFICATION_AMOUNT")
    private BigDecimal verificationAmount;

    @TableField("VER_AMOUNT_BUSINESS")
    @ApiModelProperty(value = "核销金额-公务卡")
    private BigDecimal verAmountBusiness;

    @TableField("VER_AMOUNT_SARLARY")
    @ApiModelProperty(value = "核销金额-工资卡")
    private BigDecimal verAmountSarlary;

    @TableField("VER_AMOUNT_PUBLIC")
    @ApiModelProperty(value = "核销金额-对公付款")
    private BigDecimal verAmountPublic;

    @ApiModelProperty(value = "支付至工资卡金额")
    @TableField("PAY_SALARY_AMOUNT")
    private BigDecimal paySalaryAmount;

    @ApiModelProperty(value = "公务卡还款金额")
    @TableField("BUSINUSS_AMOUNT")
    private BigDecimal businussAmount;

    @ApiModelProperty(value = "对公付款金额")
    @TableField("PAY_COMPANY_AMOUNT")
    private BigDecimal payCompanyAmount;


    @TableField("IS_GENERATE_PAY_ORDER")
    @ApiModelProperty("是否生成过付款单,提交时修改此状态 Y N")
    private String isGeneratePayOrder;

    @ApiModelProperty(value = "关联付款单Id")
    @TableField(value="PAYMENT_ID",strategy = FieldStrategy.IGNORED)
    private Long payMentId;

    @ApiModelProperty(value = "项目预算会计科目")
    @TableField("PROJECT_BUDGET_ACCOUNT_CODE")
    private String projectBudgetAccountCode;

    @ApiModelProperty(value = "预留字段1 预算科目code")
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

    @TableField("IS_AFTER_PATCH")
    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @TableField("PAID_AMOUNT")
    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;

    @TableField("NO_PAID_AMOUNT")
    @ApiModelProperty(value = "未支付金额")
    private BigDecimal noPaidAmount;

    @ApiModelProperty(value = "不含税金额")
    @TableField("NO_TAX_AMOUNT")
    private BigDecimal noTaxAmount;

    @ApiModelProperty(value = "税额")
    @TableField("TAX_AMOUNT")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "税率")
    @TableField("TAX")
    private BigDecimal tax;

    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String DOCUMENT_STATUS = "DOCUMENT_STATUS";

    public static final String PAY_STATUS = "PAY_STATUS";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String UNIT_NAME = "UNIT_NAME";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String PAYMENT_TYPE = "PAYMENT_TYPE";

    public static final String PROJECT_NAME = "PROJECT_NAME";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String APPLY_FOR_ID = "APPLY_FOR_ID";

    public static final String APPLY_FOR_NAME = "APPLY_FOR_NAME";

    public static final String MAIN_TYPE_NAME = "MAIN_TYPE_NAME";

    public static final String MAIN_TYPE_ID = "MAIN_TYPE_ID";

    public static final String REMARK = "REMARK";

    public static final String CURRENCY_CODE = "CURRENCY_CODE";

    public static final String COST = "COST";

    public static final String ATTACH_COUNT = "ATTACH_COUNT";

    public static final String TOTAL_AMOUNT = "TOTAL_AMOUNT";

    public static final String EXPENSE_AMOUNT = "EXPENSE_AMOUNT";

    public static final String VERIFICATION_AMOUNT = "VERIFICATION_AMOUNT";

    public static final String PAY_SALARY_AMOUNT = "PAY_SALARY_AMOUNT";

    public static final String BUSINUSS_AMOUNT = "BUSINUSS_AMOUNT";

    public static final String PAY_COMPANY_AMOUNT = "PAY_COMPANY_AMOUNT";

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
