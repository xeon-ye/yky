package com.deloitte.platform.api.srpmp.project.mpr.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :   MprTaskEvaInfo查询from对象
 * @Modified :
 */
@ApiModel("MprTaskEvaInfo查询表单")
@Data
public class MprTaskEvaInfoQueryForm extends BaseQueryForm<MprTaskEvaInfoQueryParam>  {


    @ApiModelProperty(value = "主键ID")
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

    @ApiModelProperty(value = "任务进展情况")
    private String taskProgress;

    @ApiModelProperty(value = "任务实施过程中进行的重要调整情况")
    private String adjustState;

    @ApiModelProperty(value = "取得的重要进展及标志性成果")
    private String landmarkAchieve;

    @ApiModelProperty(value = "经济社会效益")
    private String econSocialBenefits;

    @ApiModelProperty(value = "能力提升情况")
    private String capaImprove;

    @ApiModelProperty(value = "发展前景分析")
    private String deveProsAna;

    @ApiModelProperty(value = "人员及资金投入使用情况")
    private String personFundUse;

    @ApiModelProperty(value = "资金调整情况")
    private String fundAdjust;

    @ApiModelProperty(value = "实施中出现的重大问题和对策建议")
    private String problemSuggest;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}
