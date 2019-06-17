package com.deloitte.platform.api.fssc.budget.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-06
 * @Description : BudgetDetailingAdjustLine新增修改form对象
 * @Modified :
 */
@ApiModel("新增BudgetDetailingAdjustLine表单")
@Data
public class BudgetDetailingAdjustLineForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "预算科目ID")
    private Long budgetAccountId;

    @ApiModelProperty(value = "预算科目编码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "分配金额")
    private BigDecimal allocationAmount;

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

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

    @ApiModelProperty(value = "版本")
    private Long version;

}
