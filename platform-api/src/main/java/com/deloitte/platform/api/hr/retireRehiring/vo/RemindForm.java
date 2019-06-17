package com.deloitte.platform.api.hr.retireRehiring.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-15
 * @Description : RetireRemind新增修改form对象
 * @Modified :
 */
@ApiModel("新增RetireRemind表单")
@Data
public class RemindForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "通知类型()")
    private String remindType;

    @ApiModelProperty(value = "通知内容")
    private String content;

    @ApiModelProperty(value = "通知时间")
    private LocalDateTime remindTime;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "保留字段1")
    private String ex1;

    @ApiModelProperty(value = "保留字段2")
    private String ex2;

    @ApiModelProperty(value = "保留字段3")
    private String ex3;

}
