package com.deloitte.platform.api.push.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "OaMssSendInfo对象", description = "")
public class SendMssInfoForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "短信ID")
    private String mssId;

    @ApiModelProperty(value = "短信主题")
    private String mssTitle;

    @ApiModelProperty(value = "短信主题")
    private String mssContent;

    @ApiModelProperty(value = "短信类型")
    private String mssType;

    @ApiModelProperty(value = "发送人")
    private String sendUserId;

    @ApiModelProperty(value = "发送人姓名")
    private String sendUserName;

    @ApiModelProperty(value = "请求id")
    private String requestId;

    @ApiModelProperty(value = "收信人")
    private String receiveId;

    @ApiModelProperty(value = "收信人姓名")
    private String  receiveName;

    @ApiModelProperty(value = "短信接收号码")
    private String receiveTelephone;

    @ApiModelProperty(value = "是否发送")
    private  String isSend;

    @ApiModelProperty(value = "来源系统")
    private String resourceSystem;

    @ApiModelProperty(value = "预计拆分的短信条数")
    private Long mssCount;

    @ApiModelProperty(value = "创建人")
    private String createBy;

//    @ApiModelProperty(value = "创建时间")
//    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后更新人")
    private String updateBy;

//    @ApiModelProperty(value = "最后更新时间")
//    private LocalDateTime updateTime;

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
