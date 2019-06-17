package com.deloitte.platform.api.srpmp.project.apply.param;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;
import java.util.List;

/**

 * @Author : zhouchen
 * @Date : Create in 2019-04-17
 * @Description :  SrpmsProjectApplyReform查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectApplyReformQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectName;
    private Double applyFunds;
    private LocalDateTime projectActionDateStart;
    private LocalDateTime projectActionDateEnd;
    private Long projectApplicantId;
    private String expenditureBudgetDetail;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String approvalBasisMeaning;
    private String contentTargetProblem;
    private String researchSchemeFeasibility;
    private String projectExpectTarget;
    private String projectTechnicalInnovation;
    private String researchFoundation;
    private String workinfConditions;
    private String projectApplicantIntroduction;
    private String budgetDescription;
    private String projectAbstract;

    @ApiModelProperty(value = "项目组织单位")
    private String projectOrganizingUnit;

    @ApiModelProperty(value = "项目承担单位")
    private String projectCommitmentUnit;


    @ApiModelProperty(value = "年度预算明细")
    private List<SrpmsProjectBudgetDetailVo> budgetPreYearList;

    @ApiModelProperty(value = "项目负责人信息")
    private JSONObject leadPerson;

    @ApiModelProperty(value = "依托单位信息")
    private JSONObject leadDept;

}
