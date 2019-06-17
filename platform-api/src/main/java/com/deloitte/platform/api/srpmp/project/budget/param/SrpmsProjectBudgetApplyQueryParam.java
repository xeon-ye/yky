package com.deloitte.platform.api.srpmp.project.budget.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : lixin
 * @Date : Create in 2019-02-27
 * @Description :  SrpmsProjectBudgetApply查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectBudgetApplyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long projectId;
    private String projectNum;
    private LocalDateTime budgetActionDateStart;
    private LocalDateTime budgetActionDateEnd;
    private Long projectDeptId;
    private Long projectLeadPersonId;
    private Long projectContactPersonId;
    private Long projectFinancePersonId;
    private Long workTimeFix;
    private Long workTimeFlow;
    private Long workTimeAll;
    private String performanceIndicatorDetail;
    private String specifSupport;
    private String specifMoneyPlan;
    private String specifFacility;
    private String specifMaterial;
    private String specifTest;
    private String specifFuel;
    private String specifTravel;
    private String specifPublish;
    private String specifLabour;
    private String specifConsult;
    private String specifOther;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String budgetDetailAll;
    private Double fundSourceAmount;
    private Double fundSourceProject;
    private Double fundSourceSelf;
    private Double fundSourceOther;
    private String projectTarget;
    private String joinDeptName;
    private Double fundSourceAmountYear;
    private Double fundSourceProjectYear;
    private Double fundSourceOtherYear;
    private String performanceLibraryCode;
}
