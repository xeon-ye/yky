package com.deloitte.platform.api.project.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-06-05
 * @Description : Maintenance返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "维护项目id")
    private String maintenanceId;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    private String applicationId;

    @ApiModelProperty(value = "项目主要内容")
    private String mainNote;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

    @ApiModelProperty(value = "维护状态code")
    private String mainStatusCode;

    @ApiModelProperty(value = "维护状态name")
    private String mainStatusName;

    @ApiModelProperty(value = "关联父级项目id projectId")
    private String mainParentId;

}
