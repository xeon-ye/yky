package com.deloitte.services.oaservice.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_MSS_INFO")
@ApiModel(value="OaMssInfo对象", description="")
public class OaMssInfo extends Model<OaMssInfo> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "短信主题")
    @TableField("MSS_TITLE")
    private String mssTitle;

    @ApiModelProperty(value = "短信内容")
    @TableField("MSS_CONTENT")
    private String mssContent;

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
}
