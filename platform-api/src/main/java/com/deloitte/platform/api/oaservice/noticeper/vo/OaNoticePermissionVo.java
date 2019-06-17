package com.deloitte.platform.api.oaservice.noticeper.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-05-29
 * @Description : OaNoticePermission返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OaNoticePermissionVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限ID")
    private String id;

    @ApiModelProperty(value = "业务类型")
    private String type;

    @ApiModelProperty(value = "${field.comment}")
    private Long objectId;

    @ApiModelProperty(value = "部门CODE")
    private String orgCode;

    @ApiModelProperty(value = "单位CODE")
    private String deptCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
