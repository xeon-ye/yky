package com.deloitte.platform.api.srpmp.project.apply.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : lixin
 * @Date : Create in 2019-02-25
 * @Description :  SrpmsProjectApplyAcademy查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectApplyAcademyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectName;
    private Double applyFunds;
    private LocalDateTime projectActionDateStart;
    private LocalDateTime projectActionDateEnd;
    private Long projectApplicantId;
    private String projectAbstract;
    private String approvalBasisMeaning;
    private String contentTargetProblem;
    private String researchSchemeFeasibility;
    private String projectExpectTarget;
    private String projectTechnicalInnovation;
    private String researchFoundation;
    private String workinfConditions;
    private String projectApplicantIntroduction;
    private String projectOrganizingUnit;
    private String projectCommitmentUnit;
    private Double fundSourceAmount;
    private Double fundSourceFinancial;
    private Double fundSourceSelfRaised;
    private Double fundSourceOther;
    private String expenditureBudgetDetail;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String budgetDescription;

}
