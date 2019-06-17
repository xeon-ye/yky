package com.deloitte.services.fssc.business.advance.entity;

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
 * @author hjy
 * @since 2019-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ADP_CONTACT_DETAIL")
@ApiModel(value="BmContactDetail对象", description="")
public class ContactDetail extends Model<ContactDetail> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建人")
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

    @ApiModelProperty(value = "履行计划ID")
    @TableField("TRAVEL_PLAN_ID")
    private Long travelPlanId;

    @TableField("EX1")
    private String ex1;

    @TableField("EX2")
    private String ex2;

    @TableField("EX3")
    private String ex3;

    @TableField("EX4")
    private String ex4;

    @TableField("EX5")
    @ApiModelProperty(value = "备用字段，是否被报账单关联")
    private String ex5;

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    @Version
    private Long version;

    @ApiModelProperty(value = "支出大类ID")
    @TableField("MAIN_TYPE_ID")
    private Long mainTypeId;

    @TableField("MAIN_TYPE_CODE")
    @ApiModelProperty(value = "大类编码")
    private String mainTypeCode;

    @ApiModelProperty(value = "支出小类ID")
    @TableField("SUB_TYPE_ID")
    private Long subTypeId;

    @TableField("SUB_TYPE_CODE")
    @ApiModelProperty(value = "支出小类编码")
    private String subTypeCode;

    @TableField("ACCOUNT_CODE")
    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    @TableField("BUDGET_ACCOUNT_CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "对公预付款ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "支出大类名称")
    @TableField("MAIN_TYPE_NAME")
    private String mainTypeName;

    @ApiModelProperty(value = "支出小类名称")
    @TableField("SUB_TYPE_NAME")
    private String subTypeName;

    @ApiModelProperty(value = "合同名称")
    @TableField("CONTACT_NAME")
    private String contactName;

    @ApiModelProperty(value = "合同id")
    @TableField("CONTACT_NUMBER_ID")
    private Long contactNumberId;

    @ApiModelProperty(value = "合同编号")
    @TableField("CONTACT_NUMBER")
    private String contactNumber;


    @ApiModelProperty(value = "对方签约主体")
    @TableField("SUBJECT_SUPERFICIL")
    private String subjectSuperficil;

    @ApiModelProperty("对方签约主体code")
    @TableField("SUBJECT_SUPERFICIL_CODE")
    private String subjectSuperficilCode;

    @ApiModelProperty(value = "约定付款批次")
    @TableField("AGREED_PAYMENT_LOT")
    private String agreedPaymentLot;

    @ApiModelProperty(value = "约定付款金额")
    @TableField("AGREED_PAYMENT_AMOUNT")
    private BigDecimal agreedPaymentAmount;

    @ApiModelProperty(value = "约定比例")
    @TableField("AGREED_PROPERTIONS")
    private String  agreedPropertions;

    @ApiModelProperty(value = "计划还款时间")
    @TableField("PLAN_PAYMENT_TIME")
    private LocalDateTime planPaymentTime;

    @ApiModelProperty(value = "关联单据类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "发票号")
    @TableField("INVOICE_NUMBER")
    private String invoiceNumber;

    @ApiModelProperty(value = "税额")
    @TableField("TAX_AMOUNT")
    private BigDecimal taxMmount;

    @ApiModelProperty(value = "税率")
    @TableField("TAX_RATE")
    private BigDecimal taxRate;

    @ApiModelProperty(value="行号")
    @TableField("LINE_NUMBER")
    private Long lineNumber;

    @ApiModelProperty(value="实际付款金额")
    @TableField("ACTUAL_PLAY_AMOUNT")
    private BigDecimal actualPlayAmount;

    @ApiModelProperty(value = "我方签约主体")
    @TableField("OUR_SUBJECT_MAINSTAY")
    private String ourSubjectMainstay;

    @ApiModelProperty(value = "履行部门")
    @TableField("PERFORMANCE_DEPARTMENT")
    private String  performanageDepartment;

    @ApiModelProperty(value = "履行人")
    @TableField("EXECUTOR")
    private String  executor;

    @ApiModelProperty(value = "已核销金额")
    @TableField("HAS_VER_AMOUNT")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    @TableField("NO_VER_AMOUNT")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "付款方式")
    @TableField("PAYMENT_TYPE")
    private String paymentType;

    @TableField("PAID_AMOUNT")
    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;

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

    public static final String MAIN_TYPE_ID = "MAIN_TYPE_ID";

    public static final String SUB_TYPE_ID = "SUB_TYPE_ID";

    public static final String REMARK = "REMARK";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String MAIN_TYPE_NAME = "MAIN_TYPE_NAME";

    public static final String SUB_TYPE_NAME = "SUB_TYPE_NAME";

    public static final String CONTACT_NAME = "CONTACT_NAME";

    public static final String CONTACT_NUMBER_ID = "CONTACT_NUMBER_ID";

    public static final String CONTACT_NUMBER = "CONTACT_NUMBER";


    public static final String SUBJECT_SUPERFICIL = "SUBJECT_SUPERFICIL";

    public static final String AGREED_PAYMENT_LOT = "AGREED_PAYMENT_LOT";

    public static final String AGREED_PAYMENT_AMOUNT = "AGREED_PAYMENT_AMOUNT";

    public static final String AGREED_PROPERTIONS = "AGREED_PROPERTIONS";

    public static final String PLAN_PAYMENT_TIME = "PLAN_PAYMENT_TIME";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String INVOICE_NUMBER = "INVOICE_NUMBER";

    public static final String TAX_AMOUNT = "TAX_AMOUNT";

    public static final String TAX_RATE = "TAX_RATE";

    public static final String LINE_NUMBER = "LINE_NUMBER";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
