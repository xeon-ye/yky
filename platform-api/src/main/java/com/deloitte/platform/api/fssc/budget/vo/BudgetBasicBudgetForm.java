package com.deloitte.platform.api.fssc.budget.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.api.srpmp.common.config.LongJsonDeserializer;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description : BudgetBasicBudget新增修改form对象
 * @Modified :
 */
@ApiModel("新增BudgetBasicBudget表单")
@Data
public class BudgetBasicBudgetForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "单位ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long orgUnitId;

    @ApiModelProperty(value = "单位编码")
    private String orgUnitCode;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "一级处室")
    private String level1OfficeCode;

    @ApiModelProperty(value = "二级处室")
    @NotBlank
    private String level2OfficeCode;

    @ApiModelProperty(value = "预算科目ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long budgetAccountId;

    @ApiModelProperty(value = "预算科目")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预算期间")
    @NotBlank
    private String budgetPeriod;

    @ApiModelProperty(value = "预算金额")
    @NotNull
    private BigDecimal budgetAmount;

    @ApiModelProperty(value = "预算年度")
    @NotBlank
    private String budgetAnnual;

    @ApiModelProperty(value = "预算保留金额")
    private BigDecimal budgetRemainAmount;

    @ApiModelProperty(value = "预算占用金额")
    private BigDecimal budgetOccupyAmount;

    @ApiModelProperty(value = "预算可用金额")
    private BigDecimal budgetUsableAmount;

    @ApiModelProperty(value = "申请人")
    private String applyForPerson;

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
