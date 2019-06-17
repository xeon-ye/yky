package com.deloitte.platform.api.fssc.pay.vo;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description : PyPamentOrderInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增PyPamentOrderInfo表单")
@Data
public class PyPamentOrderInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="ID")
    private  Long id;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名申请人",required = true)
    private String createUserName;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号" ,required = true)
    private String documentNum;

    @ApiModelProperty(value = "单据状态" ,required = true)
    private String documentStatus;

    @ApiModelProperty(value = "备注摘要")
    private String remark;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "合计金额外币")
    private BigDecimal totalAmountOtherCurrency;

    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;

    @ApiModelProperty(value = "未支付金额")
    private BigDecimal noPaidAmount;

    @ApiModelProperty(value = "待支付单据编号")
    private String payDocumentNum;

    @ApiModelProperty(value = "待支付单据类型")
    private String payDocumentType;

    @ApiModelProperty(value = "付款方式")
    private String paymentType;

    @ApiModelProperty(value = "银行账号")
    private String bankAccount;

    @ApiModelProperty(value = "账户性质")
    private String bankType;

    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "预留字段1")
    private String ex1;

    @ApiModelProperty(value = "预留字段2,被占用用于放子表关联的单据id")
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

    @ApiModelProperty(value = "提交类型 撤回后提交RESUBMIT_ROLLBACK" +
            " 驳回后提交RESUBMIT_REJECT,首次提交FIRST_SUBMIT",required = true)
    private String reSubmitType;

    @ApiModelProperty(value = "是否预算检查超过80%")
    private String budgetWarningCheck;

    @ApiModelProperty("银行ID")
    private Long bankId;

    @ApiModelProperty(value = "付款状态")
    private  String payStatus;

    @ApiModelProperty(value = "是否从其他单据跳转")
    private  String isPayOrder;

    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "文件id集合")
    private List<Long> fileIds;
        @ApiModelProperty(value = "对公，对私，公务卡")
        @Valid
    private List<PyPamentBusinessLineForm> pyPamentBusinessLineForms;

  /*  private List<PyPamentPrivateLineForm> pyPamentPrivateLineForms;

    private List<PyPamentPublicLineForm> pyPamentPublicLineForms;*/

}
