package com.deloitte.platform.api.fileservice.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-19
 * @Description :   FileInfo查询from对象
 * @Modified :
 */
@ApiModel("FileInfo查询表单")
@Data
public class FileInfoQueryForm extends BaseQueryForm<FileInfoQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件类型")
    private String fileType;

    @ApiModelProperty(value = "mime类型")
    private String mimeType;

    @ApiModelProperty(value = "文件大小")
    private Long fileSize;

    @ApiModelProperty(value = "上传时间")
    private LocalDateTime uploadTime;

    @ApiModelProperty(value = "文件MD5值")
    private String fileMd5;

    @ApiModelProperty(value = "文件所有者")
    private String fileOwner;

    @ApiModelProperty(value = "文件虚拟路径（虚拟文件夹）")
    private String fileVirtualPath;

    @ApiModelProperty(value = "文件状态")
    private String fileState;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;

    @ApiModelProperty(value = "文件描述")
    private String fileDescription;

    @ApiModelProperty(value = "文件存储的 URL")
    private String fileUrl;

    @ApiModelProperty(value = "下载次数")
    private Integer downloadCount;

    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    private String ext5;

    @ApiModelProperty(value = "扩展名")
    private String fileExt;

    @ApiModelProperty(value = "是否加密 （0：否，1：是）")
    private Integer fileEncrypt;

    @ApiModelProperty(value = "加密秘钥")
    private String fileKey;
}
