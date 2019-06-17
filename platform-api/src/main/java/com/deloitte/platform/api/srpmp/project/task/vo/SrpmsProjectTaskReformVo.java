package com.deloitte.platform.api.srpmp.project.task.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : zhouchen
 * @Date : Create in 2019-04-17
 * @Description : SrpmsProjectTaskReform返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsProjectTaskReformVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "项目编号")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "经费预算")
    private Double applyFunds;

    @ApiModelProperty(value = "项目执行开始时间")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行结束时间 ")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "项目申请人ID")
    private String projectApplicantId;

    @ApiModelProperty(value = "研究内容")
    private String contentTargetProblem;

    @ApiModelProperty(value = "预期目标")
    private String projectExpectTarget;

    @ApiModelProperty(value = "主要技术特点和创新点")
    private String projectTechnicalInnovation;

    @ApiModelProperty(value = "考核指标")
    private String assessmentIndicators;

    @ApiModelProperty(value = "年度计划")
    private JSONArray researchYearPlan;

    @ApiModelProperty(value = "项目摘要")
    private String projectAbstract;

    @ApiModelProperty(value = "创建时间 ")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人 ")
    private String createBy;

    @ApiModelProperty(value = "更新时间 ")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人 ")
    private String updateBy;

    @ApiModelProperty(value = "预算说明")
    private String budgetDescription;

    @ApiModelProperty(value = "单位学术委员会意见")
    private JSONObject attachmentCommittee;

    @ApiModelProperty(value = "任务书签订各方意见")
    private JSONObject attachmentAudit;

    @ApiModelProperty(value = "单位伦理委员会意见")
    private JSONObject attachmentStatement;

    @ApiModelProperty(value = "任务书弹窗")
    private String firstFlg;

    @ApiModelProperty(value = "项目负责人信息")
    private JSONObject leadPerson;

    @ApiModelProperty(value = "依托单位信息")
    private JSONObject leadDept;

    @ApiModelProperty(value = "项目信息 ")
    private SrpmsProjectVo srpmsProjectVo;

    @ApiModelProperty(value = "项目参加人员")
    private List<SrpmsProjectPersonJoinVo> mostMemberList;

    @ApiModelProperty(value = "经费支出预算明细 ")
    private List<SrpmsProjectBudgetDetailVo> expenditureBudgetDetail;

    @ApiModelProperty(value = "项目组织单位")
    private String projectOrganizingUnit;

    @ApiModelProperty(value = "项目承担单位")
    private String projectCommitmentUnit;

    @ApiModelProperty(value = "学科分类CODE")
    private JSONArray subjectCategory;

}
