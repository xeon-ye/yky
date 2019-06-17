package com.deloitte.platform.api.srpmp.project.task.param;

import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**

 * @Author : zhouchen
 * @Date : Create in 2019-04-17
 * @Description :  SrpmsProjectTaskReform查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectTaskReformQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String projectCode;
    private String projectName;
    private Double applyFunds;
    private LocalDateTime projectActionDateStart;
    private LocalDateTime projectActionDateEnd;
    private Long projectApplicantId;
    private String contentTargetProblem;
    private String projectExpectTarget;
    private String projectTechnicalInnovation;
    private String assessmentIndicators;
    private String researchYearPlan;
    private String projectAbstract;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String budgetDescription;

}
