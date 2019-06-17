package com.deloitte.platform.api.fssc.trade.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-13
 * @Description :   TradeInformation查询from对象
 * @Modified :
 */
@ApiModel("TradeInformation查询表单")
@Data
public class TradeInformationQueryForm extends BaseQueryForm<TradeInformationQueryParam> {

    @ApiModelProperty(value = "查询账号")
    private String queryAccount;

    @ApiModelProperty(value = "查询类型")
    private String queryType;

    @ApiModelProperty(value = "交易金额-上限")
    private BigDecimal tradeAmountUp;

    @ApiModelProperty(value = "交易金额下限")
    private BigDecimal tradeAmountDown;

    @ApiModelProperty(value = "交易日期-起始")
    private LocalDateTime tradeDateStart;

    @ApiModelProperty(value = "交易日期-结束")
    private LocalDateTime tradeDateEnd;

    @ApiModelProperty(value = "交易货币")
    private String tradeCurrency;

    @ApiModelProperty(value = "联行号")
    private String interBankNum;

    @ApiModelProperty(value = "银行账户列表")
    private List<String> bankAccountList;

    @ApiModelProperty(value = "付款单位")
    private String paymentUnitCode;

    @ApiModelProperty(value = "付款银行账户")
    private String paymentBankAccount;

    @ApiModelProperty(value = "交易流水编码")
    private String tradeSeqNum;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "组织ID")
    private String orgId;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

}
