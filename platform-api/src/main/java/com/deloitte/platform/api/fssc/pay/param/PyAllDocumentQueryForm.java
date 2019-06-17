package com.deloitte.platform.api.fssc.pay.param;

import com.deloitte.platform.api.fssc.general.param.GeExpenseBorrowPrepayQueryParam;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :   PyAllDocumentQueryForm查询from对象
 * @Modified :
 */
@ApiModel("GeExpenseBorrowPrepay查询表单")
@Data
public class PyAllDocumentQueryForm {


    @ApiModelProperty(value = "关联是对公付款，对私付款或公务卡单据类型")
    @NotEmpty
    private String connectDocumentType;

    @ApiModelProperty(value = "单据类型")
    @NotEmpty
    private String documentType;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "不传")
    private Long createBy;
    @ApiModelProperty(value = "报账单开始金额")
    private BigDecimal moneyStart;
    @ApiModelProperty(value = "报账单结束金额")
    private BigDecimal moneyEnd;

    @ApiModelProperty(value = "付款，还款开始金额")
    private BigDecimal payMoneyStart;
    @ApiModelProperty(value = "付款，还款结束金额")
    private BigDecimal payMoneyEnd;

    @ApiModelProperty(value = "交易开始金额")
    private BigDecimal transactionAmountStart;
    @ApiModelProperty(value = "交易结束金额")
    private BigDecimal transactionAmountEnd;

    @ApiModelProperty(value = "创建开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value="创建结束时间")
    private LocalDateTime endTime;
    @ApiModelProperty(value="是否事后补单")
    private String isAfterPatch;

}
