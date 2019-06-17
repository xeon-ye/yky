package com.deloitte.platform.api.fssc.file.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description : File新增修改form对象
 * @Modified :
 */
@ApiModel("新增File表单")
@Data
public class FileForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "关联对象ID")
    private Long documentId;

    @ApiModelProperty(value = "关联对象类型表名")
    @NotEmpty
    private String documentType;

    @ApiModelProperty(value = "文件类型定义行id")
    private Long fileDefLineId;

}
