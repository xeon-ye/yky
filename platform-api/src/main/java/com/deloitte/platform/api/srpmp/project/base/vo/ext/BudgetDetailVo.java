package com.deloitte.platform.api.srpmp.project.base.vo.ext;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:LIJUN
 * Date:22/04/2019
 * Description:
 */
@ApiModel("预算明细")
@Data
public class BudgetDetailVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "金额")
    private String amount;

    @ApiModelProperty(value = "预算科目编码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "序号")
    private Integer serial;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "备注")
    private String remark;
}
