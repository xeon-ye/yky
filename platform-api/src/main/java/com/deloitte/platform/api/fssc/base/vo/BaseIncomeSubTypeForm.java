package com.deloitte.platform.api.fssc.base.vo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description : 收入小类新增修改form对象
 * @Modified :
 */
@ApiModel("新增收入小类表单")
@Data
public class BaseIncomeSubTypeForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "编码",required = true)
    @NotBlank(message = "收入小类编码不能为空")
    private String code;

    @ApiModelProperty(value = "名称",required = true)
    @NotBlank(message = "收入小类名称不能为空")
    private String name;

    @ApiModelProperty(value = "收入大类ID",required = true)
    @NotBlank(message = "收入大类ID不能为空")
    private String incomeMainTypeId;

    @ApiModelProperty(value = "财-会计科目编码",required = true)
    @NotBlank(message = "财-会计科目编码不能为空")
    private String cAccountCode;

    @ApiModelProperty(value = "预-会计科目编码",required = true)
    @NotBlank(message = "预-会计科目编码不能为空")
    private String yAccountCode;

    @ApiModelProperty(value = "是否有效",required = true)
    private String validFlag;

    @ApiModelProperty(value = "生效日期")
    private LocalDateTime validDate;

    @ApiModelProperty(value = "失效日期")
    private LocalDateTime invalidDate;

}
