package com.deloitte.platform.api.fssc.ppc.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :  ProjectRecieveDetail查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRecieveDetailQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long createBy;
    private String createUserName;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long documentId;
    private String documentType;
    private Long version;
    private Long connectDocumentId;
    private String connectDocumentType;
    private String connectDocumentNum;
    private String recieveUnitName;
    private String recieveBankName;
    private String recieveBankType;
    private String recieveBankAccount;
    private Long inComeSubTypeId;
    private String inComeSubTypeCode;
    private String inComeSubTypeName;
    private BigDecimal recieveAmount;
    private BigDecimal recieveConfirmAmount;
    private LocalDateTime recieveTime;
    private String payBankName;
    private String payBankAccount;
    private String payUnitName;
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
    private String inComeSubSubjectCode;
    private Long connectRecieveLineId;


}
