package com.deloitte.platform.api.fssc.attchdef.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-09
 * @Description : BaseFileDef新增修改form对象
 * @Modified :
 */
@ApiModel("新增BaseFileDef表单")
@Data
public class BaseFileDefForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    private Long id;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "单据名称")
    private String documentName;

    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "支出大类名称")
    private String mainTypeName;

    @ApiModelProperty(value = "收入大类ID")
    private Long inComeMainTypeId;

    @ApiModelProperty(value = "收入大类名称")
    private String inComeMainTypeName;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "文件类型定义")
    private List<BaseFileDefLineForm> baseFileDefLineForms;

}
