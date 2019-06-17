package com.deloitte.platform.api.srpmp.project.apply.vo.ext;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.api.srpmp.project.base.vo.*;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @Author : pengchao
 * @Date : Create in 2019-02-28
 * @Description : 创新工程-重大创新的传递数据对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class SrpmsProjectApplyInnBcooSaveVo extends BaseVo {

    private static final long serialVersionUID = 1L;	

    @ApiModelProperty(value = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "项目负责人每年工作时间")
    private Long leadPersonWorkTime;

    @ApiModelProperty(value = "共同首席专家每年工作时间")
    private Long bothTopWorkTime;

    @ApiModelProperty(value = "学科分类CODE")
    private JSONArray subjectCategory;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目执行期限开始")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "项目角色CODE")
    private String projectRole;

    @ApiModelProperty(value = "项目负责人ID")
    private Long leadPersonId;

    @ApiModelProperty(value = "共同首席专家ID")
    private Long bothTopExpertPersonId;

    @ApiModelProperty(value = "依托单位ID")
    private Long leadDeptId;

    @ApiModelProperty(value = "项目负责人信息")
    private JSONObject leadPerson;

    @ApiModelProperty(value = "共同首席专家信息")
    private JSONObject bothTopExpertPerson;

    @ApiModelProperty(value = "依托单位信息")
    private JSONObject leadDept;

    @ApiModelProperty(value = "待审核单位ID")
    private Long approveDeptId;

    @ApiModelProperty(value = "所属领域")
    private String belongDomain;

    @ApiModelProperty(value = "活动类型CODE")
    private JSONArray activeType;

    @ApiModelProperty(value = "预期成果类型CODE")
    private JSONArray achievementType;

    @ApiModelProperty(value = "拟申请岗位数")
    private Long applyPostNumber;

    @ApiModelProperty(value = "拟申请经费数")
    private Double applyFunds;

    @ApiModelProperty(value = "项目摘要")
    private String projectAbstract;

    @ApiModelProperty(value = "立项必要性")
    private String approvalNecessay;

    @ApiModelProperty(value = "项目成果的呈现形式及描述")
    private String achievementForm;

    @ApiModelProperty(value = "项目成果的预期经济、社会效益")
    private String achievementBenefit;

    @ApiModelProperty(value = "主要研究内容")
    private String researchContentMain;

    @ApiModelProperty(value = "拟采取的研究方法、技术路线及其可行性分析")
    private String researchContentMethod;

    @ApiModelProperty(value = "国际合作与交流方案")
    private String researchContentInterflow;

    @ApiModelProperty(value = "成果转化应用和科普方案")
    private String researchContentUsePlan;

    @ApiModelProperty(value = "项目预期的主要创新点")
    private String researchContentInnovate;

    @ApiModelProperty(value = "项目总体目标、考核指标及测评方式/方法")
    private String projectTarget;

    @ApiModelProperty(value = "目参与单位的选择原因及其优势")
    private String superiorityDeptChoose;

    @ApiModelProperty(value = "项目牵头和参加单位与课题实施相关的实力和基础")
    private String superiorityDeptBasic;

    @ApiModelProperty(value = "申报单位相关科研条件支撑状况")
    private String superiorityDeptSuport;

    @ApiModelProperty(value = "国际合作单位及团队情况")
    private String superiorityDeptAbroad;

    @ApiModelProperty(value = "项目首席专家及骨干成员的情况")
    private String mainstayMemberNote;

    @ApiModelProperty(value = "项目组织管理、协同机制和保障措施")
    private String manageGuarantee;

    @ApiModelProperty(value = "知识产权对策、成果管理及合作权益分配")
    private String manageKnowledgeRight;

    @ApiModelProperty(value = "风险分析及对策")
    private String manageRisk;

    @ApiModelProperty(value = "项目经费来源-总经费")
    private Double fundSourceAmount;

    @ApiModelProperty(value = "项目经费来源-创新工程经费")
    private Double fundSourceProject;

    @ApiModelProperty(value = "项目经费来源-单位自筹经费")
    private Double fundSourceSelf;

    @ApiModelProperty(value = "项目经费来源-其他经费")
    private Double fundSourceOther;

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

    @ApiModelProperty(value = "联合申请单位")
    private List<SrpmsProjectDeptJoinVo> jointApplicantUnit;

    @ApiModelProperty(value = "项目首席专家及研究骨干目前承担其它相关国家科技计划课题情况 ")
    private List<SrpmsProjectPersonJoinVo> topMemberOtherTaskList;

    @ApiModelProperty(value = "项目任务分解情况和各任务之间的逻辑关系图示")
    private List<SrpmsProjectTaskVo> taskDecompositionList ;

    @ApiModelProperty(value = "联合申报单位任务分工情况")
    private List<SrpmsProjectTaskVo> jointUnitTask;

}
