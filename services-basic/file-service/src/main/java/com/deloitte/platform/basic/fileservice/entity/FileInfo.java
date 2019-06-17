package com.deloitte.platform.basic.fileservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jackliu
 * @since 2019-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("FILE_INFO")
@ApiModel(value="FileInfo对象", description="")
public class FileInfo extends Model<FileInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "文件名称")
    @TableField("FILE_NAME")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    @TableField("FILE_PATH")
    private String filePath;

    @ApiModelProperty(value = "文件类型")
    @TableField("FILE_TYPE")
    private String fileType;

    @ApiModelProperty(value = "mime类型")
    @TableField("MIME_TYPE")
    private String mimeType;

    @ApiModelProperty(value = "文件大小")
    @TableField("FILE_SIZE")
    private Long fileSize;

    @ApiModelProperty(value = "上传时间")
    @TableField("UPLOAD_TIME")
    private LocalDateTime uploadTime;

    @ApiModelProperty(value = "文件MD5值")
    @TableField("FILE_MD5")
    private String fileMd5;

    @ApiModelProperty(value = "文件所有者")
    @TableField("FILE_OWNER")
    private String fileOwner;

    @ApiModelProperty(value = "文件虚拟路径（虚拟文件夹）")
    @TableField("FILE_VIRTUAL_PATH")
    private String fileVirtualPath;

    @ApiModelProperty(value = "文件状态")
    @TableField("FILE_STATE")
    private String fileState;

    @ApiModelProperty(value = "修改时间")
    @TableField("MODIFY_TIME")
    private LocalDateTime modifyTime;

    @ApiModelProperty(value = "文件描述")
    @TableField("FILE_DESCRIPTION")
    private String fileDescription;

    @ApiModelProperty(value = "文件存储的 URL")
    @TableField("FILE_URL")
    private String fileUrl;

    @ApiModelProperty(value = "下载次数")
    @TableField("DOWNLOAD_COUNT")
    private Integer downloadCount;

    @ApiModelProperty(value = "扩展字段1")
    @TableField("EXT_1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    @TableField("EXT_2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    @TableField("EXT_3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    @TableField("EXT_4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    @TableField("EXT_5")
    private String ext5;

    @ApiModelProperty(value = "扩展名")
    @TableField("FILE_EXT")
    private String fileExt;

    @ApiModelProperty(value = "是否加密 （0：否，1：是）")
    @TableField("FILE_ENCRYPT")
    private Integer fileEncrypt;

    @ApiModelProperty(value = "加密秘钥")
    @TableField("FILE_KEY")
    private String fileKey;


    public static final String ID = "ID";

    public static final String FILE_NAME = "FILE_NAME";

    public static final String FILE_PATH = "FILE_PATH";

    public static final String FILE_TYPE = "FILE_TYPE";

    public static final String MIME_TYPE = "MIME_TYPE";

    public static final String FILE_SIZE = "FILE_SIZE";

    public static final String UPLOAD_TIME = "UPLOAD_TIME";

    public static final String FILE_MD5 = "FILE_MD5";

    public static final String FILE_OWNER = "FILE_OWNER";

    public static final String FILE_VIRTUAL_PATH = "FILE_VIRTUAL_PATH";

    public static final String FILE_STATE = "FILE_STATE";

    public static final String MODIFY_TIME = "MODIFY_TIME";

    public static final String FILE_DESCRIPTION = "FILE_DESCRIPTION";

    public static final String FILE_URL = "FILE_URL";

    public static final String DOWNLOAD_COUNT = "DOWNLOAD_COUNT";

    public static final String EXT_1 = "EXT_1";

    public static final String EXT_2 = "EXT_2";

    public static final String EXT_3 = "EXT_3";

    public static final String EXT_4 = "EXT_4";

    public static final String EXT_5 = "EXT_5";

    public static final String FILE_EXT = "FILE_EXT";

    public static final String FILE_ENCRYPT = "FILE_ENCRYPT";

    public static final String FILE_KEY = "FILE_KEY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
