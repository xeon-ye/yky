package com.deloitte.platform.api.srpmp.project.task.vo;
import com.deloitte.platform.api.srpmp.project.base.vo.*;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-11
 * @Description : SrpmsProjectTaskInn返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SrpmsProjectTaskInnVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "项目负责人每年工作时间")
    private Long leadPersonWorkTime;

    @ApiModelProperty(value = "共同首席专家每年工作时间")
    private Long bothTopWorkTime;

    @ApiModelProperty(value = "研究队伍人数")
    private Long researchMemberSize;

    @ApiModelProperty(value = "全时工作时间")
    private Double researchWorkTime;

    @ApiModelProperty(value = "任务负责人简介")
    private String leadPersonNote;

    @ApiModelProperty(value = "共同首席专家任务负责人简介")
    private String bothTopNote;

    @ApiModelProperty(value = "立项依据")
    private String approvalNecessay;

    @ApiModelProperty(value = "研究内容")
    private String researchContentMain;

    @ApiModelProperty(value = "研究目标")
    private String researchTarget;

    @ApiModelProperty(value = "任务总体考核指标及测评方式方法")
    private String taskMasterMethod;

    @ApiModelProperty(value = "项目成果的呈现形式及描述")
    private String achievementForm;

    @ApiModelProperty(value = "主要示范或产业化内容")
    private String mainContents;

    @ApiModelProperty(value = "国际合作和交流方案")
    private String exchangeProgramme;

    @ApiModelProperty(value = "项目成果的预期经济、社会效益")
    private String achievementBenefit;

    @ApiModelProperty(value = "拟采取的研究方法、技术路线及其可行性分析")
    private String researchContentMethod;

    @ApiModelProperty(value = "任务组织管理机制、产学研结合、创新人才队伍的凝聚和培养等")
    private String taskOrgManageMode;

    @ApiModelProperty(value = "知识产权对策、成果管理及合作权益分配")
    private String knowledgeResultManage;

    @ApiModelProperty(value = "风险分析及对策")
    private String riskAnalyzeManage;

    @ApiModelProperty(value = "任务书签订各方意见及签章")
    private SrpmsProjectAttachmentVo  OpinionsAndSignatures;

    @ApiModelProperty(value = "主要参与人员")
    private List<SrpmsProjectPersonJoinVo> mainMemberList;

    @ApiModelProperty(value = "国际合作单位")
    private List<SrpmsProjectDeptJoinVo> coopDeptList;

    @ApiModelProperty(value = "年度计划")
    private List<SrpmsProjectTaskVo> taskPreYearList;

    @ApiModelProperty(value = "项目概算及筹资方案")
    private List<SrpmsProjectBudgetDetailVo> budgetPreYearList;

    @ApiModelProperty(value = "附件")
    private List<SrpmsProjectAttachmentVo> attachmentFile;

    @ApiModelProperty(value = "联合申请单位")
    private List<SrpmsProjectDeptJoinVo> JointApplicantUnit;

    @ApiModelProperty(value = "任务分解")
    private List<SrpmsProjectTaskVo> taskDecompositionList ;

    @ApiModelProperty(value = "任务设置")
    private List<SrpmsProjectTaskVo> taskSettingList ;
}
