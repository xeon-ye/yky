package com.deloitte.platform.api.fileservice.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jackliu
 * @Date : Create in 2019-02-19
 * @Description :  FileInfo查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String fileName;
    private String filePath;
    private String fileType;
    private String mimeType;
    private Long fileSize;
    private LocalDateTime uploadTime;
    private String fileMd5;
    private String fileOwner;
    private String fileVirtualPath;
    private String fileState;
    private LocalDateTime modifyTime;
    private String fileDescription;
    private String fileUrl;
    private Integer downloadCount;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String fileExt;
    private Integer fileEncrypt;
    private String fileKey;

}
