package com.deloitte.platform.api.fssc.bpm.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-18
 * @Description : EventDef新增修改form对象
 * @Modified :
 */
@ApiModel("新增EventDef表单")
@Data
public class EventDefForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "预留字段1")
    private String ex1;

    @ApiModelProperty(value = "预留字段2")
    private String ex2;

    @ApiModelProperty(value = "预留字段3")
    private String ex3;

    @ApiModelProperty(value = "预留字段4")
    private String ex4;

    @ApiModelProperty(value = "预留字段5")
    private String ex5;

    @ApiModelProperty(value = "预留字段6")
    private String ex6;

    @ApiModelProperty(value = "预留字段7")
    private String ex7;

    @ApiModelProperty(value = "预留字段8")
    private String ex8;

    @ApiModelProperty(value = "预留字段9")
    private String ex9;

    @ApiModelProperty(value = "预留字段10")
    private String ex10;

    @ApiModelProperty(value = "预留字段11")
    private String ex11;

    @ApiModelProperty(value = "预留字段12")
    private String ex12;

    @ApiModelProperty(value = "预留字段13")
    private String ex13;

    @ApiModelProperty(value = "预留字段14")
    private String ex14;

    @ApiModelProperty(value = "预留字段15")
    private String ex15;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "回调class")
    private String doClassName;

    @ApiModelProperty(value = "回调方法")
    private String doMethod;

    @ApiModelProperty(value = "回调参数")
    private String doParams;

    @ApiModelProperty(value = "事件描述")
    private String eventDescription;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "执行顺序")
    private Long doOrder;

    @ApiModelProperty(value = "执行类型事前,事后BEFORE,AFTER")
    private String beforeOrAfter;

}
