package com.deloitte.services.fssc.system.bank.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 交易信息
 * </p>
 *
 * @author jaws
 * @since 2019-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("TRADE_INFORMATION")
@ApiModel(value="TradeInformation对象", description="交易信息")
public class TradeInformation extends Model<TradeInformation> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "查询账号")
    @TableField("QUERY_ACCOUNT")
    private String queryAccount;

    @ApiModelProperty(value = "来往帐标识")
    @TableField("DEALINGS_FLAG")
    private String dealingsFlag;

    @ApiModelProperty(value = "业务类型")
    @TableField("BUSINESS_TYPE")
    private String businessType;

    @ApiModelProperty(value = "付款开户行号")
    @TableField("PAYMENT_BANK_NUM")
    private String paymentBankNum;

    @ApiModelProperty(value = "付款开户行名")
    @TableField("PAYMENT_BANK_NAME")
    private String paymentBankName;

    @ApiModelProperty(value = "付款人账号")
    @TableField("PAYMENT_ACCOUNT")
    private String paymentAccount;

    @ApiModelProperty(value = "付款人名称")
    @TableField("PAYMENT_NAME")
    private String paymentName;

    @ApiModelProperty(value = "交易日期")
    @TableField("TRADE_DATE")
    private LocalDateTime tradeDate;

    @ApiModelProperty(value = "交易时间")
    @TableField("TRADE_TIME")
    private LocalDateTime tradeTime;

    @ApiModelProperty(value = "交易货币")
    @TableField("TRADE_CURRENCY")
    private String tradeCurrency;

    @ApiModelProperty(value = "交易金额")
    @TableField("TRADE_AMOUNT")
    private BigDecimal tradeAmount;

    @ApiModelProperty(value = "交易后余额")
    @TableField("TRADE_REMAINING_AMOUNT")
    private BigDecimal tradeRemainingAmount;

    @ApiModelProperty(value = "交易流水号")
    @TableField("TRADE_SERIAL_NUM")
    private String tradeSerialNum;

    @ApiModelProperty(value = "汇款行业务编号")
    @TableField("REMITTING_BANK_BUSINESS_NUM")
    private String remittingBankBusinessNum;

    @ApiModelProperty(value = "收款人行号")
    @TableField("RECEIVER_BANK_NUM")
    private String receiverBankNum;

    @ApiModelProperty(value = "收款人账户")
    @TableField("RECEIVER_ACCOUNT")
    private String receiverAccount;

    @ApiModelProperty(value = "收款人开户行名")
    @TableField("RECEIVER_BANK_NAME")
    private String receiverBankName;

    @ApiModelProperty(value = "收款人名称")
    @TableField("RECEIVER_NAME")
    private String receiverName;

    @ApiModelProperty(value = "收款行业务编号")
    @TableField("RECEIVER_BANK_BUSINESS_NUM")
    private String receiverBankBusinessNum;

    @ApiModelProperty(value = "被代理行号")
    @TableField("PROXIED_BANK_NUM")
    private String proxiedBankNum;

    @ApiModelProperty(value = "被代理账号")
    @TableField("PROXIED_ACCOUNT")
    private String proxiedAccount;

    @ApiModelProperty(value = "被代理账户名")
    @TableField("PROXIED_ACCOUNT_NAME")
    private String proxiedAccountName;

    @ApiModelProperty(value = "被代理账户开户行名")
    @TableField("PROXIED_ACCOUNT_BANK_NUM")
    private String proxiedAccountBankNum;

    @ApiModelProperty(value = "可用余额")
    @TableField("USABLE_AMOUNT")
    private BigDecimal usableAmount;

    @ApiModelProperty(value = "冻结金额")
    @TableField("FREEZE_AMOUNT")
    private BigDecimal freezeAmount;

    @ApiModelProperty(value = "透支额度")
    @TableField("OVERDRAFT_AMOUNT")
    private BigDecimal overdraftAmount;

    @ApiModelProperty(value = "可用透支额度")
    @TableField("USABLE_OVERDRAFT_AMOUNT")
    private BigDecimal usableOverdraftAmount;

    @ApiModelProperty(value = "起息日期")
    @TableField("DATE_OF_VALUE")
    private LocalDateTime dateOfValue;

    @ApiModelProperty(value = "凭证类型")
    @TableField("VOUCHER_TYPE")
    private String voucherType;

    @ApiModelProperty(value = "凭证号码")
    @TableField("VOUCHER_NUM")
    private String voucherNum;

    @ApiModelProperty(value = "汇率")
    @TableField("EXCHANGE_RATE")
    private BigDecimal exchangeRate;

    @ApiModelProperty(value = "渠道标识")
    @TableField("CHANNEL_IDENTIFICATION")
    private String channelIdentification;

    @ApiModelProperty(value = "商户号")
    @TableField("MERCHANT_NUM")
    private String merchantNum;

    @ApiModelProperty(value = "组织路径")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "扩展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    private Long version;

    @ApiModelProperty(value = "用途")
    @TableField("PURPOSE")
    String purpose;

    @ApiModelProperty(value = "记录标识号")
    @TableField("RECORD_NUM")
    String recordNum;

    @ApiModelProperty(value = "交易附言")
    @TableField("REMARK")
    String remark;


    public static final String ID = "ID";


    public static final String QUERY_ACCOUNT = "QUERY_ACCOUNT";
    public static final String QUERY_ACCOUNT_EXCEL = "查询账号";

    public static final String DEALINGS_FLAG = "DEALINGS_FLAG";
    public static final String DEALINGS_FLAG_EXCEL = "来往帐标识";

    public static final String BUSINESS_TYPE = "BUSINESS_TYPE";
    public static final String BUSINESS_TYPE_EXCEL = "业务类型";

    public static final String PAYMENT_BANK_NUM = "PAYMENT_BANK_NUM";
    public static final String PAYMENT_BANK_NUM_EXCEL = "付款人开户行号";

    public static final String PAYMENT_BANK_NAME = "PAYMENT_BANK_NAME";
    public static final String PAYMENT_BANK_NAME_EXCEL = "付款人开户行名";

    public static final String PAYMENT_ACCOUNT = "PAYMENT_ACCOUNT";
    public static final String PAYMENT_ACCOUNT_EXCEL = "付款人账号";

    public static final String PAYMENT_NAME = "PAYMENT_NAME";
    public static final String PAYMENT_NAME_EXCEL = "付款人名称";

    public static final String TRADE_DATE = "TRADE_DATE";
    public static final String TRADE_DATE_EXCEL = "交易日期";

    public static final String TRADE_TIME = "TRADE_TIME";
    public static final String TRADE_TIME_EXCEL = "交易时间";

    public static final String TRADE_CURRENCY = "TRADE_CURRENCY";
    public static final String TRADE_CURRENCY_EXCEL = "交易货币";

    public static final String TRADE_AMOUNT = "TRADE_AMOUNT";
    public static final String TRADE_AMOUNT_EXCEL = "交易金额";

    public static final String TRADE_REMAINING_AMOUNT = "TRADE_REMAINING_AMOUNT";
    public static final String TRADE_REMAINING_AMOUNT_EXCEL = "交易后余额";

    public static final String TRADE_SERIAL_NUM = "TRADE_SERIAL_NUM";
    public static final String TRADE_SERIAL_NUM_EXCEL = "交易流水号";

    public static final String REMITTING_BANK_BUSINESS_NUM = "REMITTING_BANK_BUSINESS_NUM";
    public static final String REMITTING_BANK_BUSINESS_NUM_EXCEL = "汇款行业务编号";

    public static final String RECEIVER_BANK_NUM = "RECEIVER_BANK_NUM";
    public static final String RECEIVER_BANK_NUM_EXCEL = "收款人行号";

    public static final String RECEIVER_BANK_NAME = "RECEIVER_BANK_NAME";
    public static final String RECEIVER_BANK_NAME_EXCEL = "收款人开户行名";

    public static final String RECEIVER_ACCOUNT = "RECEIVER_ACCOUNT";
    public static final String RECEIVER_ACCOUNT_EXCEL = "收款人账户";

    public static final String RECEIVER_NAME = "RECEIVER_NAME";
    public static final String RECEIVER_NAME_EXCEL = "收款人名称";

    public static final String RECEIVER_BANK_BUSINESS_NUM = "RECEIVER_BANK_BUSINESS_NUM";
    public static final String RECEIVER_BANK_BUSINESS_NUM_EXCEL = "收款行业务编号";

    public static final String PROXIED_BANK_NUM = "PROXIED_BANK_NUM";
    public static final String PROXIED_BANK_NUM_EXCEL = "被代理行号";

    public static final String PROXIED_ACCOUNT = "PROXIED_ACCOUNT";
    public static final String PROXIED_ACCOUNT_EXCEL = "被代理账号";

    public static final String PROXIED_ACCOUNT_NAME = "PROXIED_ACCOUNT_NAME";
    public static final String PROXIED_ACCOUNT_NAME_EXCEL = "被代理账户名";

    public static final String PROXIED_ACCOUNT_BANK_NUM = "PROXIED_ACCOUNT_BANK_NUM";
    public static final String PROXIED_ACCOUNT_BANK_NUM_EXCEL = "被代理账户开户行名";

    public static final String USABLE_AMOUNT = "USABLE_AMOUNT";
    public static final String USABLE_AMOUNT_EXCEL = "可用余额";

    public static final String FREEZE_AMOUNT = "FREEZE_AMOUNT";
    public static final String FREEZE_AMOUNT_EXCEL = "冻结金额";

    public static final String OVERDRAFT_AMOUNT = "OVERDRAFT_AMOUNT";
    public static final String OVERDRAFT_AMOUNT_EXCEL = "透支额度";

    public static final String USABLE_OVERDRAFT_AMOUNT = "USABLE_OVERDRAFT_AMOUNT";
    public static final String USABLE_OVERDRAFT_AMOUNT_EXCEL = "可用透支额度";

    public static final String DATE_OF_VALUE = "DATE_OF_VALUE";
    public static final String DATE_OF_VALUE_EXCEL = "起息日期";

    public static final String VOUCHER_TYPE = "VOUCHER_TYPE";
    public static final String VOUCHER_TYPE_EXCEL = "凭证类型";

    public static final String VOUCHER_NUM = "VOUCHER_NUM";
    public static final String VOUCHER_NUM_EXCEL = "凭证号码";

    public static final String EXCHANGE_RATE = "EXCHANGE_RATE";
    public static final String EXCHANGE_RATE_EXCEL = "汇率";

    public static final String CHANNEL_IDENTIFICATION = "CHANNEL_IDENTIFICATION";
    public static final String CHANNEL_IDENTIFICATION_EXCEL = "渠道标识";

    public static final String MERCHANT_NUM = "MERCHANT_NUM";
    public static final String MERCHANT_NUM_EXCEL = "商户号";

    public static final String PURPOSE = "PURPOSE";
    public static final String PURPOSE_EXCEL = "用途";

    public static final String RECORD_NUM = "RECORD_NUM";
    public static final String RECORD_NUM_EXCEL = "记录标识号";

    public static final String REMARK = "REMARK";
    public static final String REMARK_EXCEL = "交易附言";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String VERSION = "VERSION";



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
