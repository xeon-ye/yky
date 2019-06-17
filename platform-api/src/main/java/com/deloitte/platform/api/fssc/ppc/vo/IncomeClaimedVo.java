package com.deloitte.platform.api.fssc.ppc.vo;

import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class IncomeClaimedVo {

    @ApiModelProperty(value = "款项确认行id 需要保存到收款单行")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long paymentLineDetailId;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;
    @ApiModelProperty(value = "收入小类id")
    private String inComeSubTypeId;
    @ApiModelProperty(value = "项目编号")
    private String projectNum;
    @ApiModelProperty(value = "收入大类名称")
    private String inComeMainTypeName;
    @ApiModelProperty(value = "收入小类名称")
    private String inComeSubTypeName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "预计到款金额")
    private BigDecimal expectedAmount;

    @ApiModelProperty(value = "预计到款时间")
    private LocalDateTime expectedRecieveTime;

    @ApiModelProperty(value = "对方单位信息名称")
    private String adverseUnitName;

    @ApiModelProperty(value = "对方银行账号")
    private String adverseBankAccount;
}
