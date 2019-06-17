package com.deloitte.platform.api.fssc.travle.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-02
 * @Description : TasTravelTime新增修改form对象
 * @Modified :
 */
@ApiModel("新增TasTravelTime表单")
@Data
public class TasTravelTimeForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "关联行表ID")
    private Long travelLineId;

    @ApiModelProperty(value = "差旅起始时间")
    private LocalDateTime travelBeginTime;

    @ApiModelProperty(value = "差旅结束时间")
    private LocalDateTime travelEdnTime;

    @ApiModelProperty(value="关联类型")
    private  String documentType;
}
