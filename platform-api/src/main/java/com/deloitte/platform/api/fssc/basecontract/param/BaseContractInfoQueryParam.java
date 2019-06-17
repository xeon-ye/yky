package com.deloitte.platform.api.fssc.basecontract.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description :  BaseContractInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseContractInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long version;
    private Long contractId;
    private String contractNo;
    private String contractName;
    private String sideSubjectName;
    private String ourSubjectName;
    private LocalDateTime executeStatueTime;
    private LocalDateTime executeStartTime;
    private LocalDateTime executeEndTime;
    private String contractType;
    private String statue;
    private String org;
    private String operator;
    private String isPayStampDuty;
    private String isForeignContract;
    private String contractTypeStampDuty;
    private String nameOfTaxable;
    private Double stampDuty;
    private Double generalStampDutyRate;
    private Double taxAmountOrPieces;
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
