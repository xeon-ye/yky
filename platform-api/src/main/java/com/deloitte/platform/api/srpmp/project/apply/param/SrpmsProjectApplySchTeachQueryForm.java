package com.deloitte.platform.api.srpmp.project.apply.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-20
 * @Description :   SrpmsProjectApplySchTeach查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectApplySchTeach查询表单")
@Data
public class SrpmsProjectApplySchTeachQueryForm extends BaseQueryForm<SrpmsProjectApplySchTeachQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目类别")
    private String baseScienceTchType;

    @ApiModelProperty(value = "一级学科")
    private String firstLevelDiscipline;

    @ApiModelProperty(value = "二级学科")
    private String twoLevelDiscipline;

    @ApiModelProperty(value = "拟申请经费数")
    private Double applyFunds;

    @ApiModelProperty(value = "项目执行期限开始")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "项目负责人ID")
    private Long projectLeaderId;

    @ApiModelProperty(value = "关键字")
    private String projectKeyword;

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
    private String projectBudgetDetail;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
