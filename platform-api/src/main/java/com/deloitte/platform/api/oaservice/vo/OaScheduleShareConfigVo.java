package com.deloitte.platform.api.oaservice.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-23
 * @Description : OaScheduleShareConfig返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OaScheduleShareConfigVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "发起共享人id")
    private String userId;

    @ApiModelProperty(value = "发起共享人姓名")
    private String userName;

    @ApiModelProperty(value = "共享人")
    private String shareToId;

    @ApiModelProperty(value = "共享人姓名")
    private String shareToName;

    @ApiModelProperty(value = "共享人所在部门id")
    private String shareToDeptId;

    @ApiModelProperty(value = "共享人所在部门名称")
    private String shareToDeptName;

    @ApiModelProperty(value = "权限：VIEW:查看/EDIT:编辑")
    private String permissions;

    @ApiModelProperty(value = "是否有效：Y/N")
    private String enableFlag;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后更新人")
    private String updateBy;

    @ApiModelProperty(value = "最后更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备用字段")
    private String ext1;

    @ApiModelProperty(value = "备用字段")
    private String ext2;

    @ApiModelProperty(value = "备用字段")
    private String ext3;

    @ApiModelProperty(value = "备用字段")
    private String ext4;

    @ApiModelProperty(value = "备用字段")
    private String ext5;

}
