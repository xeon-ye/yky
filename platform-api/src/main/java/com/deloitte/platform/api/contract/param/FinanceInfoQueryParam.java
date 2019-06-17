package com.deloitte.platform.api.contract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : yangyq
 * @Date : Create in 2019-04-17
 * @Description :  FinanceInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String ourContractEntityCode;
    private String ourContractEntity;
    private String otherContractEntityCode;
    private String otherContractEntity;
    private String otherBankCode;
    private String otherBank;
    private String otherAccountName;
    private String otherAccount;
    private String appointPayStage;
    private String appointPayCondition;
    private Long appointPayRate;
    private Double appointPayAmount;
    private Double actPayRate;
    private LocalDateTime planPayTime;
    private LocalDateTime actPayTime;
    private String isManual;
    private String isUsed;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private Long contractId;
    private String contractName;
    private String spareField1;
    private String spareField2;
    private String spareField3;
    private LocalDateTime spareField4;
    private Long spareField5;
    private String appointIncomeStage;
    private String appointIncomeCondition;
    private Long appointIncomeRate;
    private Double appointIncomeAmount;
    private Double actIncomeRate;
    private LocalDateTime planIncomeTime;
    private LocalDateTime actIncomeTime;
    private String isManualIncome;
    private String contractType;
    private String type;

}
