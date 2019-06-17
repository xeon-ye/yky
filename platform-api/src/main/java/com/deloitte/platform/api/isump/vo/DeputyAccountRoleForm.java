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
 * @Description : DeputyAccountRole新增修改form对象
 * @Modified :
 */
@ApiModel("新增DeputyAccountRole表单")
@Data
public class DeputyAccountRoleForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "副账号ID")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long deputyAccountId;

    @ApiModelProperty(value = "角色ID")
    @JsonSerialize(using = JsonLongSerialize.class)
    @JsonDeserialize(using = JsonLongDeserializer.class)
    private Long roleId;

}
