package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   FlowStation查询from对象
 * @Modified :
 */
@ApiModel("FlowStation导出表单")
@Data
public class FlowStationExportForm {


    @ApiModelProperty(value = "流动站名称")
    private String stationName;

    @ApiModelProperty(value = "状态（1有效，0无效）")
    private Integer status;


}
