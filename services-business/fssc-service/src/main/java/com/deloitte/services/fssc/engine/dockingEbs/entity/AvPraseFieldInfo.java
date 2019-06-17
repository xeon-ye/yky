package com.deloitte.services.fssc.engine.dockingEbs.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="AvParseFieldInfo", description="解析会计凭证逻辑字段里面取值逻辑")
public class AvPraseFieldInfo {
    @ApiModelProperty(value = "具体方法，取值方式：default,field,map")
    private  String  method;//{"method":"default","value":"0"}
    @ApiModelProperty(value = "具体方法")
    private String value;
}
