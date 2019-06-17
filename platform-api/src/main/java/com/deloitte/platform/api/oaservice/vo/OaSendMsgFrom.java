package com.deloitte.platform.api.oaservice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@ApiModel("发送短信表单")
@Data
public class OaSendMsgFrom {
    @ApiModelProperty(value = "短信请求id")
    @NotEmpty
    private String requestId;

    @ApiModelProperty(value = "短信发送人id")
    private String sendUserId;

    @ApiModelProperty(value = "短信发送人姓名")
    private String sendUserName;

    @ApiModelProperty(value = "短信内容")
    @NotEmpty
    private String content;

    @ApiModelProperty(value = "短信主题")
    @NotEmpty
    private String title;

    @ApiModelProperty(value = "短信类型")
    private String mssType;

    @ApiModelProperty(value = "短信接收对象")
    private List<OaSendMsgReceiveFrom> receiveArr;
}
