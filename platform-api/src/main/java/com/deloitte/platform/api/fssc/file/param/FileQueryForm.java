package com.deloitte.platform.api.fssc.file.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description :   File查询from对象
 * @Modified :
 */
@ApiModel("File查询表单")
@Data
public class FileQueryForm extends BaseQueryForm<FileQueryParam>  {


    @ApiModelProperty(value = "关联对象ID")
    private Long documentId;

    @ApiModelProperty(value = "关联对象类型表名")
    private String documentType;

}
