package com.deloitte.platform.api.fssc.bank.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-02-26
 * @Description :  BankInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String bankCode;
    private String bankName;
    private String branchBankName;
    private String interBranchNumber;
    private String bankInternationalCode;
    private String address;
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
    private String remark;
    private String isValid;
    private String auditAStatus;
}
