package com.deloitte.platform.api.fssc.pe.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-05-10
 * @Description :   PerSelfTarget查询from对象
 * @Modified :
 */
@ApiModel("PerSelfTarget查询表单")
@Data
public class PerSelfTargetQueryForm extends BaseQueryForm<PerSelfTargetQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

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
}
