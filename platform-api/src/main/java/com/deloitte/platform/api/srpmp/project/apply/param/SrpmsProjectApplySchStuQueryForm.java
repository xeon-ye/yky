package com.deloitte.platform.api.srpmp.project.apply.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : caoyue
 * @Date : Create in 2019-02-21
 * @Description :   SrpmsProjectApplySchStu查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectApplySchStu查询表单")
@Data
public class SrpmsProjectApplySchStuQueryForm extends BaseQueryForm<SrpmsProjectApplySchStuQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "项目类型（校基科费学生的项目类别）")
    private String baseScienceStuType;

    @ApiModelProperty(value = "导师姓名")
    private String advisorName;

    @ApiModelProperty(value = "导师职称")
    private String advisorPositionTitle;

    @ApiModelProperty(value = "导师研究方向")
    private String advisorResearchDirection;

    @ApiModelProperty(value = "导师联系电话")
    private String advisorPhone;

    @ApiModelProperty(value = "导师所在院系")
    private String advisorDept;

    @ApiModelProperty(value = "拟申请经费数")
    private Double applyFunds;

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

    @ApiModelProperty(value = "预算明细JSON")
    private String projectBudgetDetail;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "一级学科")
    private String firstLevelDiscipline;

    @ApiModelProperty(value = "二级学科")
    private String secondLevelDiscipline;
}
