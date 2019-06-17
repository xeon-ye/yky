package com.deloitte.platform.api.srpmp.project.apply.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : lixin
 * @Date : Create in 2019-02-20
 * @Description :  SrpmsProjectApplySchTeach查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectApplySchTeachQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectName;
    private String baseScienceTchType;
    private String firstLevelDiscipline;
    private String twoLevelDiscipline;
    private Double applyFunds;
    private LocalDateTime projectActionDateStart;
    private LocalDateTime projectActionDateEnd;
    private Long projectLeaderId;
    private String projectKeyword;
    private String projectAbstract;
    private String approvalBasisMeaning;
    private String contentTargetProblem;
    private String researchSchemeFeasibility;
    private String projectFeatureInnovate;
    private String projectExpectAchievement;
    private String researchFoundation;
    private String workinfConditions;
    private String projectLeaderIntroduction;
    private String projectBudgetDetail;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
