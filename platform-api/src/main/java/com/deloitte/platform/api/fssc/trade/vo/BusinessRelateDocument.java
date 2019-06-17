package com.deloitte.platform.api.fssc.trade.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessRelateDocument implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据类型(名称)")
    private String documentType;

    @ApiModelProperty(value = "申请人(名称)")
    private String applyForPerson;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "处理状态")
    private String processStatus;

    @ApiModelProperty(value = "流水编码")
    private String serialNum;
}
