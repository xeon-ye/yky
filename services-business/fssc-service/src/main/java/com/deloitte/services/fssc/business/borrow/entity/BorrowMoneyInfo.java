package com.deloitte.services.fssc.business.borrow.entity;

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
 * 借款主表
 * </p>
 *
 * @author qiliao
 * @since 2019-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BM_BORROW_MONEY_INFO")
@ApiModel(value="BorrowMoneyInfo对象", description="借款主表")
public class BorrowMoneyInfo extends Model<BorrowMoneyInfo> {

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
    private String unitCode;

    @ApiModelProperty(value = "归属部门ID")
    @TableField("DEPT_ID")
    private Long deptId;

    @TableField("DEPT_CODE")
    private String deptCode;

    @ApiModelProperty(value = "项目ID")
    @TableField(value = "PROJECT_ID",strategy = FieldStrategy.IGNORED)
    private Long projectId;

    @TableField("PROJECT_CODE")
    private String projectCode;

    @TableField("PROJECT_UNIT_ID")
    private Long projectUnitId;

    @TableField("PROJECT_UNIT_CODE")
    private String projectUnitCode;

    @TableField("PROJECT_UNIT_NAME")
    private String projectUnitName;

    @TableField("FSSC_CODE")
    private String fsscCode;

    @ApiModelProperty(value = "关联事项申请ID")
    @TableField("APPLY_FOR_ID")
    private Long applyForId;

    @ApiModelProperty(value = "支出大类ID")
    @TableField("MAIN_TYPE_ID")
    private Long mainTypeId;

    @TableField("MAIN_TYPE_CODE")
    private String mainTypeCode;

    @ApiModelProperty(value = "付款方式")
    @TableField("PAYMENT_TYPE")
    private String paymentType;

    @ApiModelProperty(value = "预计还款时间")
    @TableField("REPAYMENT_TIME")
    private LocalDateTime repaymentTime;

    @ApiModelProperty(value = "借款金额合计原币")
    @TableField("BORROW_AMOUNT")
    private BigDecimal borrowAmount;

    @ApiModelProperty(value = "已核销金额")
    @TableField("HAS_VER_AMOUNT")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    @TableField("NO_VER_AMOUNT")
    private BigDecimal noVerAmount;

    @TableField("PAID_AMOUNT")
    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;

    @TableField("NO_PAID_AMOUNT")
    @ApiModelProperty(value = "未支付金额")
    private BigDecimal noPaidAmount;

    @TableField("IS_AFTER_PATCH")
    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "费率")
    @TableField("COST")
    private BigDecimal cost;

    @ApiModelProperty(value = "支持性附件数量")
    @TableField("ATTACH_COUNT")
    private Long attachCount;

    @ApiModelProperty(value = "借款金额合计原币(外币)")
    @TableField("BORROW_AMOUNT_OTHER_CURRENCY")
    private BigDecimal borrowAmountOtherCurrency;

    @ApiModelProperty(value = "已核销金额(外币)")
    @TableField("HAS_VER_AMOUNT_OTHER_CURRENCY")
    private BigDecimal hasVerAmountOtherCurrency;

    @ApiModelProperty(value = "未核销金额(外币)")
    @TableField("NO_VER_AMOUNT_OTHER_CURRENCY")
    private BigDecimal noVerAmountOtherCurrency;


    @ApiModelProperty(value = "币种")
    @TableField("CURRENCY_CODE")
    private String currencyCode;

    @ApiModelProperty(value = "是否同意承诺书")
    @TableField("IS_AGREED_PROMIS")
    private String isAgreedPromis;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "是否包含工资卡")
    @TableField("HAS_SALARY_CARD")
    private String hasSalaryCard;


    @ApiModelProperty(value = "归属单位名称")
    @TableField("UNIT_NAME")
    private String unitName;
    @ApiModelProperty(value = "归属部门名称")
    @TableField("DEPT_NAME")
    private String deptName;
    @ApiModelProperty(value = "大类名称")
    @TableField("MAIN_TYPE_NAME")
    private String mainTypeName;
    @ApiModelProperty(value = "项目名称")
    @TableField(value = "PROJECT_NAME",strategy = FieldStrategy.IGNORED)
    private String projectName;
    @ApiModelProperty(value = "关联事项名称")
    @TableField("APPLY_FOR_NAME")
    private String applyForName;

    @TableField("IS_GENERATE_PAY_ORDER")
    @ApiModelProperty("是否生成过付款单,提交时修改此状态 Y N")
    private String isGeneratePayOrder;

    @ApiModelProperty(value = "关联付款单Id")
    @TableField(value = "PAYMENT_ID",strategy = FieldStrategy.IGNORED)
    private Long payMentId;

    @ApiModelProperty(value = "项目预算会计科目")
    @TableField("PROJECT_BUDGET_ACCOUNT_CODE")
    private String projectBudgetAccountCode;

    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String EX1 = "EX1";

    public static final String EX2 = "EX2";

    public static final String EX3 = "EX3";

    public static final String EX4 = "EX4";

    public static final String EX5 = "EX5";

    public static final String VERSION = "VERSION";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String DOCUMENT_TYPE="DOCUMENT_TYPE";

    public static final String DOCUMENT_STATUS = "DOCUMENT_STATUS";

    public static final String PAY_STATUS = "PAY_STATUS";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String APPLY_FOR_ID = "APPLY_FOR_ID";

    public static final String MAIN_TYPE_ID = "MAIN_TYPE_ID";

    public static final String PAYMENT_TYPE = "PAYMENT_TYPE";

    public static final String REPAYMENT_TIME = "REPAYMENT_TIME";

    public static final String BORROW_AMOUNT = "BORROW_AMOUNT";

    public static final String HAS_VER_AMOUNT = "HAS_VER_AMOUNT";

    public static final String NO_VER_AMOUNT = "NO_VER_AMOUNT";

    public static final String CURRENCY_CODE = "CURRENCY_CODE";

    public static final String IS_AGREED_PROMIS = "IS_AGREED_PROMIS";

    public static final String REMARK = "REMARK";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
