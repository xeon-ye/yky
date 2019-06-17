package com.deloitte.platform.api.oaservice.noticeper.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-05-29
 * @Description :   OaNoticePermission查询from对象
 * @Modified :
 */
@ApiModel("OaNoticePermission查询表单")
@Data
public class OaNoticePermissionQueryForm extends BaseQueryForm<OaNoticePermissionQueryParam>  {


    @ApiModelProperty(value = "权限ID")
    private Long id;

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
