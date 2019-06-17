package com.deloitte.platform.api.hr.teacherAndPostdoctor.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : Jetvae
 * @Date : Create in 2019-04-01
 * @Description :   FlowStation查询from对象
 * @Modified :
 */
@ApiModel("FlowStation查询表单")
@Data
public class FlowStationQueryForm extends BaseQueryForm<FlowStationQueryParam>  {



    @ApiModelProperty(value = "流动站名称")
    private String stationName;

    @ApiModelProperty(value = "联动查询参数（1全部，2一级，3二级）")
    private Integer typeSel;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "状态（1有效，0无效）")
    private Integer status;


}
