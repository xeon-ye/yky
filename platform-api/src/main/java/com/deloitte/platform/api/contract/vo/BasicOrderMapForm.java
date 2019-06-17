package com.deloitte.platform.api.contract.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : BasicOrderMap新增修改form对象
 * @Modified :
 */
@ApiModel("新增BasicOrderMap表单")
@Data
public class BasicOrderMapForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "合同编号")
    private Long contractId;

    @ApiModelProperty(value = "订单编号")
    private Long orderId;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUsed;

    @ApiModelProperty(value = "备用字段")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    private Long spareField5;

}
