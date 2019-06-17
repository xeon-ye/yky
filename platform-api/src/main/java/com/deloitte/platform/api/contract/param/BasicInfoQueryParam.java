package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : zhengchun
 * @Date : Create in 2019-04-03
 * @Description :  BasicInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String contractNo;
    private String contractName;
    private String oldContractId;
    private String oldContractNo;
    private String oldContractName;
    private String contractSerialNumber;
    private String incomeExpenditureTypeCode;
    private String incomeExpenditureType;
    private String contractTypeCode;
    private String contractType;
    private String contractNatureCode;
    private String contractNature;
    private String templateAttributeCode;
    private String templateAttribute;
    private String templateAttributeRemark;
    private String templateCode;
    private String templateName;
    private String contractCurrencyCode;
    private String contractCurrency;
    private String contractCurrencyRemark;
    private String otherWayCode;
    private String otherWay;
    private String otherWayRemark;
    private String sourcesFunding;
    private String paymentWayCode;
    private String paymentWay;
    private String paymentWayRemark;
    private String billingRatio;
    private Long oldAmountIncludTax;
    private Long oldTaxRate;
    private Long oldTaxAmount;
    private Long oldAmountExcludTax;
    private String oldAmountExcludTaxCapital;
    private Long reportedAmount;
    private Long paidAmount;
    private Long amountIncludTax;
    private Long taxRate;
    private Long taxRateRemark;
    private Long taxAmount;
    private Long amountExcludTax;
    private String amountExcludTaxCapital;
    private String isPayDepositCode;
    private String isPayDeposit;
    private Long contractDeposit;
    private LocalDateTime performanceEffectiveTime;
    private String isForeignContract;
    private String operatorCode;
    private String operator;
    private String oldOperatorCode;
    private String oldOperator;
    private String orgCode;
    private String org;
    private String contactNum;
    private String oldOrgCode;
    private String oldOrg;
    private String oldContactNum;
    private String executeInfoCode;
    private String executeInfo;
    private String executeInfoRemark;
    private String executeCause;
    private LocalDateTime executeStartTime;
    private LocalDateTime executeEndTime;
    private String contractBody;
    private String contractAttament;
    private String contractStartupBasis;
    private String surePersonCode;
    private String surePerson;
    private String executerCode;
    private String executer;
    private String oldExecutePersonCode;
    private String oldExecutePerson;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private LocalDateTime disabledTime;
    private String disabledBy;
    private String statue;
    private String sureStatue;
    private String signStatue;
    private String executeStatue;
    private String finishStatue;
    private String spareField1;
    private String spareField2;
    private String spareField3;
    private LocalDateTime spareField4;
    private Long spareField5;
    private String relationCode;
    private String relation;
    private LocalDateTime sureStatueTime;
    private LocalDateTime signStatueTime;
    private LocalDateTime executeStatueTime;
    private LocalDateTime finishStatueTime;
    private LocalDateTime statueTime;
    private String performanceTypeCode;
    private String performanceType;
    private String performanceEffectiveCond;
    private String executeType;
    private String executeTypeCode;
    private Long executeLong;
    private String timeType;
    private String engCond;
    private String evaluateStatue;
    private LocalDateTime validTime;
    private String review;
    private String approvalType;

}
