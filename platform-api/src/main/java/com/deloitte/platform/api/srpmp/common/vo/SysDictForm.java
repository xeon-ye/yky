package com.deloitte.platform.api.srpmp.common.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-20
 * @Description : SysDict新增修改form对象
 * @Modified :
 */
@ApiModel("新增SysDict表单")
@Data
public class SysDictForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "字典值")
    private String dictValue;

    @ApiModelProperty(value = "父级字典编码，顶级为NULL")
    private Long dictParent;

    @ApiModelProperty(value = "字典生效日期")
    private LocalDateTime activeDate;

    @ApiModelProperty(value = "字典失效日期")
    private LocalDateTime expiredDate;

    @ApiModelProperty(value = "是否过期，0正常 1过期")
    private Integer isExpired;

}
