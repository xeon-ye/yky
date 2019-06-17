package com.deloitte.platform.api.srpmp.project.apply.vo.ext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.api.srpmp.project.base.vo.*;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;	
import java.util.List;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-16
 * @Description : 创新工程-协同创新保存数据对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class SrpmsProjectApplyInnCooSaveVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目负责人每年工作时间")
    private Long leadPersonWorkTime;

    @ApiModelProperty(value = "共同首席专家每年工作时间")
    private Long bothTopWorkTime;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "研究方向CODE")
    private String direction;

    @ApiModelProperty(value = "学科分类CODE")
    private JSONArray subjectCategory;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目执行期限开始")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "项目负责人ID")
    private Long leadPersonId;

    @ApiModelProperty(value = "共同首席专家ID")
    private Long bothTopExpertPersonId;

    @ApiModelProperty(value = "申请经费")
    private String applyFunds;
    
    @ApiModelProperty(value = "依托单位ID")
    private Long leadDeptId;

    @ApiModelProperty(value = "项目角色CODE")
    private String projectRole;

    @ApiModelProperty(value = "待审核单位ID")
    private Long approveDeptId;

    @ApiModelProperty(value = "团队组成情况-研究方向")
    private String teamDirection;

    @ApiModelProperty(value = "团队组成情况-英文名称")
    private String teamEnName;

    @ApiModelProperty(value = "团队的组成情况")
    private String teamConstitute;

    @ApiModelProperty(value = "首席专家简介")
    private String topExpertPersonIntro;

    @ApiModelProperty(value = "团队主要成员简介")
    private String teamMemberIntro;

    @ApiModelProperty(value = "近五年取得的主要学术成绩、创新点及其科学意义")
    private String performanceLately;

    @ApiModelProperty(value = "项目总体目标、考核指标及测评方式/方法")
    private String projectTarget;

    @ApiModelProperty(value = "述拟开展的研究工作方案及其可行性")
    private String researchPlan;

    @ApiModelProperty(value = "团队国际合作与交流计划等")
    private String researchContentInterflow;

    @ApiModelProperty(value = "项目预期的主要创新点")
    private String researchContentInnovate;

    @ApiModelProperty(value = "预期取得的重大成果及其意义")
    private String achievementForm;

    @ApiModelProperty(value = "项目成果的预期经济、社会效益")
    private String achievementBenefit;

    @ApiModelProperty(value = "项目组织管理、协同机制和保障措施")
    private String manageGuarantee;

    @ApiModelProperty(value = "知识产权对策、成果管理及合作权益分配")
    private String manageKnowledgeRight;

    @ApiModelProperty(value = "风险分析及对策")
    private String manageRisk;

    @ApiModelProperty(value = "保障条件")
    private String guaranteePlan;

    @ApiModelProperty(value = "任务分解逻辑关系图解")
    private String taskDiagram;

    @ApiModelProperty(value = "主要参与人员")
    private List<SrpmsProjectPersonJoinVo> mainMemberList;

    @ApiModelProperty(value = "国际合作单位")
    private List<SrpmsProjectDeptJoinVo> coopDeptList;

    @ApiModelProperty(value = "项目的年度任务、考核指标和时间节点")
    private List<SrpmsProjectTaskVo> taskPreYearList;

    @ApiModelProperty(value = "项目概算及筹资方案")
    private List<SrpmsProjectBudgetDetailVo> budgetPreYearList;

    @ApiModelProperty(value = "附件")
    private List<SrpmsProjectAttachmentVo> attachmentFile;

    @ApiModelProperty(value = "牵头单位学术委员会推荐意见")
    private SrpmsProjectAttachmentVo attachmentCommittee;

    @ApiModelProperty(value = "牵头单位审核意见及承诺")
    private SrpmsProjectAttachmentVo attachmentAudit;

    @ApiModelProperty(value = "创新工程-重大协同创新申请书声明")
    private SrpmsProjectAttachmentVo attachmentStatement;

    @ApiModelProperty(value = "项目首席专家及研究骨干目前承担其它相关国家科技计划课题情况 ")
    private List<SrpmsProjectPersonJoinVo> topMemberOtherTaskList;

    @ApiModelProperty(value = "项目任务分解情况和各任务之间的逻辑关系图示")
    private List<SrpmsProjectTaskVo> taskDecompositionList ;

    @ApiModelProperty(value = "联合申报单位任务分工情况")
    private List<SrpmsProjectTaskVo> jointUnitTask;

    @ApiModelProperty(value = "任务分解")
    private List<SrpmsProjectTaskVo> subTaskList;

    @ApiModelProperty(value = "项目负责人信息")
    private JSONObject leadPerson;

    @ApiModelProperty(value = "共同首席专家信息")
    private JSONObject bothTopExpertPerson;

    @ApiModelProperty(value = "依托单位信息")
    private JSONObject leadDept;

    @ApiModelProperty(value = "所属领域CODE")
    private String belongDomain;
}
