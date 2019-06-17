package com.deloitte.platform.api.fssc.general.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :  GeGeneralExpense查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeGeneralExpenseQueryParam extends BaseParam {
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
    private String unitName;
    private String deptName;
    private String paymentType;
    private String projectName;
    private Long projectId;
    private Long applyForId;
    private String applyForName;
    private String mainTypeName;
    private Long mainTypeId;
    private String remark;
    private String currencyCode;
    private BigDecimal cost;
    private Long attachCount;
    private BigDecimal totalAmount;
    private BigDecimal expenseAmount;
    private BigDecimal verificationAmount;
    private BigDecimal paySalaryAmount;
    private BigDecimal businussAmount;
    private BigDecimal payCompanyAmount;
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

}
