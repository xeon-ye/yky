package com.deloitte.platform.api.fssc.trade.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : jaws
 * @Date : Create in 2019-05-13
 * @Description :  TradeInformation查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeInformationQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String queryAccount;
    private String dealingsFlag;
    private String businessType;
    private String paymentBankNum;
    private String paymentAccount;
    private String paymentName;
    private LocalDateTime tradeDate;
    private LocalDateTime tradeTime;
    private String tradeCurrency;
    private BigDecimal tradeAmount;
    private BigDecimal tradeRemainingAmount;
    private String tradeSerialNum;
    private String remittingBankBusinessNum;
    private String receiverBankNum;
    private String receiverBankName;
    private String receiverName;
    private String receiverBankBusinessNum;
    private String proxiedBankNum;
    private String proxiedAccount;
    private String proxiedAccountName;
    private String proxiedAccountBankNum;
    private BigDecimal usableAmount;
    private BigDecimal freezeAmount;
    private BigDecimal overdraftAmount;
    private BigDecimal usableOverdraftAmount;
    private LocalDateTime dateOfValue;
    private String voucherType;
    private String voucherNum;
    private BigDecimal exchangeRate;
    private String channelIdentification;
    private String merchantNum;
    private String orgPath;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private BigDecimal version;

}
