package com.deloitte.platform.api.project.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/5/22 17:13
 * @Description :
 * @Modified:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String id;
    private String fileName;
    private String filePath;
    private String fileType;
    private String mimeType;
    private String fileSize;
    private String uploadTime;
    private String fileMd5;
    private String fileOwner;
    private String fileVirtualPath;
    private String fileState;
    private String modifyTime;
    private String fileDescription;
    private String fileUrl;
    private String downloadCount;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String fileExt;
    private String fileEncrypt;
    private String fileKey;

}
