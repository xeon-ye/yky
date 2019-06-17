package com.deloitte.platform.api.hr.retireRehiring.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :   RetireRehiringPerson查询from对象
 * @Modified :
 */
@ApiModel("查询表单")
@Data
public class RehiringQueryForm {

    @ApiModelProperty(value = "年")
    private String year;

    @ApiModelProperty(value = "部门编码")
    private String organizationCode;
}
