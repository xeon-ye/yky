package com.deloitte.platform.api.srpmp.project.base.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-03-15
 * @Description :   SrpmsVoucher查询from对象
 * @Modified :
 */
@ApiModel("SrpmsVoucher查询表单")
@Data
public class SrpmsVoucherQueryForm extends BaseQueryForm<SrpmsVoucherQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "单据业务编号")
    private String bizNumber;

}
