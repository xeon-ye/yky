package com.deloitte.platform.api.fssc.base.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : hjy
 * @Date : Create in 2019-02-27
 * @Description : BaseExpenseMainType新增修改form对象
 * @Modified :
 */
@ApiModel("新增支出大类表单")
@Data
public class BaseExpenseMainTypeForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID",notes = "修改时,必须传,否则做新增处理")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "是否父值")
    private String parentFlag;

    @ApiModelProperty(value = "父值编码")
    private String parentCode;

    @ApiModelProperty(value = "是否有效")
    private String invalidFlag;

    @ApiModelProperty(value = "生效日期")
    private LocalDateTime validDate;

    @ApiModelProperty(value = "失效日期")
    private LocalDateTime invalidDate;

    @ApiModelProperty(value = "关联预算科目ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long budgetAccountId;

}
