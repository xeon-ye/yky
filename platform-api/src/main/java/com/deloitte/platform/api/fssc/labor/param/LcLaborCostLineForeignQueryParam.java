package com.deloitte.platform.api.fssc.labor.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description :  LcLaborCostLineForeign查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LcLaborCostLineForeignQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long createBy;
    private String createUserName;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long version;
    private String documentId;
    private Long subTypeId;
    private String subTypeCode;
    private String recieveUserName;
    private String nationality;
    private LocalDateTime firstInChina;
    private LocalDateTime birthday;
    private String birthAddress;
    private String gender;
    private String hasDomicileInChina;
    private LocalDateTime estimatedTimeOfDeparture;
    private String typeOfEmployment;
    private LocalDateTime typeOfEmploymentTime;
    private Long recieveUserId;
    private String unitName;
    private String unitCode;
    private Long unitId;
    private String position;
    private String certType;
    private String certNum;
    private String distributionStandard;
    private String bankName;
    private String bankCode;
    private String bankAccount;
    private String interBranchNumber;
    private BigDecimal shouldGiveAmount;
    private BigDecimal deductedAmount;
    private BigDecimal realGiveAmount;
    private String subTypeName;
    private String remark;
    private String ex1;
    private String ex2;
    private String ex3;
    private String ex4;
    private String ex5;
    private String ex6;
    private String ex7;
    private String ex8;
    private String ex9;
    private String ex10;
    private String ex11;
    private String ex12;
    private String ex13;
    private String ex14;
    private String ex15;
    private Long lineNumber;

}
