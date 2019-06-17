package com.deloitte.platform.api.isump.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description :   ProDeputyAccount查询from对象
 * @Modified :
 */
@ApiModel("ProDeputyAccount查询表单")
@Data
public class ProDeputyAccountQueryForm extends BaseQueryForm<ProDeputyAccountQueryParam>  {


    @ApiModelProperty(value = "${field.comment}")
    private Long id;

    @ApiModelProperty(value = "${field.comment}")
    private Long deputyAccountId;

    @ApiModelProperty(value = "${field.comment}")
    private Long proCategoryId;
}
