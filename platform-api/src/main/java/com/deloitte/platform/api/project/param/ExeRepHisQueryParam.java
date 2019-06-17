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
 * @Description :  ExeRepHis查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExeRepHisQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String repHisId;
    private String exeReplyId;
    private String replyPartId;
    private String replyPartName;
    private String replyAdv;
    private String replyEndCode;
    private String replyEndName;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private Long orgId;
    private String orgPath;
    private String applicationId;
    private String projectId;
    private String repPersonId;
    private String repPersonName;
    private String repDepartmentId;
    private String repDepartmentName;
    private String maintenanceId;
    private String replyId;

}
