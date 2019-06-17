package com.deloitte.services.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.deloitte.platform.api.contract.vo.SysSignSubjectInfoForm;
import com.deloitte.platform.api.contract.vo.SysSignSubjectInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 合同基本信息
 * </p>
 *
 * @author zhengchun
 * @since 2019-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_BASIC_INFO")
@ApiModel(value="BasicInfo对象", description="合同基本信息")
public class BasicInfo extends Model<BasicInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "合同编号")
    @TableField("CONTRACT_NO")
    private String contractNo;

    @ApiModelProperty(value = "合同名称")
    @TableField("CONTRACT_NAME")
    private String contractName;

    @ApiModelProperty(value = "原合同id")
    @TableField("OLD_CONTRACT_ID")
    private String oldContractId;

    @ApiModelProperty(value = "原合同编号")
    @TableField("OLD_CONTRACT_NO")
    private String oldContractNo;

    @ApiModelProperty(value = "原合同名称")
    @TableField("OLD_CONTRACT_NAME")
    private String oldContractName;

    @ApiModelProperty(value = "合同流水号")
    @TableField("CONTRACT_SERIAL_NUMBER")
    private String contractSerialNumber;

    @ApiModelProperty(value = "收支类型编号")
    @TableField("INCOME_EXPENDITURE_TYPE_CODE")
    private String incomeExpenditureTypeCode;

    @ApiModelProperty(value = "收支类型")
    @TableField("INCOME_EXPENDITURE_TYPE")
    private String incomeExpenditureType;

    @ApiModelProperty(value = "合同类型编号")
    @TableField("CONTRACT_TYPE_CODE")
    private String contractTypeCode;

    @ApiModelProperty(value = "合同类型")
    @TableField("CONTRACT_TYPE")
    private String contractType;

    @ApiModelProperty(value = "合同性质编号")
    @TableField("CONTRACT_NATURE_CODE")
    private String contractNatureCode;

    @ApiModelProperty(value = "合同性质")
    @TableField("CONTRACT_NATURE")
    private String contractNature;

    @ApiModelProperty(value = "标准文本属性编号")
    @TableField("TEMPLATE_ATTRIBUTE_CODE")
    private String templateAttributeCode;

    @ApiModelProperty(value = "标准文本属性")
    @TableField("TEMPLATE_ATTRIBUTE")
    private String templateAttribute;

    @ApiModelProperty(value = "标准文本属性补充（选择方式为其他时填写）")
    @TableField("TEMPLATE_ATTRIBUTE_REMARK")
    private String templateAttributeRemark;

    @ApiModelProperty(value = "标准文本编号")
    @TableField("TEMPLATE_CODE")
    private String templateCode;

    @ApiModelProperty(value = "标准文本名称")
    @TableField("TEMPLATE_NAME")
    private String templateName;

    @ApiModelProperty(value = "合同币种编号")
    @TableField("CONTRACT_CURRENCY_CODE")
    private String contractCurrencyCode;

    @ApiModelProperty(value = "合同币种")
    @TableField("CONTRACT_CURRENCY")
    private String contractCurrency;

    @ApiModelProperty(value = "合同币种补充（选择方式为其他时填写）")
    @TableField("CONTRACT_CURRENCY_REMARK")
    private String contractCurrencyRemark;

    @ApiModelProperty(value = "对方选择方式编号")
    @TableField("OTHER_WAY_CODE")
    private String otherWayCode;

    @ApiModelProperty(value = "对方选择方式")
    @TableField("OTHER_WAY")
    private String otherWay;

    @ApiModelProperty(value = "对方选择方式补充（选择方式为其他时填写）")
    @TableField("OTHER_WAY_REMARK")
    private String otherWayRemark;

    @ApiModelProperty(value = "经费来源")
    @TableField("SOURCES_FUNDING")
    private String sourcesFunding;

    @ApiModelProperty(value = "收付款方式编号")
    @TableField("PAYMENT_WAY_CODE")
    private String paymentWayCode;

    @ApiModelProperty(value = "收付款方式")
    @TableField("PAYMENT_WAY")
    private String paymentWay;

    @ApiModelProperty(value = "收付款方式补充（选择方式为其他时填写）")
    @TableField("PAYMENT_WAY_REMARK")
    private String paymentWayRemark;

    @ApiModelProperty(value = "结算比例")
    @TableField("BILLING_RATIO")
    private String billingRatio;

    @ApiModelProperty(value = "原含税合同金额")
    @TableField("OLD_AMOUNT_INCLUD_TAX")
    private BigDecimal oldAmountIncludTax;

    @ApiModelProperty(value = "原税率")
    @TableField("OLD_TAX_RATE")
    private BigDecimal oldTaxRate;

    @ApiModelProperty(value = "原税额")
    @TableField("OLD_TAX_AMOUNT")
    private BigDecimal oldTaxAmount;

    @ApiModelProperty(value = "原不含税合同金额")
    @TableField("OLD_AMOUNT_EXCLUD_TAX")
    private BigDecimal oldAmountExcludTax;

    @ApiModelProperty(value = "原含税合同金额大写（汉字版本）")
    @TableField("OLD_AMOUNT_EXCLUD_TAX_CAPITAL")
    private String oldAmountExcludTaxCapital;

    @ApiModelProperty(value = "已报账金额")
    @TableField("REPORTED_AMOUNT")
    private BigDecimal reportedAmount;

    @ApiModelProperty(value = "已付款金额")
    @TableField("PAID_AMOUNT")
    private BigDecimal paidAmount;

    @ApiModelProperty(value = "含税合同金额")
    @TableField("AMOUNT_INCLUD_TAX")
    private BigDecimal amountIncludTax;

    @ApiModelProperty(value = "税率")
    @TableField("TAX_RATE")
    private BigDecimal taxRate;

    @ApiModelProperty(value = "税率补充（选择方式为其他时填写）")
    @TableField("TAX_RATE_REMARK")
    private BigDecimal taxRateRemark;

    @ApiModelProperty(value = "税额")
    @TableField("TAX_AMOUNT")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "不含税合同金额")
    @TableField("AMOUNT_EXCLUD_TAX")
    private BigDecimal amountExcludTax;

    @ApiModelProperty(value = "含税合同金额大写（汉字版本）")
    @TableField("AMOUNT_EXCLUD_TAX_CAPITAL")
    private String amountExcludTaxCapital;

    @ApiModelProperty(value = "保证金/保函编号")
    @TableField("IS_PAY_DEPOSIT_CODE")
    private String isPayDepositCode;

    @ApiModelProperty(value = "保证金/保函")
    @TableField("IS_PAY_DEPOSIT")
    private String isPayDeposit;

    @ApiModelProperty(value = "保证金/保函金额")
    @TableField("CONTRACT_DEPOSIT")
    private BigDecimal contractDeposit;

    @ApiModelProperty(value = "明确到期时间")
    @TableField("PERFORMANCE_EFFECTIVE_TIME")
    private LocalDateTime performanceEffectiveTime;

    @ApiModelProperty(value = "是否为涉外合同")
    @TableField("IS_FOREIGN_CONTRACT")
    private String isForeignContract;

    @ApiModelProperty(value = "经办人编号")
    @TableField("OPERATOR_CODE")
    private String operatorCode;

    @ApiModelProperty(value = "经办人")
    @TableField("OPERATOR")
    private String operator;

    @ApiModelProperty(value = "原经办人编号")
    @TableField("OLD_OPERATOR_CODE")
    private String oldOperatorCode;

    @ApiModelProperty(value = "原经办人")
    @TableField("OLD_OPERATOR")
    private String oldOperator;

    @ApiModelProperty(value = "主办部门编号")
    @TableField("ORG_CODE")
    private String orgCode;

    @ApiModelProperty(value = "主办部门")
    @TableField("ORG")
    private String org;

    @ApiModelProperty(value = "联系电话")
    @TableField("CONTACT_NUM")
    private String contactNum;

    @ApiModelProperty(value = "原主办部门编号")
    @TableField("OLD_ORG_CODE")
    private String oldOrgCode;

    @ApiModelProperty(value = "原主办部门")
    @TableField("OLD_ORG")
    private String oldOrg;

    @ApiModelProperty(value = "原联系电话")
    @TableField("OLD_CONTACT_NUM")
    private String oldContactNum;

    @ApiModelProperty(value = "合同履行情况编号")
    @TableField("EXECUTE_INFO_CODE")
    private String executeInfoCode;

    @ApiModelProperty(value = "合同履行情况")
    @TableField("EXECUTE_INFO")
    private String executeInfo;

    @ApiModelProperty(value = "合同履行情况补充（选择方式为其他时填写）")
    @TableField("EXECUTE_INFO_REMARK")
    private String executeInfoRemark;

    @ApiModelProperty(value = "履行原因说明")
    @TableField("EXECUTE_CAUSE")
    private String executeCause;

    @ApiModelProperty(value = "履行期限-开始时间")
    @TableField("EXECUTE_START_TIME")
    private LocalDateTime executeStartTime;

    @ApiModelProperty(value = "履行期限-结束时间")
    @TableField("EXECUTE_END_TIME")
    private LocalDateTime executeEndTime;

    @ApiModelProperty(value = "合同正文")
    @TableField("CONTRACT_BODY")
    private String contractBody;

    @ApiModelProperty(value = "合同附件")
    @TableField("CONTRACT_ATTAMENT")
    private String contractAttament;

    @ApiModelProperty(value = "合同启动依据")
    @TableField("CONTRACT_STARTUP_BASIS")
    private String contractStartupBasis;

    @ApiModelProperty(value = "定稿人编号")
    @TableField("SURE_PERSON_CODE")
    private String surePersonCode;

    @ApiModelProperty(value = "定稿人")
    @TableField("SURE_PERSON")
    private String surePerson;

    @ApiModelProperty(value = "履行人编号")
    @TableField("EXECUTER_CODE")
    private String executerCode;

    @ApiModelProperty(value = "履行人")
    @TableField("EXECUTER")
    private String executer;

    @ApiModelProperty(value = "原履行人编号")
    @TableField("OLD_EXECUTE_PERSON_CODE")
    private String oldExecutePersonCode;

    @ApiModelProperty(value = "原履行人")
    @TableField("OLD_EXECUTE_PERSON")
    private String oldExecutePerson;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "无效时间")
    @TableField("DISABLED_TIME")
    private LocalDateTime disabledTime;

    @ApiModelProperty(value = "无效人")
    @TableField("DISABLED_BY")
    private String disabledBy;

    @ApiModelProperty(value = "审批状态")
    @TableField("STATUE")
    private String statue;

    @ApiModelProperty(value = "定稿状态")
    @TableField("SURE_STATUE")
    private String sureStatue;

    @ApiModelProperty(value = "签署状态")
    @TableField("SIGN_STATUE")
    private String signStatue;

    @ApiModelProperty(value = "履行状态")
    @TableField("EXECUTE_STATUE")
    private String executeStatue;

    @ApiModelProperty(value = "办结状态")
    @TableField("FINISH_STATUE")
    private String finishStatue;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_1")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_2")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_3")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_4")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_5")
    private BigDecimal spareField5;

    @ApiModelProperty(value = "关联性质编号")
    @TableField("RELATION_CODE")
    private String relationCode;

    @ApiModelProperty(value = "关联性质")
    @TableField("RELATION")
    private String relation;

    @ApiModelProperty(value = "定稿时间")
    @TableField("SURE_STATUE_TIME")
    private LocalDateTime sureStatueTime;

    @ApiModelProperty(value = "签署时间")
    @TableField("SIGN_STATUE_TIME")
    private LocalDateTime signStatueTime;

    @ApiModelProperty(value = "履行时间")
    @TableField("EXECUTE_STATUE_TIME")
    private LocalDateTime executeStatueTime;

    @ApiModelProperty(value = "办结时间")
    @TableField("FINISH_STATUE_TIME")
    private LocalDateTime finishStatueTime;

    @ApiModelProperty(value = "审批时间")
    @TableField("STATUE_TIME")
    private LocalDateTime statueTime;

    @ApiModelProperty(value = "保函到期方式code")
    @TableField("PERFORMANCE_TYPE_CODE")
    private String performanceTypeCode;

    @ApiModelProperty(value = "保函到期方式")
    @TableField("PERFORMANCE_TYPE")
    private String performanceType;

    @ApiModelProperty(value = "条件到期时间")
    @TableField("PERFORMANCE_EFFECTIVE_COND")
    private String performanceEffectiveCond;

    @ApiModelProperty(value = "履行期限方式")
    @TableField("EXECUTE_TYPE")
    private String executeType;

    @ApiModelProperty(value = "履行期限方式code")
    @TableField("EXECUTE_TYPE_CODE")
    private String executeTypeCode;

    @ApiModelProperty(value = "固定时长")
    @TableField("EXECUTE_LONG")
    private Long executeLong;

    @ApiModelProperty(value = "时间类型（年；月；日）")
    @TableField("TIME_TYPE")
    private String timeType;

    @ApiModelProperty(value = "结束条件")
    @TableField("ENG_COND")
    private String engCond;

    @ApiModelProperty(value = "打印状态")
    @TableField("PRINT_STATUE")
    private String printStatue;

    @ApiModelProperty(value = "打印时间")
    @TableField("PRINT_STATUE_TIME")
    private String printStatueTime;

    @ApiModelProperty(value = "查询列表状态")
    @TableField("STATUE_LIST")
    private String statueList;

    @ApiModelProperty(value = "是否印花税")
    @TableField("IS_IMPRINT")
    private String isImprint;

    @ApiModelProperty(value = "基础信息是否发送财务系统")
    @TableField("IS_TO_FSSC")
    private String isToFssc;

    @ApiModelProperty(value = "财务信息是否发送财务系统")
    @TableField("IS_TO_BASIC_FSSC")
    private String isToBasicFssc;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "履行人部门")
    @TableField("EXECUTER_ORG")
    private String executerOrg;

    @ApiModelProperty(value = "履行人部门ID")
    @TableField("EXECUTER_ORG_ID")
    private String executerOrgId;

    @ApiModelProperty(value = "合同评价状态")
    @TableField("EVALUATE_STATUE")
    private String evaluateStatue;

    @ApiModelProperty(value = "生效时间")
    @TableField("VALID_TIME")
    private LocalDateTime validTime;

    @ApiModelProperty(value = "计税金额或件数")
    @TableField("IMPRINT_CASES")
    private String imprintCases;

    @ApiModelProperty(value = "印花税税额")
    @TableField("IMPRINT_AMOUNT")
    private String imprintAmount;

    @ApiModelProperty(value = "合同作废流程key")
    @TableField("CANCEL_PROCESS_KEY")
    private String cancelProcessKey;

    @ApiModelProperty(value = "是否已复审")
    @TableField("REVIEW")
    private String review;

    @ApiModelProperty(value = "办公室负责人审批意见")
    @TableField("APPROVAL_TYPE")
    private String approvalType;

    @ApiModelProperty(value = "合同提交流程key")
    @TableField("SUBMIT_PROCESS_KEY")
    private String submitPocessKey;

    @ApiModelProperty(value = "经费来源value")
    @TableField("SOURCES_FUNDING_VALUE")
    private String sourcesFundingValue;

    @ApiModelProperty(value = "原税率value")
    @TableField("OLD_TAX_RATE_VALUE")
    private String oldTaxRateValue;

    @ApiModelProperty(value = "税率value")
    @TableField("TAX_RATE_VALUE")
    private String taxRateValue;

    @ApiModelProperty(value = "原保证金/保函金额")
    @TableField("OLD_CONTRACT_DEPOSIT")
    private BigDecimal oldContractDeposit;

    @ApiModelProperty(value = "公司")
    @TableField("DEPT")
    private String dept;

    @ApiModelProperty(value = "公司code")
    @TableField("DEPT_CODE")
    private String deptCode;


    public static final String ID = "ID";

    public static final String CONTRACT_NO = "CONTRACT_NO";

    public static final String CONTRACT_NAME = "CONTRACT_NAME";

    public static final String OLD_CONTRACT_ID = "OLD_CONTRACT_ID";

    public static final String OLD_CONTRACT_NO = "OLD_CONTRACT_NO";

    public static final String OLD_CONTRACT_NAME = "OLD_CONTRACT_NAME";

    public static final String CONTRACT_SERIAL_NUMBER = "CONTRACT_SERIAL_NUMBER";

    public static final String INCOME_EXPENDITURE_TYPE_CODE = "INCOME_EXPENDITURE_TYPE_CODE";

    public static final String INCOME_EXPENDITURE_TYPE = "INCOME_EXPENDITURE_TYPE";

    public static final String CONTRACT_TYPE_CODE = "CONTRACT_TYPE_CODE";

    public static final String CONTRACT_TYPE = "CONTRACT_TYPE";

    public static final String CONTRACT_NATURE_CODE = "CONTRACT_NATURE_CODE";

    public static final String CONTRACT_NATURE = "CONTRACT_NATURE";

    public static final String TEMPLATE_ATTRIBUTE_CODE = "TEMPLATE_ATTRIBUTE_CODE";

    public static final String TEMPLATE_ATTRIBUTE = "TEMPLATE_ATTRIBUTE";

    public static final String TEMPLATE_ATTRIBUTE_REMARK = "TEMPLATE_ATTRIBUTE_REMARK";

    public static final String TEMPLATE_CODE = "TEMPLATE_CODE";

    public static final String TEMPLATE_NAME = "TEMPLATE_NAME";

    public static final String CONTRACT_CURRENCY_CODE = "CONTRACT_CURRENCY_CODE";

    public static final String CONTRACT_CURRENCY = "CONTRACT_CURRENCY";

    public static final String CONTRACT_CURRENCY_REMARK = "CONTRACT_CURRENCY_REMARK";

    public static final String OTHER_WAY_CODE = "OTHER_WAY_CODE";

    public static final String OTHER_WAY = "OTHER_WAY";

    public static final String OTHER_WAY_REMARK = "OTHER_WAY_REMARK";

    public static final String SOURCES_FUNDING = "SOURCES_FUNDING";

    public static final String PAYMENT_WAY_CODE = "PAYMENT_WAY_CODE";

    public static final String PAYMENT_WAY = "PAYMENT_WAY";

    public static final String PAYMENT_WAY_REMARK = "PAYMENT_WAY_REMARK";

    public static final String BILLING_RATIO = "BILLING_RATIO";

    public static final String OLD_AMOUNT_INCLUD_TAX = "OLD_AMOUNT_INCLUD_TAX";

    public static final String OLD_TAX_RATE = "OLD_TAX_RATE";

    public static final String OLD_TAX_AMOUNT = "OLD_TAX_AMOUNT";

    public static final String OLD_AMOUNT_EXCLUD_TAX = "OLD_AMOUNT_EXCLUD_TAX";

    public static final String OLD_AMOUNT_EXCLUD_TAX_CAPITAL = "OLD_AMOUNT_EXCLUD_TAX_CAPITAL";

    public static final String REPORTED_AMOUNT = "REPORTED_AMOUNT";

    public static final String PAID_AMOUNT = "PAID_AMOUNT";

    public static final String AMOUNT_INCLUD_TAX = "AMOUNT_INCLUD_TAX";

    public static final String TAX_RATE = "TAX_RATE";

    public static final String TAX_RATE_REMARK = "TAX_RATE_REMARK";

    public static final String TAX_AMOUNT = "TAX_AMOUNT";

    public static final String AMOUNT_EXCLUD_TAX = "AMOUNT_EXCLUD_TAX";

    public static final String AMOUNT_EXCLUD_TAX_CAPITAL = "AMOUNT_EXCLUD_TAX_CAPITAL";

    public static final String IS_PAY_DEPOSIT_CODE = "IS_PAY_DEPOSIT_CODE";

    public static final String IS_PAY_DEPOSIT = "IS_PAY_DEPOSIT";

    public static final String CONTRACT_DEPOSIT = "CONTRACT_DEPOSIT";

    public static final String PERFORMANCE_EFFECTIVE_TIME = "PERFORMANCE_EFFECTIVE_TIME";

    public static final String IS_FOREIGN_CONTRACT = "IS_FOREIGN_CONTRACT";

    public static final String OPERATOR_CODE = "OPERATOR_CODE";

    public static final String OPERATOR = "OPERATOR";

    public static final String OLD_OPERATOR_CODE = "OLD_OPERATOR_CODE";

    public static final String OLD_OPERATOR = "OLD_OPERATOR";

    public static final String ORG_CODE = "ORG_CODE";

    public static final String ORG = "ORG";

    public static final String CONTACT_NUM = "CONTACT_NUM";

    public static final String OLD_ORG_CODE = "OLD_ORG_CODE";

    public static final String OLD_ORG = "OLD_ORG";

    public static final String OLD_CONTACT_NUM = "OLD_CONTACT_NUM";

    public static final String EXECUTE_INFO_CODE = "EXECUTE_INFO_CODE";

    public static final String EXECUTE_INFO = "EXECUTE_INFO";

    public static final String EXECUTE_INFO_REMARK = "EXECUTE_INFO_REMARK";

    public static final String EXECUTE_CAUSE = "EXECUTE_CAUSE";

    public static final String EXECUTE_START_TIME = "EXECUTE_START_TIME";

    public static final String EXECUTE_END_TIME = "EXECUTE_END_TIME";

    public static final String CONTRACT_BODY = "CONTRACT_BODY";

    public static final String CONTRACT_ATTAMENT = "CONTRACT_ATTAMENT";

    public static final String CONTRACT_STARTUP_BASIS = "CONTRACT_STARTUP_BASIS";

    public static final String SURE_PERSON_CODE = "SURE_PERSON_CODE";

    public static final String SURE_PERSON = "SURE_PERSON";

    public static final String EXECUTER_CODE = "EXECUTER_CODE";

    public static final String EXECUTER = "EXECUTER";

    public static final String OLD_EXECUTE_PERSON_CODE = "OLD_EXECUTE_PERSON_CODE";

    public static final String OLD_EXECUTE_PERSON = "OLD_EXECUTE_PERSON";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String DISABLED_TIME = "DISABLED_TIME";

    public static final String DISABLED_BY = "DISABLED_BY";

    public static final String STATUE = "STATUE";

    public static final String SURE_STATUE = "SURE_STATUE";

    public static final String SIGN_STATUE = "SIGN_STATUE";

    public static final String EXECUTE_STATUE = "EXECUTE_STATUE";

    public static final String FINISH_STATUE = "FINISH_STATUE";

    public static final String SPARE_FIELD_1 = "SPARE_FIELD_1";

    public static final String SPARE_FIELD_2 = "SPARE_FIELD_2";

    public static final String SPARE_FIELD_3 = "SPARE_FIELD_3";

    public static final String SPARE_FIELD_4 = "SPARE_FIELD_4";

    public static final String SPARE_FIELD_5 = "SPARE_FIELD_5";

    public static final String RELATION_CODE = "RELATION_CODE";

    public static final String RELATION = "RELATION";

    public static final String SURE_STATUE_TIME = "SURE_STATUE_TIME";

    public static final String SIGN_STATUE_TIME = "SIGN_STATUE_TIME";

    public static final String EXECUTE_STATUE_TIME = "EXECUTE_STATUE_TIME";

    public static final String FINISH_STATUE_TIME = "FINISH_STATUE_TIME";

    public static final String STATUE_TIME = "STATUE_TIME";

    public static final String PERFORMANCE_TYPE_CODE = "PERFORMANCE_TYPE_CODE";

    public static final String PERFORMANCE_TYPE = "PERFORMANCE_TYPE";

    public static final String PERFORMANCE_EFFECTIVE_COND = "PERFORMANCE_EFFECTIVE_COND";

    public static final String EXECUTE_TYPE = "EXECUTE_TYPE";

    public static final String EXECUTE_TYPE_CODE = "EXECUTE_TYPE_CODE";

    public static final String EXECUTE_LONG = "EXECUTE_LONG";

    public static final String TIME_TYPE = "TIME_TYPE";

    public static final String ENG_COND = "ENG_COND";

    public static final String PRINT_STATUE = "PRINT_STATUE";

    public static final String PRINT_STATUE_TIME = "PRINT_STATUE_TIME";

    public static final String STATUE_LIST = "STATUE_LIST";

    public static final String IS_IMPRINT = "IS_IMPRINT";

    public static final String IS_TO_FSSC = "IS_TO_FSSC";

    public static final String REMARK = "REMARK";

    public static final String EXECUTER_ORG_ID = "EXECUTER_ORG_ID";

    public static final String EXECUTER_ORG = "EXECUTER_ORG";

    public static final String EVALUATE_STATUE = "EVALUATE_STATUE";

    public static final String VALID_TIME = "VALID_TIME";

    public static final String IMPRINT_CASES = "IMPRINT_CASES";

    public static final String IMPRINT_AMOUNT = "IMPRINT_AMOUNT";

    public static final String REVIEW = "REVIEW";

    public static final String APPROVAL_TYPE = "APPROVAL_TYPE";

    public static final String CANCEL_PROCESS_KEY = "CANCEL_PROCESS_KEY";

    public static final String SUBMIT_PROCESS_KEY = "SUBMIT_PROCESS_KEY";

    public static final String SOURCES_FUNDING_VALUE = "SOURCES_FUNDING_VALUE";

    public static final String OLD_TAX_RATE_VALUE = "OLD_TAX_RATE_VALUE";

    public static final String TAX_RATE_VALUE = "TAX_RATE_VALUE";

    public static final String OLD_CONTRACT_DEPOSIT = "OLD_CONTRACT_DEPOSIT";

    public static final String DEPT = "DEPT";

    public static final String DEPT_CODE = "DEPT_CODE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
