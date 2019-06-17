package com.deloitte.platform.api.srpmp.project.apply.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : pengchao
 * @Date : Create in 2019-03-04
 * @Description : SrpmsProjectApplyInnCoo新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectApplyInnCoo表单")
@Data
public class SrpmsProjectApplyInnCooForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "研究方向CODE")
    private String direction;

    @ApiModelProperty(value = "${field.comment}")
    private String leadDeptName;

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

}
