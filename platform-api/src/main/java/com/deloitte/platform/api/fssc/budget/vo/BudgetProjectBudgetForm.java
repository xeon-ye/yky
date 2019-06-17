package com.deloitte.platform.api.fssc.budget.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description : 项目预算 新增修改form对象
 * @Modified :
 */
@ApiModel("新增项目预算 表单")
@Data
public class BudgetProjectBudgetForm extends BaseForm {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "单位ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long orgUnitId;

    @ApiModelProperty(value = "单位编码")
    private String orgUnitCode;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "预算项目ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long budgetProjectId;

    @ApiModelProperty(value = "项目")
    private String projectCode;

    @ApiModelProperty(value = "课题")
    private String subjectCode;

    @ApiModelProperty(value = "任务")
    private String taskCode;

    @ApiModelProperty(value = "状态")
    private String projectStatus;

    @ApiModelProperty(value = "预算科目")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预算科目ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long budgetAccountId;

    @ApiModelProperty(value = "预算期间")
    private String budgetPeriod;

    @ApiModelProperty(value = "预算年度")
    private String budgetAnnual;

    @ApiModelProperty(value = "预算金额")
    private BigDecimal budgetAmount;

    @ApiModelProperty(value = "预算保留金额")
    private BigDecimal budgetRemainAmount;

    @ApiModelProperty(value = "预算占用金额")
    private BigDecimal budgetOccupyAmount;

    @ApiModelProperty(value = "预算可用金额")
    private BigDecimal budgetUsableAmount;

    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    private String ext5;

}
