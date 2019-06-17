package com.deloitte.platform.api.fssc.budget.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-06
 * @Description :   BudgetDetailingAdjustLine查询from对象
 * @Modified :
 */
@ApiModel("BudgetDetailingAdjustLine查询表单")
@Data
public class BudgetDetailingAdjustLineQueryForm extends BaseQueryForm<BudgetDetailingAdjustLineQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

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

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}
