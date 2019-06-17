package com.deloitte.platform.api.fssc.contract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : hjy
 * @Date : Create in 2019-03-25
 * @Description :  CrbContractReimburseBill查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrbContractReimburseBillQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long createBy;
    private String createUserName;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long version;
    private String documentNum;
    private String documentStatus;
    private String payStatus;
    private Long unitId;
    private Long deptId;
    private Long projectId;
    private Long projectUnitId;
    private String projectUnitCode;
    private String projectUnitName;
    private Long mainTypeId;
    private BigDecimal totalSum;
    private String currencyCode;
    private String isAgreedPromis;
    private String remark;
    private String unitName;
    private String deptName;
    private String mainTypeName;
    private String projectName;
    private String paymentType;
    private String supplier;
    private Long supplierId;
    private Long contactNumberId;
    private String contactNumber;
    private Long supportFileNum;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String ext6;
    private String ext7;
    private String ext8;
    private String ext9;
    private String ext10;
    private String ext11;
    private String ext12;
    private String ext13;
    private String ext14;
    private String ext15;
    private String unitCode;
    private String deptCode;
    private String projectCode;
    private String supplierCode;
    private String mainTypeCode;
    private String contactName;
    private BigDecimal totalAmountReimburse;
    private BigDecimal verAmount;
    private BigDecimal actualPaymentAmount;
    private LocalDateTime postingTime;
    private BigDecimal cost;
    private BigDecimal totalSumPosition;
    private BigDecimal verAmountBusiness;
    private BigDecimal verAmountExpense;
    private BigDecimal businessRepayment;
    private BigDecimal expensePayment;
    private String fsscCode;
    private BigDecimal noTaxAmount;
    private BigDecimal taxAmount;

}
