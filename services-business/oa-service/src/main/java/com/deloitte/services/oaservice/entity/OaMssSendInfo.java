package com.deloitte.services.oaservice.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_MSS_SEND_INFO")
@ApiModel(value = "OaMssSendInfo对象", description = "")
public class OaMssSendInfo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "短信ID")
    @TableField("MSS_ID")
    private String mssId;

    @ApiModelProperty(value = "短信主题")
    @TableField("MSS_TITLE")
    private String mssTitle;

    @ApiModelProperty(value = "短信类型")
    @TableField("MSS_TYPE")
    private String mssType;

    @ApiModelProperty(value = "发送人")
    @TableField("SEND_USER_ID")
    private String sendUserId;

    @ApiModelProperty(value = "发送人姓名")
    @TableField("SEND_USER_NAME")
    private String sendUserName;

    @ApiModelProperty(value = "请求id")
    @TableField("REQUEST_ID")
    private String requestId;

    @ApiModelProperty(value = "收信人")
    @TableField("RECEIVE_ID")
    private String receiveId;

    @ApiModelProperty(value = "收信人姓名")
    @TableField("RECEIVE_NAME")
    private String  receiveName;

    @ApiModelProperty(value = "短信接收号码")
    @TableField("RECEIVE_TELEPHONE")
    private String receiveTelephone;

    @ApiModelProperty(value = "是否发送")
    @TableField("IS_SEND")
    private  String isSend;

    @ApiModelProperty(value = "来源系统")
    @TableField("RESOURCE_SYSTEM")
    private String resourceSystem;

    @ApiModelProperty(value = "预计拆分的短信条数")
    @TableField("MSS_COUNT")
    private Long mssCount;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT5")
    private String ext5;

    public static final String ID = "ID";

    public static final String MSS_ID = "MSS_ID";

    public static final String MSS_TITLE = "MSS_TITLE";

    public static final String SEND_USER_ID = "SEND_USER_ID";

    public static final String SEND_USER_NAME = "SEND_USER_NAME";

    public static final String REQUEST_ID = "REQUEST_ID";

    public static final String MSS_TYPE = "MSS_TYPE";

    public static final String RECEIVE_ID = "RECEIVE_ID";

    public static final String RECEIVE_NAME = "RECEIVE_NAME";

    public static final String RECEIVE_TELEPHONE = "RECEIVE_TELEPHONE";

    public static final  String RESOURCE_SYSTEM = "RESOURCE_SYSTEM";

    public static final  String MSS_COUNT = "MSS_COUNT";

    public static final String IS_SEND = "IS_SEND";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

}
