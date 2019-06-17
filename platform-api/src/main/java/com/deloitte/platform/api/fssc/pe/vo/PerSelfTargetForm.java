package com.deloitte.platform.api.fssc.pe.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description : PerSelfTarget新增修改form对象
 * @Modified :
 */
@ApiModel("新增PerSelfTarget表单")
@Data
public class PerSelfTargetForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "一级指标")
    private String firstPer;

    @ApiModelProperty(value = "二级指标")
    private String secondPer;

    @ApiModelProperty(value = "三级指标")
    private String thiredPer;

    @ApiModelProperty(value = "三级指标单位ID")
    private Long thiredUnitId;

    @ApiModelProperty(value = "三级指标单位code")
    private String thiredUnitCode;

    @ApiModelProperty(value = "三级指标单位名称")
    private String thiredUnitName;

    @ApiModelProperty(value = "年度指标值")
    private String yearPerValue;

    @ApiModelProperty(value = "实际完成值")
    private String realCompleteValue;

    @ApiModelProperty(value = "分值")
    private BigDecimal scoreValue;

    @ApiModelProperty(value = "得分")
    private BigDecimal scored;

    @ApiModelProperty(value = "未完成原因")
    private String reason;

    @ApiModelProperty(value = "单据id")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "版本")
    private Long version;



}
