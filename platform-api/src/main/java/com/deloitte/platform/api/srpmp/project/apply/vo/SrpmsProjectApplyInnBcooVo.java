package com.deloitte.platform.api.srpmp.project.apply.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-04
 * @Description : SrpmsProjectApplyInnBcoo返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsProjectApplyInnBcooVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "所属领域CODE")
    private String belongDomain;

    @ApiModelProperty(value = "活动类型CODE")
    private String activeType;

    @ApiModelProperty(value = "预期成果类型CODE")
    private String achievementType;

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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}
