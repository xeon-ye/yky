package com.deloitte.platform.api.srpmp.project.mpr.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-02
 * @Description : MprProjEvaInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprProjEvaInfo表单")
@Data
public class MprProjEvaInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目ID")
    @NotNull
    private Long id;

    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "项目类别")
    private String projectType;

    @ApiModelProperty(value = "项目分类")
    private String projectCategory;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目周期")
    private String projectCycle;

    @ApiModelProperty(value = "项目首席专家")
    private String projectChiefExpert;

    @ApiModelProperty(value = "项目承担单位")
    private String projectUndertaker;

    @ApiModelProperty(value = "报告时间")
    private String reportDate;

    @ApiModelProperty(value = "项目总体进展情况")
    private String taskProgress;

    @ApiModelProperty(value = "项目实施过程中进行的重要调整情况")
    private String adjustState;

    @ApiModelProperty(value = "取得的重要进展及标志性成果")
    private String landmarkAchieve;

    @ApiModelProperty(value = "经济社会效益")
    private String econSocialBenefits;

    @ApiModelProperty(value = "能力提升情况")
    private String capaImprove;

    @ApiModelProperty(value = "团队建设情况")
    private String teamBuild;

    @ApiModelProperty(value = "发展前景分析")
    private String deveProsAna;

    @ApiModelProperty(value = "人员及资金投入使用情况")
    private String personFundUse;

    @ApiModelProperty(value = "资金调整情况")
    private String fundAdjust;

    @ApiModelProperty(value = "组织实施管理情况及重大问题和对策建议")
    private String problemSuggest;

    @ApiModelProperty(value = "根据项目自评专家组意见阐述有关项目内容和经费的调整建议")
    private String projCont;

    @ApiModelProperty(value = "项目任务书中有特殊约定或其他需要说明的事项")
    private String instructions;

}
