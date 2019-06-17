package com.deloitte.platform.api.hr.common.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : Dict新增修改form对象
 * @Modified :
 */
@ApiModel("新增Dict表单")
@Data
public class DictForm extends BaseForm {
    private static final long serialVersionUID = 1L;


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

}
