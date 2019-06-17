package com.deloitte.platform.api.fssc.budget.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-12
 * @Description : BudgetAdvanceBorrowLine新增修改form对象
 * @Modified :
 */
@ApiModel("新增BudgetAdvanceBorrowLine表单")
@Data
public class BudgetAdvanceBorrowLineForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "借款金额")
    private BigDecimal borrowAmount;

    @ApiModelProperty(value = "支出小类ID")
    private Long subTypeId;

    @ApiModelProperty(value = "预算保留金额")
    private BigDecimal budgetRemainAmount;

    @ApiModelProperty(value = "可用保留金额")
    private BigDecimal usableRemainAmount;

    @ApiModelProperty(value = "已用保留金额")
    private BigDecimal usedRemainAmount;

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

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

}
