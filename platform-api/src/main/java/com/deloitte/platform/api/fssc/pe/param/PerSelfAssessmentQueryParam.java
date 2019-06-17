package com.deloitte.platform.api.fssc.pe.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description :  PerSelfAssessment查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerSelfAssessmentQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long version;
    private String documentNum;
    private String documentStatus;
    private String projectDuty;
    private Long projectDutyId;
    private String projectName;
    private Long doDeptId;
    private String doDeptCode;
    private String doDeptName;
    private Long doUnitId;
    private String doUnitCode;
    private String doUnitName;
    private BigDecimal fundSourceAmount;
    private BigDecimal fundSourceProject;
    private BigDecimal fundSourceOther;
    private BigDecimal fundSourceAmountYear;
    private BigDecimal fundSourceProjectYear;
    private BigDecimal fundSourceOtherYear;
    private BigDecimal executeTotal;
    private BigDecimal executeProject;
    private BigDecimal executeOther;
    private BigDecimal totalScoreValue;
    private BigDecimal projectScoreValue;
    private BigDecimal otherScoreValue;
    private BigDecimal executePercentTotal;
    private BigDecimal executePercentProject;
    private BigDecimal executePercentOther;
    private BigDecimal totalScored;
    private BigDecimal prjectScored;
    private BigDecimal otherScored;
    private BigDecimal projectTarget;
    private String realCompleteStatus;
    private String remark;
    private String ex1;
    private String ex2;
    private String ex3;
    private String ex4;
    private String ex5;

}
