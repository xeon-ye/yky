package com.deloitte.platform.api.srpmp.project.apply.vo.ext;

import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDetailVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectDeptJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectTaskVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectApplyInnPre返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class SrpmsProjectApplyInnPreExportVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "学科分类CODE")
    private String subjectCategory;

    @NotBlank(message = "项目名称不能为空。")
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目执行期限开始")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "项目负责人ID")
    private Long projectLeaderId;

    @ApiModelProperty(value = "共同首席专家ID")
    private Long bothTopExpertPersonId;

    @ApiModelProperty(value = "项目角色CODE")
    private String projectRole;

    @ApiModelProperty(value = "依托单位ID")
    private Long dependDeptId;

    @ApiModelProperty(value = "待审核单位ID")
    private Long approveDeptId;

    @ApiModelProperty(value = "预期成果类型CODE")
    private List<String> achievementType;

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

    @ApiModelProperty(value = "项目总体目标、考核指标及测评方式/方法")
    private String projectTarget;

    @ApiModelProperty(value = "基础条件和优势")
    private String superiorityDeptBasic;

    @ApiModelProperty(value = "合作国家地区信息")
    private List<SrpmsProjectDeptJoinVo> coopDeptList;

    @ApiModelProperty(value = "主要参与人员")
    private List<SrpmsProjectPersonJoinVo> mainMemberList;

    @ApiModelProperty(value = "年度任务集合")
    private List<SrpmsProjectTaskVo> taskPreYearList;

    @ApiModelProperty(value = "年度预算集合")
    private List<SrpmsProjectBudgetDetailVo> budgetPreYearList;

    @ApiModelProperty(value = "附件集合")
    private List<SrpmsProjectAttachmentVo> attachmentList;

    @ApiModelProperty(value = "牵头单位学术委员会推荐意见")
    private SrpmsProjectAttachmentVo attachmentCommittee;

    @ApiModelProperty(value = "牵头单位审核意见及承诺")
    private SrpmsProjectAttachmentVo attachmentAudit;

    @ApiModelProperty(value = "创新工程-重大协同创新申请书声明")
    private SrpmsProjectAttachmentVo attachmentStatement;

    @ApiModelProperty(value = "项目负责人信息")
    private String leadPerson;

    @ApiModelProperty(value = "共同首席专家信息")
    private String bothTopExpertPerson;

    @ApiModelProperty(value = "依托单位信息")
    private String leadDept;
}
