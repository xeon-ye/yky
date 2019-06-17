package com.deloitte.platform.api.srpmp.project.apply.vo.ext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-20
 * @Description : SrpmsProjectApplySchTeach返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SrpmsProjectApplySchTeachSaveVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @NotNull
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;
    
    @ApiModelProperty(value = "申报年度")
    private String applyYear;
    
    @ApiModelProperty(value = "项目执行期限开始")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束")
    private LocalDateTime projectActionDateEnd;
    
    @ApiModelProperty(value = "负责人信息JSON")
    private JSONObject leadPerson;

    @ApiModelProperty(value = "共同负责人信息JSON")
    private JSONObject bothTopExpertPerson;

    @ApiModelProperty(value = "承担单位信息JSON")
    private JSONObject leadDept;

    @ApiModelProperty(value = "项目角色CODE")
    private String projectRole;

    @ApiModelProperty(value = "学科分类CODE")
    private JSONArray subjectCategory;
    
    @ApiModelProperty(value = "拟申请经费数")
    private Double applyFunds;

    @ApiModelProperty(value = "项目类别(校基科费教师的项目类别)")
    private String baseScienceTchType;

    @ApiModelProperty(value = "一级学科")
    private JSONArray firstLevelDiscipline;

    @ApiModelProperty(value = "二级学科")
    private String twoLevelDiscipline;

    @ApiModelProperty(value = "关键字")
    private JSONArray projectKeyword;

    @ApiModelProperty(value = "项目摘要")
    private String projectAbstract;

    @ApiModelProperty(value = "立项依据，研究意义")
    private String approvalBasisMeaning;

    @ApiModelProperty(value = "研究内容、研究目标以及拟解决的关键科学问题")
    private String contentTargetProblem;

    @ApiModelProperty(value = "拟采取的研究方案及可行性分析")
    private String researchSchemeFeasibility;

    @ApiModelProperty(value = "本项目的特色与创新之处")
    private String projectFeatureInnovate;

    @ApiModelProperty(value = "本项目的预期成果")
    private String projectExpectAchievement;

    @ApiModelProperty(value = "研究基础")
    private String researchFoundation;

    @ApiModelProperty(value = "工作条件")
    private String workinfConditions;

    @ApiModelProperty(value = "项目负责人简介")
    private String projectLeaderIntroduction;

    @ApiModelProperty(value = "预算明细")
    private JSONArray projectBudgetDetail;

    @ApiModelProperty(value = "年度预算明细")
    private List<SrpmsProjectBudgetDetailVo>   budgetPreYearList;

    @ApiModelProperty(value = "主要参与人员")
    private List<SrpmsProjectPersonJoinVo> mainMemberList;

    @ApiModelProperty(value = "本项目相关生物医学研究伦理问题意见")
    private SrpmsProjectAttachmentVo attachmentCommittee;

    @ApiModelProperty(value = "本项目相关生物安全问题意见")
    private SrpmsProjectAttachmentVo attachmentAudit;

    @ApiModelProperty(value = "项目负责人所在单位学术委员会推荐意见")
    private SrpmsProjectAttachmentVo attachmentStatement;

    @ApiModelProperty(value = "项目负责人所在单位审核意见及承诺")
    private SrpmsProjectAttachmentVo attachmentDept;

    @ApiModelProperty(value = "学校审批意见")
    private SrpmsProjectAttachmentVo attachmentSchool;






}
