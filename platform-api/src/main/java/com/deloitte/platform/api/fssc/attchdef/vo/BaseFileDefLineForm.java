package com.deloitte.platform.api.fssc.attchdef.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-09
 * @Description : BaseFileDefLine新增修改form对象
 * @Modified :
 */
@ApiModel("新增BaseFileDefLine表单")
@Data
public class BaseFileDefLineForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    private Long id;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "附件类型定义ID")
    private Long fileDefId;

    @ApiModelProperty(value = "附件名称")
    private String attachName;



}
