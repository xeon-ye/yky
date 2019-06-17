package com.deloitte.platform.api.fssc.budget.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "项目预算汇总")
public class ProjectBudgetSummaryVo {

    @ApiModelProperty(value = "汇总的预算数据")
    private BudgetProjectBudgetVo taskVo;

    @ApiModelProperty(value = "具体科目的预算数据")
    private List<BudgetProjectBudgetVo> accountVoList;

}
