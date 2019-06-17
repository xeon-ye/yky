package com.deloitte.platform.api.fssc.attchdef.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-09
 * @Description :   BaseFileDef查询from对象
 * @Modified :
 */
@ApiModel("BaseFileDef查询表单")
@Data
public class BaseFileDefQueryForm extends BaseQueryForm<BaseFileDefQueryParam>  {


    @ApiModelProperty(value = "单据名称")
    private String documentName;


    @ApiModelProperty(value = "支出大类名称")
    private String mainTypeName;


    @ApiModelProperty(value = "收入大类名称")
    private String inComeMainTypeName;

    @ApiModelProperty(value = "是否有效")
    private String isValid;


}
