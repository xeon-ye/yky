package com.deloitte.platform.api.fssc.general.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :   GeExpenseBorrowPrepay查询from对象
 * @Modified :
 */
@ApiModel("GeExpenseBorrowPrepay查询表单")
@Data
public class GeExpenseBorrowPrepayQueryForm extends BaseQueryForm<GeExpenseBorrowPrepayQueryParam>  {


    @ApiModelProperty(value = "单据类型")
    @NotEmpty
    private String documentType;

    @ApiModelProperty(value = "付款方式 逗号隔开 如: CASH,BUSINESS_CARD")
    private String paymentTypes;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "不传")
    private Long deptId;
    @ApiModelProperty(value = "不传")
    private Long unitId;
    @ApiModelProperty(value = "不传")
    private Long createBy;

    @ApiModelProperty(value = "事前申请id")
    private Long applyForId;

    @ApiModelProperty(value = "对方签约主体")
    private String subjectSuperficil;

    @ApiModelProperty(value = "约定付款批次")
    private String agreedPaymentLot;

    @ApiModelProperty(value = "履行计划ID")
    private Long travelPlanId;

    @ApiModelProperty(value = "履行人")
    private Long executor;

    @ApiModelProperty(value = "合同编号")
    private String contactNumber;

    @ApiModelProperty(value = "付款方式")
    private String paymentType;

    @ApiModelProperty(value = "不传")
    private List<String> paymentTypeList;

    @ApiModelProperty(value = "付款单银行账户财务会计科目")
    private String paymentAccountCode;

    @ApiModelProperty(value = "付款银行账号")
    private String bankAccount;

    @ApiModelProperty(value = "付款银行账户")
    private String bankType;
}
