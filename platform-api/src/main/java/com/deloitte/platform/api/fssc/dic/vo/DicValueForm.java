package com.deloitte.platform.api.fssc.dic.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description : DicValue新增修改form对象
 * @Modified :
 */
@ApiModel("新增DicValue表单")
@Data
public class DicValueForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "字典主表类型ID")
    private Long id;

    @ApiModelProperty(value = "字典主表类型dicParentId")
    private Long dicParentId;

    @ApiModelProperty(value = "字段代码")
    private String dicCode;

    @ApiModelProperty(value = "字段名称")
    private String dicName;

    @ApiModelProperty(value = "字典值")
    private String dicValue;

    @ApiModelProperty(value = "字段描述")
    private String dicDesciption;

    @ApiModelProperty(value = "排序编号")
    private Double dicOrder;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "预留字段1")
    private String ext1;




}
