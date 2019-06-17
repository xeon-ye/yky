package com.deloitte.platform.api.srpmp.project.apply.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : caoyue
 * @Date : Create in 2019-02-21
 * @Description :  SrpmsProjectApplySchStu查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectApplySchStuQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String baseScienceStuType;
    private String advisorName;
    private String advisorPositionTitle;
    private String advisorResearchDirection;
    private String advisorPhone;
    private String advisorDept;
    private Double applyFunds;
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
    private String firstLevelDiscipline;
    private String secondLevelDiscipline;

}
