package com.deloitte.platform.api.srpmp.project.task.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.common.config.LongJsonDeserializer;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-03-14
 * @Description : SrpmsProjectTaskSchStudent新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectTaskSchStudent表单")
@Data
public class SrpmsProjectTaskSchStudentForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID", required = true)
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目组织单位")
    private String projectOrganizingUnit;

    @ApiModelProperty(value = "项目承担单位")
    private String projectCommitmentUnit;

    @ApiModelProperty(value = "项目类别")
    private String baseScienceStuType;

    @ApiModelProperty(value = "项目执行开始时间")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行结束时间 ")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "项目摘要")
    private String projectAbstract;

    @ApiModelProperty(value = "提交成果考核指标")
    private String resultAssessmentIndicators;

    @ApiModelProperty(value = "年度计划")
    private JSONArray researchYearPlan;

    @ApiModelProperty(value = "经费预算")
    private Double applyFunds;

    @ApiModelProperty(value = "预算明细JSON")
    private List<SrpmsProjectBudgetDetailVo> expenditureBudgetDetail;

    @ApiModelProperty(value = "项目负责人信息")
    private JSONObject leadPerson;

    @ApiModelProperty(value = "依托单位信息")
    private JSONObject leadDept;

    @ApiModelProperty(value = "项目信息 ")
    private SrpmsProjectVo srpmsProjectVo;

    @ApiModelProperty(value = "项目参加人员")
    private List<SrpmsProjectPersonJoinVo> mostMemberList;

    @ApiModelProperty(value = "任务书附件")
    private JSONObject attachmentCommittee;

    @ApiModelProperty(value = "任务书签订各方意见及签章")
    private JSONObject attachmentAudit;

    @ApiModelProperty(value = "学科分类CODE")
    private JSONArray subjectCategory;

}
