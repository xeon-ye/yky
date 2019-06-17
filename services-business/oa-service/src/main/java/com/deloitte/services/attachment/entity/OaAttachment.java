package com.deloitte.services.attachment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 附件表
 * </p>
 *
 * @author jianghaixun
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_ATTACHMENT")
@ApiModel(value="OaAttachment对象", description="附件表")
public class OaAttachment extends Model<OaAttachment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "附件表id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "业务ID，例如新闻id, 公告id")
    @TableField("BUSICESS_ID")
    private String busicessId;

    @ApiModelProperty(value = "业务类型名称，例如新闻、公告")
    @TableField("BUSICESS_NAME")
    private String busicessName;

    @ApiModelProperty(value = "附件名")
    @TableField("ATTACH_NAME")
    private String attachName;

    @ApiModelProperty(value = "附件URL")
    @TableField("ATTACH_URL")
    private String attachUrl;

    @ApiModelProperty(value = "是否删除 默认0，表示未删除")
    @TableField("DEL_FLAG")
    private String delFlag;

    @ApiModelProperty(value = "附件路径")
    @TableField("ATTACH_PATH")
    private String attachPath;

    @ApiModelProperty(value = "附件日期")
    @TableField("APPLY_DATETIME")
    private LocalDateTime applyDatetime;

    @ApiModelProperty(value = "文件服务器存储ID")
    @TableField("FILE_ID")
    private String fileId;


    public static final String ID = "ID";

    public static final String BUSICESS_ID = "BUSICESS_ID";

    public static final String BUSICESS_NAME = "BUSICESS_NAME";

    public static final String ATTACH_NAME = "ATTACH_NAME";

    public static final String ATTACH_URL = "ATTACH_URL";

    public static final String DEL_FLAG = "DEL_FLAG";

    public static final String ATTACH_PATH = "ATTACH_PATH";

    public static final String APPLY_DATETIME = "APPLY_DATETIME";

    public static final String FILE_ID = "FILE_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
