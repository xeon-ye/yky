package com.deloitte.platform.api.fssc.pay.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description :  PyPamentOrderInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PyPamentOrderInfoQueryParam extends BaseParam {
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
    private String unitCode;
    private Long unitId;
    private String unitName;
    private String deptCode;
    private Long deptId;
    private String deptName;
    private String remark;
    private String currencyCode;
    private BigDecimal cost;
    private BigDecimal totalAmount;
    private BigDecimal totalAmountOtherCurrency;
    private BigDecimal paidAmount;
    private BigDecimal noPaidAmount;
    private String payDocumentNum;
    private String payDocumentType;
    private String paymentType;
    private String bankAccount;
    private String bankType;
    private String isAfterPatch;
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
    private  String payStatus;
    private  String isPayOrder;
    private String accountCode;
    private Long bankId;
    private String budgetAccountCode;

}
