package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : zhengchun
 * @Date : Create in 2019-05-30
 * @Description :  Reply查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String replyDocumentId;
    private String projectId;
    private String applicationId;
    private String operationYear;
    private String replyYear;
    private String beginYear;
    private String cycle;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Long orgId;
    private String orgPath;
    private String replyCode;
    private String serviceNum;
    private String replyStatusCode;
    private String replyStatusName;
    private LocalDateTime replyTime;
    private String replyPersonId;
    private String replyPersonName;

}
