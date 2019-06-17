package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description : ProCategory新增修改form对象
 * @Modified :
 */
@ApiModel("新增ProCategory表单")
@Data
public class ProCategoryForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "${field.comment}")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "上级code")
    private String parentCode;

    @ApiModelProperty(value = "${field.comment}")
    private String remark;

    @ApiModelProperty(value = "所属系统类别")
    private String sysType;

}
