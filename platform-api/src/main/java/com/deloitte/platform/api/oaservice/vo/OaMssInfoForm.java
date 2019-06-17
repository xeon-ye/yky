package com.deloitte.platform.api.oaservice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApiModel("新增OaAssistantMapping表单")
@NoArgsConstructor
@Data
public class OaMssInfoForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "短信内容")
    private String mssContent;

    @ApiModelProperty(value = "短信主题")
    private String mssTitle;

    @ApiModelProperty(value = "短信类型")
    private String mssType;

    @ApiModelProperty(value = "发送人")
    private String sendUserId;

    @ApiModelProperty(value = "发送人姓名")
    private String sendUserName;

    @ApiModelProperty(value = "请求id")
    private String requestId;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "最后更新人")
    private String updateBy;

//    @ApiModelProperty(value = "最后更新时间")
//    private LocalDateTime updateTime;
//    @ApiModelProperty(value = "创建时间")
//    private LocalDateTime createTime;

    @ApiModelProperty(value = "备用字段")
    private String ext1;

    @ApiModelProperty(value = "备用字段")
    private String ext2;

    @ApiModelProperty(value = "备用字段")
    private String ext3;

    @ApiModelProperty(value = "备用字段")
    private String ext4;

    @ApiModelProperty(value = "备用字段")
    private String ext5;
}
