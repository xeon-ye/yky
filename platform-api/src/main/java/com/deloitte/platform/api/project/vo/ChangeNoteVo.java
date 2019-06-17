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
 * @Date : Create in 2019-06-06
 * @Description : ChangeNote返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeNoteVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "变更id")
    private String changeId;

    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    private String applicationId;

    @ApiModelProperty(value = "项目执行id")
    private String executionId;

    @ApiModelProperty(value = "变更原因")
    private String changeAdv;

    @ApiModelProperty(value = "变更状态code")
    private String changeStatusCode;

    @ApiModelProperty(value = "变更状态name")
    private String changeStatusName;

    @ApiModelProperty(value = "历史记录时间")
    private LocalDateTime hisTime;

    @ApiModelProperty(value = "历史记录变更人")
    private String hisBy;

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

    @ApiModelProperty(value = "拓展3")
    private String ext3;

    @ApiModelProperty(value = "维度id")
    private Long orgId;

    @ApiModelProperty(value = "path")
    private String orgPath;

    @ApiModelProperty(value = "批复id")
    private String replyId;

    @ApiModelProperty(value = "项目维护id")
    private String maintid;

}
