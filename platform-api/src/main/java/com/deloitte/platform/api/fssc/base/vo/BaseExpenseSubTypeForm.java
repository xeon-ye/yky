package com.deloitte.platform.api.fssc.base.vo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-02
 * @Description : BaseExpenseSubType新增修改form对象
 * @Modified :
 */
@ApiModel("新增支出小类表单")
@Data
public class BaseExpenseSubTypeForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "编码")
    @NotBlank(message = "支出小类编码不能为空")
    private String code;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "支出小类名称不能为空")
    private String name;

    @ApiModelProperty(value = "支出大类ID")
    @NotBlank(message = "支出大类ID不能为空")
    private String expenseMainTypeId;

    @ApiModelProperty(value = "财-会计科目编码",required = true)
    @NotBlank(message = "财-会计科目编码不能为空")
    private String cAccountCode;

    @ApiModelProperty(value = "预-会计科目编码",required = true)
    @NotBlank(message = "预-会计科目编码不能为空")
    private String yAccountCode;

    @ApiModelProperty(value = "是否有效")
    private String validFlag;

    @ApiModelProperty(value = "生效日期")
    private LocalDateTime validDate;

    @ApiModelProperty(value = "失效日期")
    private LocalDateTime invalidDate;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "费用类型")
    private String costType;

}
