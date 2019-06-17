package com.deloitte.platform.api.fssc.general.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :   GeExpensePaymentList查询from对象
 * @Modified :
 */
@ApiModel("GeExpensePaymentList查询表单")
@Data
public class GeExpensePaymentListQueryForm extends BaseQueryForm<GeExpensePaymentListQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型表名")
    private String documentType;

    @ApiModelProperty(value = "付款金额")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "供应商ID")
    private Long vendorId;

    @ApiModelProperty(value = "供应商名称")
    private String vendorName;

    @ApiModelProperty(value = "银行ID")
    private Long bankId;

    @ApiModelProperty(value = "付款状态")
    private String payStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

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

    @ApiModelProperty(value = "统一社会信息代码")
    private String commonCreditCode;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "账户名称")
    private String bankAccount ;

    @ApiModelProperty(value = "联行号")
    private String interBranchNumber;
}
