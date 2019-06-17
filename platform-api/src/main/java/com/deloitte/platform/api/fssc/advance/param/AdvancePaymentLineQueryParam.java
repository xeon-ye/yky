package com.deloitte.platform.api.fssc.advance.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description :  BmAdvancePaymentLine查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvancePaymentLineQueryParam extends BaseParam {
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
    private Long mainTypeId;
    private String mainTypeCode;
    private Long subTypeId;
    private String subTypeCode;
    private String accountCode;
    private String budgetAccountCode;
    private BigDecimal prepaidAmount;
    private String remark;
    private Long documentId;
    private String mainTypeName;
    private String subTypeName;
    private Long lineNumber;
    private String documentType;


}
