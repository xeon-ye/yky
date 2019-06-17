package com.deloitte.platform.api.fssc.file.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description :  File查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long createBy;
    private String createUserName;
    private Long updateBy;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private String ex1;
    private String ex2;
    private String ex3;
    private String ex4;
    private String ex5;
    private Long version;
    private String fileType;
    private String fileUrl;
    private Long documentId;
    private String documentType;
    private String fileName;
    private Long remoteFileId;
}
