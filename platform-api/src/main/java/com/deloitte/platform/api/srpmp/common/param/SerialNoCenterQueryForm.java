package com.deloitte.platform.api.srpmp.common.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-03-04
 * @Description :   SerialNoCenter查询from对象
 * @Modified :
 */
@ApiModel("SerialNoCenter查询表单")
@Data
public class SerialNoCenterQueryForm extends BaseQueryForm<SerialNoCenterQueryParam>  {


    @ApiModelProperty(value = "ID，主键，业务不相关")
    private Long id;

    @ApiModelProperty(value = "序列号类型")
    private String serialType;

    @ApiModelProperty(value = "序列号头")
    private String serialHeader;

    @ApiModelProperty(value = "流水号")
    private Long serialNo;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;
}
