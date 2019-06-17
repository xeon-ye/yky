package com.deloitte.platform.api.srpmp.common.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-03-04
 * @Description : SerialNoCenter新增修改form对象
 * @Modified :
 */
@ApiModel("新增SerialNoCenter表单")
@Data
public class SerialNoCenterForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "序列号类型")
    private String serialType;

    @ApiModelProperty(value = "序列号头")
    private String serialHeader;

    @ApiModelProperty(value = "流水号")
    private Long serialNo;

}
