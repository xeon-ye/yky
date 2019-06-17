package com.deloitte.platform.api.fssc.basecontract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description :  BaseContractPlanLine查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseContractPlanLineQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long createBy;
    private String createUserName;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long version;
    private String contractNo;
    private String contractName;
    private Long travelPlanId;
    private String unitName;
    private String deptName;
    private String sideSubjectName;
    private String appointPayStage;
    private String agreedPropertions;
    private Double agreedPaymentAmount;
    private LocalDateTime planPayTime;
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
    private BigDecimal hasVerAmount;
    private BigDecimal actualPlayAmount;
    private BigDecimal receipPlayAmount;
    private BigDecimal paidAmount;

}
