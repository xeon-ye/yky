package com.deloitte.platform.api.srpmp.project.accept.vo;

import com.alibaba.fastjson.JSONArray;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description : SrpmsProjectAccept新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectAccept表单")
@Data
public class SrpmsProjectAcceptForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "验收状态")
    private String status;

    @ApiModelProperty(value = "项目考核指标")
    private String assessmentIndicators;

    @ApiModelProperty(value = "研究内容考核指标完成情况")
    private String projectCompletionCase;

    @ApiModelProperty(value = "分析未完成原因")
    private String projectUnfinishReason;

    @ApiModelProperty(value = "项目实施经验")
    private String projectImplementExperience;

    @ApiModelProperty(value = "项目摘要")
    private String projectAbstract;

    @ApiModelProperty(value = "研究内容及考核指标执行情况描述")
    private String projectContentIndicators;

    @ApiModelProperty(value = "研究工作取得的重要进展与成果")
    private String projectGainResult;

    @ApiModelProperty(value = "团队建设，人才培养")
    private String projectTeamConstruction;

    @ApiModelProperty(value = "存在问题及建议")
    private String projectQuestionAdvice;

    @ApiModelProperty(value = "资助金额")
    private Double applyFunds;

    @ApiModelProperty(value = "项目成果目录")
    private JSONArray projectResultDirectory;

    @ApiModelProperty(value = "附件")
    private List<SrpmsProjectAttachmentVo> attachmentFile;

}
