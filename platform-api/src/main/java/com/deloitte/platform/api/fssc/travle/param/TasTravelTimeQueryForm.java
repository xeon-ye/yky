package com.deloitte.platform.api.fssc.travle.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-02
 * @Description :   TasTravelTime查询from对象
 * @Modified :
 */
@ApiModel("TasTravelTime查询表单")
@Data
public class TasTravelTimeQueryForm extends BaseQueryForm<TasTravelTimeQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "关联行表ID")
    private Long travelLineId;

    @ApiModelProperty(value = "差旅起始时间")
    private LocalDateTime travelBeginTime;

    @ApiModelProperty(value = "差旅结束时间")
    private LocalDateTime travelEdnTime;

    @ApiModelProperty(value="关联类型")
    private  String documentType;
}
