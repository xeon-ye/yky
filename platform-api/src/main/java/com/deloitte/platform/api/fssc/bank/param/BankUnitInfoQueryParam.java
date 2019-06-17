package com.deloitte.platform.api.fssc.bank.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-02-26
 * @Description :  BankUnitInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankUnitInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long unitId;
    private String bankAccount;
    private Long subjectId;
    private String auditStatus;
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
    private String bankType;
    private String isBankDrectLink;
    private String isValid;
    private String remark;
    private Long bankId;
    private String currencyCode;

    private String subjectName;
}
