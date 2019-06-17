package com.deloitte.platform.api.fssc.borrow.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description :  BorrowMoneyInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowMoneyInfoQueryParam extends BaseParam {
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
    private String documentStatus;
    private String payStatus;
    private Long unitId;
    private Long deptId;
    private Long projectId;
    private Long applyForId;
    private Long mainTypeId;
    private String paymentType;
    private LocalDateTime repaymentTime;
    private BigDecimal borrowAmount;
    private BigDecimal hasVerAmount;
    private BigDecimal noVerAmount;
    private String currencyCode;
    private String isAgreedPromis;
    private String remark;
    private String hasSalaryCard;
    private BigDecimal cost;
    private Long payMentId;
    private String unitName;

    private String deptName;

    private String mainTypeName;

    private String projectName;

    private String applyForName;
}
