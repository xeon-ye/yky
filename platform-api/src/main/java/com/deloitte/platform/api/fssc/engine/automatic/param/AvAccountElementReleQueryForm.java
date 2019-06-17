package com.deloitte.platform.api.fssc.engine.automatic.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-27
 * @Description :   AvAccountElementRele查询from对象
 * @Modified :
 */
@ApiModel("AvAccountElementRele查询表单")
@Data
public class AvAccountElementReleQueryForm extends BaseQueryForm<AvAccountElementReleQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "核算要素列ID")
    private Long elementId;

    @ApiModelProperty(value = "账薄ID")
    private Long ledgerId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "更新人")
    private Long updateBy;

    @ApiModelProperty(value = "预留属性1")
    private String etx1;

    @ApiModelProperty(value = "预留属性2")
    private String etx2;

    @ApiModelProperty(value = "预留属性3")
    private String etx3;

    @ApiModelProperty(value = "预留属性4")
    private String etx4;

    @ApiModelProperty(value = "预留属性5")
    private String etx5;
}
