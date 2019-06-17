package com.deloitte.platform.api.fssc.basecontract.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description : BaseContractPlanLine新增修改form对象
 * @Modified :
 */
@ApiModel("新增BaseContractPlanLine表单")
@Data
public class BaseContractPlanLineForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "创建人姓名申请人")
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "合同ID")
    private Long contractId;

    @ApiModelProperty(value = "合同名称")
    private String contractName;

    @ApiModelProperty(value = "履行计划ID")
    private Long travelPlanId;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "归属部门名称")
    private String deptName;

    @ApiModelProperty(value = "对方签约主体")
    private String sideSubjectName;

    @ApiModelProperty(value = "约定付款批次")
    private String appointPayStage;

    @ApiModelProperty(value = "约定比例")
    private String agreedPropertions;

    @ApiModelProperty(value = "约定付款金额")
    private BigDecimal agreedPaymentAmount;

    @ApiModelProperty(value = "计划付款时间")
    private LocalDateTime planPayTime;

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
    @ApiModelProperty(value = "已核销金额")
    private BigDecimal hasVerAmount;
    @ApiModelProperty(value="实际付款金额")
    private BigDecimal actualPlayAmount;

    @ApiModelProperty(value="实际付款金额/发票金额")
    private BigDecimal receipPlayAmount;

    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;

}
