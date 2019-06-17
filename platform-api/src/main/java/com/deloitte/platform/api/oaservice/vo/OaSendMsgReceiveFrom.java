package com.deloitte.platform.api.oaservice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("短信接收人信息表单")
@Data
public class OaSendMsgReceiveFrom {
    @ApiModelProperty(value = "短信接收")
    private String receiveId;

    @ApiModelProperty(value = "短信接收人姓名")
    private String receiveName;

    @ApiModelProperty(value = "短信接收对象")
    private String receiveType;

    @ApiModelProperty(value = "短信接收电话")
    private String receiveTelephone;

}
