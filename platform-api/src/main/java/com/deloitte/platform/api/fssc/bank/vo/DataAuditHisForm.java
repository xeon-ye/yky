package com.deloitte.platform.api.fssc.bank.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-25
 * @Description : DataAuditHis新增修改form对象
 * @Modified :
 */
@ApiModel("新增DataAuditHis表单")
@Data
public class DataAuditHisForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "对象ID")
    private Long documentId;

    @ApiModelProperty(value = "对象类型即表名")
    private String documentType;

    @ApiModelProperty(value = "审核意见")
    private String auditOpin;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

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

    @ApiModelProperty(value = "版本")
    private Long version;
    @ApiModelProperty(value = "拒绝原因")
    private String refusedReson;
}
