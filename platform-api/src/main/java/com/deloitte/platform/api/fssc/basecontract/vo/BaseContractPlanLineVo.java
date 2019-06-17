package com.deloitte.platform.api.fssc.basecontract.vo;

import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description : BaseContractPlanLine返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseContractPlanLineVo extends BaseVo {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("合同id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long contractId;
    @ApiModelProperty("合同编号")
    private String contractNo;
    @ApiModelProperty("合同名称")
    private String contractName;
    @ApiModelProperty("主办部门/履行部门")
    private String org;
    @ApiModelProperty("主办人/履行人")
    private String operator;
    @ApiModelProperty("对方签约主体")
    private String sideSubjectName;
    @ApiModelProperty(value = "对方签约主体Code")
    private String sideSubjectCode;
    @ApiModelProperty("我方签约主体")
    private String ourSubjectName;
    @ApiModelProperty("合同含税金额/合同总金额")
    private String amountIncludTax;
    @ApiModelProperty("约定付款批次")
    private String appointPayStage;
    @ApiModelProperty("约定比例")
    private String agreedPropertions;
    @ApiModelProperty("约定付款金额")
    private String agreedPaymentAmount;
    @ApiModelProperty("履行计划id/合同详情id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long travelPlanId;
    @ApiModelProperty("计划付款时间")
    private LocalDateTime planPayTime;

    @ApiModelProperty(value = "合同类型")
    private String contractType;

    @ApiModelProperty(value = "约定收款批次")
    private String appointRecieveStage;

    @ApiModelProperty(value = "约定收款比例")
    private String agreedRecievePropertions;

    @ApiModelProperty(value = "约定收款金额")
    private BigDecimal agreedRecieveAmount;

    @ApiModelProperty(value = "计划收款时间")
    private LocalDateTime planRecieveTime;
    @ApiModelProperty(value = "已核销金额")
    private BigDecimal hasVerAmount;
    @ApiModelProperty(value="实际付款金额")
    private BigDecimal actualPlayAmount;
    @ApiModelProperty(value="实际付款金额/发票金额")
    private BigDecimal receipPlayAmount;

    @ApiModelProperty(value = "已支付金额")
    private BigDecimal paidAmount;

}
