package com.deloitte.platform.api.hr.common.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   Dict查询from对象
 * @Modified :
 */
@ApiModel("Dict查询表单")
@Data
public class DictQueryForm extends BaseQueryForm<DictQueryParam>  {


    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "上级编码")
    private String parentCode;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "状态")
    private String dtcodes;
}
