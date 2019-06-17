package com.deloitte.platform.api.fssc.pe.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description : PerSelfAssessment新增修改form对象
 * @Modified :
 */
@ApiModel("新增PerSelfAssessment表单")
@Data
public class PerSelfAssessmentForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    private Long id;
    
    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "项目负责人")
    private String projectDuty;

    @ApiModelProperty(value = "项目负责人ID")
    private Long projectDutyId;

    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "主管部门ID")
    private Long doDeptId;

    @ApiModelProperty(value = "主管部门code")
    private String doDeptCode;

    @ApiModelProperty(value = "主管部门名称")
    private String doDeptName;

    @ApiModelProperty(value = "实施单位ID")
    private Long doUnitId;

    @ApiModelProperty(value = "实施单位CODE")
    private String doUnitCode;

    @ApiModelProperty(value = "实施单位名称")
    private String doUnitName;

    @ApiModelProperty(value = "年初资金总额")
    private BigDecimal fundSourceAmount;

    @ApiModelProperty(value = "年初财政拨款")
    private BigDecimal fundSourceProject;

    @ApiModelProperty(value = "年初其他经费")
    private BigDecimal fundSourceOther;

    @ApiModelProperty(value = "全年资金总额")
    private BigDecimal fundSourceAmountYear;

    @ApiModelProperty(value = "全年财政拨款")
    private BigDecimal fundSourceProjectYear;

    @ApiModelProperty(value = "全年其他经费")
    private BigDecimal fundSourceOtherYear;

    @ApiModelProperty(value = "全年执行总额")
    private BigDecimal executeTotal;

    @ApiModelProperty(value = "全年执行财政拨款")
    private BigDecimal executeProject;

    @ApiModelProperty(value = "全年执行其他资金")
    private BigDecimal executeOther;

    @ApiModelProperty(value = "总额分值")
    private BigDecimal totalScoreValue;

    @ApiModelProperty(value = "财政拨款分值")
    private BigDecimal projectScoreValue;

    @ApiModelProperty(value = "其他资金分值")
    private BigDecimal otherScoreValue;

    @ApiModelProperty(value = "总额执行率")
    private BigDecimal executePercentTotal;

    @ApiModelProperty(value = "财政拨款执行率")
    private BigDecimal executePercentProject;

    @ApiModelProperty(value = "其他资金执行率")
    private BigDecimal executePercentOther;

    @ApiModelProperty(value = "总额得分")
    private BigDecimal totalScored;

    @ApiModelProperty(value = "财政拨款得分")
    private BigDecimal prjectScored;

    @ApiModelProperty(value = "其他资金得分")
    private BigDecimal otherScored;

    @ApiModelProperty(value = "预期目标")
    private String projectTarget;

    @ApiModelProperty(value = "实际完成情况")
    private String realCompleteStatus;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "指标集合")
    private List<PerSelfTargetForm> perSelfTargetForms;

    @ApiModelProperty(value = "文件id集合")
    private List<Long> fileIds;

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    private String reSubmitType="FIRST_SUBMIT";

}
