package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.api.utils.JsonLongDeserializer;
import com.deloitte.platform.api.utils.JsonLongSerialize;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : OrgRole新增修改form对象
 * @Modified :
 */
@ApiModel("新增OrgRole表单")
@Data
public class OrgRoleForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "组织架构ID")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long orgId;

    @ApiModelProperty(value = "角色ID")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long roleId;

}
