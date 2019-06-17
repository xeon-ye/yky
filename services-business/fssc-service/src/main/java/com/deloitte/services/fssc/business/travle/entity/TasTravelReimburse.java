package com.deloitte.services.fssc.business.travle.entity;

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
 * @since 2019-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("TAS_TRAVEL_REIMBURSE")
@ApiModel(value="TasTravelReimburse对象", description="")
public class TasTravelReimburse extends Model<TasTravelReimburse> {

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

    @ApiModelProperty(value = "归属单位ID")
    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "归属部门ID")
    @TableField("DEPT_ID")
    private Long deptId;

    @ApiModelProperty(value = "项目ID")
    @TableField(value = "PROJECT_ID",strategy = FieldStrategy.IGNORED)
    private Long projectId;

    @TableField("PROJECT_UNIT_ID")
    @ApiModelProperty(value = "项目承担单位ID")
    private Long projectUnitId;

    @TableField("PROJECT_UNIT_CODE")
    @ApiModelProperty(value = "项目承担单位编码")
    private String projectUnitCode;

    @TableField("PROJECT_UNIT_NAME")
    @ApiModelProperty(value = "项目承担单位名称")
    private String projectUnitName;

    @ApiModelProperty(value = "支出大类ID")
    @TableField("MAIN_TYPE_ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "合计金额")
    @TableField("TOTAL_SUM")
    private BigDecimal totalSum;

    @ApiModelProperty(value = "是否同意承诺书")
    @TableField("IS_AGREED_PROMIS")
    private String isAgreedPromis;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

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

    @TableField("IS_GENERATE_PAY_ORDER")
    @ApiModelProperty("是否生成过付款单,提交时修改此状态 Y N")
    private String isGeneratePayOrder;

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

    @ApiModelProperty(value = "单位编码")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "部门编码")
    @TableField("DEPT_CODE")
    private String deptCode;

    @ApiModelProperty(value = "项目编码")
    @TableField(value = "PROJECT_CODE",strategy = FieldStrategy.IGNORED)
    private String projectCode;

    @ApiModelProperty(value = "支出大类编码")
    @TableField("MAIN_TYPE_CODE")
    private String mainTypeCode;

    @ApiModelProperty(value = "币种")
    @TableField("CURRENCY_CODE")
    private String currencyCode;

    @ApiModelProperty(value = "付款方式")
    @TableField("PAYMENT_TYPE")
    private String paymentType;

    @ApiModelProperty(value = "出差人数")
    @TableField("TRAVEL_PEOPLE_NUM")
    private Long travelPeopleNum;

    @ApiModelProperty(value = "已支付金额")
    @TableField("PAID_AMOUNT")
    private BigDecimal paidAmount;

    @ApiModelProperty(value = "未支付金额")
    @TableField("NO_PAID_AMOUNT")
    private BigDecimal noPaidAmount;

    @ApiModelProperty(value = "费率")
    @TableField("COST")
    private BigDecimal cost;

    @ApiModelProperty(value = "合计金额本币位")
    @TableField("TOTAL_SUM_POSITION")
    private BigDecimal totalSumPosition;


    @ApiModelProperty(value = "关联差旅申请CODE")
    @TableField("APPLY_FOR_CODE")
    private String applyForCode;

    @ApiModelProperty(value = "关联差旅申请id")
    @TableField("APPLY_FOR_ID")
    private Long applyForId;

    @ApiModelProperty(value = "付款状态")
    @TableField("PAY_STATUS")
    private String payStatus;

    @ApiModelProperty(value = "支持附件数量")
    @TableField("SUPPORT_FILE_NUM")
    private Long supportFileNum;

    @ApiModelProperty(value = "是否事后补单")
    @TableField("IS_AFTER_PATCH")
    private String isAfterPatch;

    @ApiModelProperty(value = "报销总金额")
    @TableField("EXPENSE_AMOUNT")
    private BigDecimal expenseAmount;

    @ApiModelProperty(value = "公务卡还款金额")
    @TableField("BUSINUSS_AMOUNT")
    private BigDecimal businussAmount;

    @ApiModelProperty(value = "核销金额")
    @TableField("VERIFICATION_AMOUNT")
    private BigDecimal verificationAmount;

    @ApiModelProperty(value = "未核销金额")
    @TableField("NO_VER_AMOUNT")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "对公付款金额")
    @TableField("PAY_COMPANY_AMOUNT")
    private BigDecimal payCompanyAmount;

    @ApiModelProperty(value = "支付至工资卡金额")
    @TableField("PAY_SALARY_AMOUNT")
    private BigDecimal paySalaryAmount;

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "组织PATH")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "关联付款单Id")
    @TableField(value="PAYMENT_ID",strategy = FieldStrategy.IGNORED)
    private Long payMentId;

    @ApiModelProperty(value = "核销金额公务卡")
    @TableField("VER_AMOUNT_BUSINESS")
    private BigDecimal verAmountBusiness;
    @ApiModelProperty(value = "核销金额对公付款")
    @TableField("VER_AMOUNT_EXPENSE")
    private BigDecimal verAmountExpense;
    @ApiModelProperty(value = "核销金额工资卡")
    @TableField("VER_AMOUNT_SALARY")
    private BigDecimal verAmountSalary;

    @TableField("FSSC_CODE")
    @ApiModelProperty(value = "项目预算编码")
    private String fsscCode;

    @ApiModelProperty(value = "项目预算会计科目")
    @TableField("PROJECT_BUDGET_ACCOUNT_CODE")
    private String projectBudgetAccountCode;

    @ApiModelProperty(value = "不含税金额")
    @TableField("NO_TAX_AMOUNT")
    private BigDecimal noTaxAmount;

    @ApiModelProperty(value = "税额")
    @TableField("TAX_AMOUNT")
    private BigDecimal taxAmount;

    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String DOCUMENT_STATUS = "DOCUMENT_STATUS";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String DEPT_ID = "DEPT_ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String MAIN_TYPE_ID = "MAIN_TYPE_ID";

    public static final String TOTAL_SUM = "TOTAL_SUM";

    public static final String IS_AGREED_PROMIS = "IS_AGREED_PROMIS";

    public static final String REMARK = "REMARK";

    public static final String UNIT_NAME = "UNIT_NAME";

    public static final String DEPT_NAME = "DEPT_NAME";

    public static final String MAIN_TYPE_NAME = "MAIN_TYPE_NAME";

    public static final String PROJECT_NAME = "PROJECT_NAME";

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

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String DEPT_CODE = "DEPT_CODE";

    public static final String PROJECT_CODE = "PROJECT_CODE";

    public static final String MAIN_TYPE_CODE = "MAIN_TYPE_CODE";

    public static final String CURRENCY_CODE = "CURRENCY_CODE";

    public static final String PAYMENT_TYPE = "PAYMENT_TYPE";

    public static final String TRAVEL_PEOPLE_NUM = "TRAVEL_PEOPLE_NUM";

    public static final String PAID_AMOUNT = "PAID_AMOUNT";

    public static final String NO_PAID_AMOUNT = "NO_PAID_AMOUNT";

    public static final String ASSOCIAT_TRAVEL_APPLY = "ASSOCIAT_TRAVEL_APPLY";

    public static final String PAY_STATUS = "PAY_STATUS";

    public static final String SUPPORT_FILE_NUM = "SUPPORT_FILE_NUM";

    public static final String IS_AFTER_PATCH = "IS_AFTER_PATCH";

    public static final String EXPENSE_AMOUNT = "EXPENSE_AMOUNT";

    public static final String BUSINUSS_AMOUNT = "BUSINUSS_AMOUNT";

    public static final String VERIFICATION_AMOUNT = "VERIFICATION_AMOUNT";

    public static final String PAY_COMPANY_AMOUNT = "PAY_COMPANY_AMOUNT";

    public static final String PAY_SALARY_AMOUNT = "PAY_SALARY_AMOUNT";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
