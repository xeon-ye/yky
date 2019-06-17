package com.deloitte.platform.api.dss.scientific.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: BudgetAmountImplementationRateVo
 * @Description: 预算经费和执行率
 * @Auther: wangyanyun
 * @Date: 2019-03-13
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class BudgetAmountImplementationRateVo {

    private Double budget;//预算
    private String dept;//单位
    private String projectName;//项目名称
    private String projectNum;//项目编号
    private Double expend;//支出
    private Long deptId;//单位ID
    private Double putMoney;//到位经费
    //
}
