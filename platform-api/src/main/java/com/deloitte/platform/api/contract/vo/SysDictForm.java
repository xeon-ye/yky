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
 * @Description : SysDict新增修改form对象
 * @Modified :
 */
@ApiModel("新增SysDict表单")
@Data
public class SysDictForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "字典类型")
    private String type;

    @ApiModelProperty(value = "字典编码")
    private String code;

    @ApiModelProperty(value = "字典值")
    private String value;

    @ApiModelProperty(value = "父级字典编码，顶级为0")
    private Long parentCode;

    @ApiModelProperty(value = "字典生效时间")
    private LocalDateTime activeDate;

    @ApiModelProperty(value = "字典失效时间")
    private LocalDateTime expiredDate;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    private String isUesd;

    @ApiModelProperty(value = "描述")
    private String describe;

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
