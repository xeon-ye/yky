package com.deloitte.platform.api.srpmp.project.apply.vo.ext;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.api.srpmp.project.base.vo.*;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-18
 * @Description : SrpmsProjectApplyInnBcoo新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectApplyInnBcoo表单")
@Data
public class SrpmsProjectApplyInnSaveVo extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    private long id;

    @ApiModelProperty(value = "申请编号")
    private String applyNum;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目执行期限开始")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "活动类型CODE")
    private String activeType;

    @ApiModelProperty(value = "预期成果类型CODE")
    private String achievementType;

    @ApiModelProperty(value = "拟申请岗位数")
    private Long applyPostNumber;

    @ApiModelProperty(value = "拟申请经费数")
    private Double applyFunds;

    @ApiModelProperty(value = "牵头单位ID")
    private long leadDeptId;

    @ApiModelProperty(value = "牵头单位Form")
    private SrpmsProjectDeptForm leadDeptForm;

    @ApiModelProperty(value = "首席专家ID")
    private long topExpertPersonId;

    @ApiModelProperty(value = "首席专家Form")
    private SrpmsProjectPersonForm topExpertPersonForm;

    @ApiModelProperty(value = "共同首席专家ID")
    private long bothTopExpertPersonId;

    @ApiModelProperty(value = "共同首席专家Form")
    private SrpmsProjectPersonForm bothTopExpertPersonForm;

    @ApiModelProperty(value = "主要参与人员")
    private List<SrpmsProjectPersonJoinVo> mainMember;

    @ApiModelProperty(value = "国际合作单位")
    private List<SrpmsProjectDeptJoinVo> InternationalCooperationUnit;

    @ApiModelProperty(value = "联合申请单位")
    private List<SrpmsProjectDeptJoinVo> JointApplicantUnit;

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

    @ApiModelProperty(value = "承担其它相关国家科技计划")
    private List<SrpmsProjectPersonJoinVo> otherProject;

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

    @ApiModelProperty(value = "附件")
    private List<SrpmsProjectAttachmentForm> attachmentFile;
}
