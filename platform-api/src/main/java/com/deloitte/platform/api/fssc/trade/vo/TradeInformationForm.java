package com.deloitte.platform.api.fssc.trade.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-13
 * @Description : TradeInformation新增修改form对象
 * @Modified :
 */
@ApiModel("新增/修改 交易信息表单")
@Data
public class TradeInformationForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "查询账号")
    private String queryAccount;

    @ApiModelProperty(value = "来往帐标识")
    private String dealingsFlag;

    @ApiModelProperty(value = "业务类型")
    private String businessType;

    @ApiModelProperty(value = "付款开户行号")
    private String paymentBankNum;

    @ApiModelProperty(value = "付款人账号")
    private String paymentAccount;

    @ApiModelProperty(value = "付款人名称")
    private String paymentName;

    @ApiModelProperty(value = "交易日期")
    private LocalDateTime tradeDate;

    @ApiModelProperty(value = "交易时间")
    private LocalDateTime tradeTime;

    @ApiModelProperty(value = "交易货币")
    private String tradeCurrency;

    @ApiModelProperty(value = "交易金额")
    private BigDecimal tradeAmount;

    @ApiModelProperty(value = "交易后余额")
    private BigDecimal tradeRemainingAmount;

    @ApiModelProperty(value = "交易流水号")
    private String tradeSerialNum;

    @ApiModelProperty(value = "汇款行业务编号")
    private String remittingBankBusinessNum;

    @ApiModelProperty(value = "收款人行号")
    private String receiverBankNum;

    @ApiModelProperty(value = "收款人开户行名")
    private String receiverBankName;

    @ApiModelProperty(value = "收款人名称")
    private String receiverName;

    @ApiModelProperty(value = "收款行业务编号")
    private String receiverBankBusinessNum;

    @ApiModelProperty(value = "被代理行号")
    private String proxiedBankNum;

    @ApiModelProperty(value = "被代理账号")
    private String proxiedAccount;

    @ApiModelProperty(value = "被代理账户名")
    private String proxiedAccountName;

    @ApiModelProperty(value = "被代理账户开户行名")
    private String proxiedAccountBankNum;

    @ApiModelProperty(value = "可用余额")
    private BigDecimal usableAmount;

    @ApiModelProperty(value = "冻结金额")
    private BigDecimal freezeAmount;

    @ApiModelProperty(value = "透支额度")
    private BigDecimal overdraftAmount;

    @ApiModelProperty(value = "可用透支额度")
    private BigDecimal usableOverdraftAmount;

    @ApiModelProperty(value = "起息日期")
    private LocalDateTime dateOfValue;

    @ApiModelProperty(value = "凭证类型")
    private String voucherType;

    @ApiModelProperty(value = "凭证号码")
    private String voucherNum;

    @ApiModelProperty(value = "汇率")
    private BigDecimal exchangeRate;

    @ApiModelProperty(value = "渠道标识")
    private String channelIdentification;

    @ApiModelProperty(value = "商户号")
    private String merchantNum;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    private String ext5;

    @ApiModelProperty(value = "版本")
    private Long version;

}
