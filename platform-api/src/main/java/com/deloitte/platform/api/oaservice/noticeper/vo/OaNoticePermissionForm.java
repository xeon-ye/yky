package com.deloitte.platform.api.oaservice.noticeper.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-05-29
 * @Description : OaNoticePermission新增修改form对象
 * @Modified :
 */
@ApiModel("新增OaNoticePermission表单")
@Data
public class OaNoticePermissionForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "业务类型")
    private String type;

    @ApiModelProperty(value = "${field.comment}")
    private Long objectId;

    @ApiModelProperty(value = "部门CODE")
    private String orgCode;

    @ApiModelProperty(value = "单位CODE")
    private String deptCode;

}
