package com.deloitte.platform.api.fssc.borrow.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-03-06
 * @Description :  BmBorrowBank查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BmBorrowBankQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long createBy;
    private String createUserName;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private String ex1;
    private String ex2;
    private String ex3;
    private String ex4;
    private String ex5;
    private Long version;
    private String documentNum;
    private String payStatus;
    private BigDecimal borrowAmount;
    private String getOrReturn;
     private Long documentId;

     private String documentType;

    private LocalDateTime payTime;
    private String empNo;
    private Long empId;
    private String empName;
    private String bankName;

    private String bankAccount;

    private String interBranchNumber;

 }
