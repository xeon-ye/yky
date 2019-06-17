package com.deloitte.platform.api.hr.retireRehiring.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-15
 * @Description :   RetireRemind查询from对象
 * @Modified :
 */
@ApiModel("RetireRemind查询表单")
@Data
public class RemindQueryForm extends BaseQueryForm<RemindQueryParam>  {


    @ApiModelProperty(value = "注释")
    private Long id;

    @ApiModelProperty(value = "通知类型()")
    private String remindType;

    @ApiModelProperty(value = "通知内容")
    private String content;

    @ApiModelProperty(value = "通知时间")
    private LocalDateTime remindTime;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "${field.comment}")
    private Long createBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "保留字段1")
    private String ex1;

    @ApiModelProperty(value = "保留字段2")
    private String ex2;

    @ApiModelProperty(value = "保留字段3")
    private String ex3;
}
