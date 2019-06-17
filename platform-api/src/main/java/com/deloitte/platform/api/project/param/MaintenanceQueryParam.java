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
 * @Date : Create in 2019-06-05
 * @Description :  Maintenance查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String maintenanceId;
    private String projectId;
    private String applicationId;
    private String mainNote;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String mainStatusCode;
    private String mainStatusName;
    private String mainParentId;

}