package com.deloitte.platform.api.fssc.trade.vo;

import com.deloitte.platform.api.fssc.bank.vo.BankUnitInfoVo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-13
 * @Description : TradeInformation返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeInformationVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

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

    @ApiModelProperty(value = "付款银行名称")
    private String paymentBankName;

    @ApiModelProperty(value = "交易日期")
    private LocalDateTime tradeDate;

    @ApiModelProperty(value = "交易日期-字符串形式")
    private String tradeDateStr;

    @ApiModelProperty(value = "交易时间")
    private LocalDateTime tradeTime;

    @ApiModelProperty(value = "交易时间-字符串形式")
    private String tradeTimeStr;

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

    @ApiModelProperty(value = "收款人账户")
    private String receiverAccount;

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

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "收款单位编码")
    private String receiverUnitCode;

    @ApiModelProperty(value = "收款单位名称")
    private String receiverUnitName;

    @ApiModelProperty(value = "收款单位ID")
    private String receiverUnitId;

    @ApiModelProperty(value = "收款账户类型")
    private String receiverAccountType;

    @ApiModelProperty(value = "收款账户类型编码")
    private String receiverAccountTypeCode;

    @ApiModelProperty(value = "付款单位编码")
    private String paymentUnitCode;

    @ApiModelProperty(value = "付款单位名称")
    private String paymentUnitName;

    @ApiModelProperty(value = "付款单位ID")
    private String paymentUnitId;

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
    private BigDecimal version;

    @ApiModelProperty(value = "用途")
    String purpose;

    @ApiModelProperty(value = "记录标识号")
    String recordNum;

    @ApiModelProperty(value = "交易附言")
    String remark;

    @ApiModelProperty(value = "银行账号名称")
    private String receiverBankAccountName;

    @ApiModelProperty(value = "处理状态")
    private String processStatus;

    @ApiModelProperty(value = "关联单据")
    private List<BusinessRelateDocument> relateDocumentList;

    private List<BankUnitInfoVo> bankUnitInfoVos;

}
