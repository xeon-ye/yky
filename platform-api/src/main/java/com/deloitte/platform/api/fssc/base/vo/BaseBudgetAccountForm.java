package com.deloitte.platform.api.fssc.base.vo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-06
 * @Description : BaseBudgetAccount新增修改form对象
 * @Modified :
 */
@ApiModel("新增BaseBudgetAccount表单")
@Data
public class BaseBudgetAccountForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID",notes = "修改时,必须传,否则新增处理")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "编码",required = true)
    @NotBlank(message = "预算科目编码不能为空")
    private String code;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "预算科目名称不能为空")
    private String name;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "启用标志")
    @NotBlank(message = "启用标志不能为空")
    private String validFlag;

    @ApiModelProperty(value = "预算类型")
    @NotBlank(message = "预算类型不能为空")
    private String budgetType;

}
