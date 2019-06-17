package com.deloitte.platform.api.fssc.pe.vo;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description : PerSelfAssessment返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerSelfAssessmentVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目负责人")
    private String projectDuty;

    @ApiModelProperty(value = "项目负责人ID")
    private Long projectDutyId;

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

    @ApiModelProperty(value = "项目立项时间")
    private LocalDateTime projectStartTime;

    @ApiModelProperty(value = "预留字段1")
    private String ex1;

    @ApiModelProperty(value = "预留字段2")
    private String ex2;

    @ApiModelProperty(value = "预留字段3")
    private String ex3;

    @ApiModelProperty(value = "预留字段4")
    private String ex4;

    @ApiModelProperty(value = "预留字段5")
    private String ex5;

    @ApiModelProperty(value = "指标集合")
    private List<PerSelfTargetVo> targetVos;

    @ApiModelProperty(value = "审批历史")
    private List<BpmProcessTaskVo> bpmProcessTaskVos;
}
