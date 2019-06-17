package com.deloitte.platform.api.srpmp.project.apply.vo;
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
 * @Author : lixin
 * @Date : Create in 2019-02-25
 * @Description : SrpmsProjectApplyAcademy返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsProjectApplyAcademyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "拟申请经费数")
    private Double applyFunds;

    @ApiModelProperty(value = "项目执行期限开始")
    private LocalDateTime projectActionDateStart;

    @ApiModelProperty(value = "项目执行期限结束 ")
    private LocalDateTime projectActionDateEnd;

    @ApiModelProperty(value = "申请人ID ")
    private Long projectApplicantId;

    @ApiModelProperty(value = "项目摘要 ")
    private String projectAbstract;

    @ApiModelProperty(value = "立项依据，研究意义")
    private String approvalBasisMeaning;

    @ApiModelProperty(value = "研究内容、研究目标以及拟解决的关键科学问题")
    private String contentTargetProblem;

    @ApiModelProperty(value = "拟采取的研究方案及可行性分析 ")
    private String researchSchemeFeasibility;

    @ApiModelProperty(value = "本项目的预期目标 ")
    private String projectExpectTarget;

    @ApiModelProperty(value = "主要技术特点和创新点  ")
    private String projectTechnicalInnovation;

    @ApiModelProperty(value = "研究基础 ")
    private String researchFoundation;

    @ApiModelProperty(value = "工作条件")
    private String workinfConditions;

    @ApiModelProperty(value = "申请人简介 ")
    private String projectApplicantIntroduction;

    @ApiModelProperty(value = "项目组织单位")
    private String projectOrganizingUnit;

    @ApiModelProperty(value = "项目承担单位")
    private String projectCommitmentUnit;

    @ApiModelProperty(value = "项目经费来源-总经费")
    private Double fundSourceAmount;

    @ApiModelProperty(value = "项目经费来源-国家财政拨款")
    private Double fundSourceFinancial;

    @ApiModelProperty(value = "项目经费来源-单位自筹经费")
    private Double fundSourceSelfRaised;

    @ApiModelProperty(value = "项目经费来源-其他经费 ")
    private Double fundSourceOther;

    @ApiModelProperty(value = "经费支出预算明细 ")
    private String expenditureBudgetDetail;

    @ApiModelProperty(value = "创建时间 ")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人 ")
    private String createBy;

    @ApiModelProperty(value = "更新时间 ")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人 ")
    private String updateBy;

    @ApiModelProperty(value = "预算说明")
    private String budgetDescription;

}
