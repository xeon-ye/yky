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
 * @Date : Create in 2019-06-06
 * @Description :  ExeReply查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExeReplyQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String exeReplyId;
    private String projectId;
    private String changeId;
    private String exeReplyCode;
    private String exeReplyName;
    private String exeReplyAdv;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private Long orgId;
    private String orgPath;
    private String serviceNum;
    private String repPersonId;
    private String repPersonName;
    private String repDepartmentId;
    private String repDepartmentName;
    private String replyId;
    private String maintenanceId;

}
