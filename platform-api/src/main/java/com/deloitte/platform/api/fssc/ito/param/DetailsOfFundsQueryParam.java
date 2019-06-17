package com.deloitte.platform.api.fssc.ito.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-04-15
 * @Description :  DetailsOfFunds查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsOfFundsQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long createBy;
    private String createUserName;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
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
    private Long incomeSubTypeId;
    private String incomeSubTypeCode;
    private String incomeSubTypeName;
    private Long incomeTypeId;
    private String incomeTypeName;
    private String incomeTypeCode;
    private String accountCode;
    private String budgetAccountCode;
    private Long lineNumber;
    private String receiptNumber;
    private String paidUnit;
    private String paymentBankInfo;
    private String accountType;
    private String paymentBankAccount;
    private BigDecimal amountPaid;
    private LocalDateTime paidTime;
    private String collectionAccount;
    private String collectionNumber;
    private String remark;
    private Long documentId;
    private String documentType;
    private Long receiptLineId;
    private Long bankId;
    private String bankSubjectCode;

    private String budgetBankSubjectCode;

}
