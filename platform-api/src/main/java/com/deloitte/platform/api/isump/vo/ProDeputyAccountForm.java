package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description : ProDeputyAccount新增修改form对象
 * @Modified :
 */
@ApiModel("新增ProDeputyAccount表单")
@Data
public class ProDeputyAccountForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "${field.comment}")
    private Long deputyAccountId;

    @ApiModelProperty(value = "${field.comment}")
    private Long proCategoryId;

}
