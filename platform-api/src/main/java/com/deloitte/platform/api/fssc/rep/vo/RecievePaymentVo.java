package com.deloitte.platform.api.fssc.rep.vo;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
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
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description : RecievePayment返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecievePaymentVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "创建人ID申请人")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名申请人")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "收款情况收款状态")
    private String recieveStatus;

    @ApiModelProperty(value = "来源期间")
    private String sourceDuring;

    @ApiModelProperty(value = "收款方式")
    private String recievePaymentType;

    @ApiModelProperty(value = "款项类型")
    private String paymentType;

    @ApiModelProperty(value = "收入大类ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long inComeMainTypeId;

    @ApiModelProperty(value = "收入大类CODE")
    private String inComeMainTypeCode;

    @ApiModelProperty(value = "收入大类名称")
    private String inComeMainTypeName;

    @ApiModelProperty(value = "归属单位code")
    private String unitCode;

    @ApiModelProperty(value = "归属单位ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long unitId;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "管理部门code")
    private String deptCode;

    @ApiModelProperty(value = "管理部门ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long deptId;

    @ApiModelProperty(value = "管理部门名称")
    private String deptName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目code")
    private String projectCode;

    @ApiModelProperty(value = "项目承担单位id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long projectUnitId;

    @ApiModelProperty(value = "项目承担单位code")
    private String projectUnitCode;

    @ApiModelProperty(value = "项目承担单位名称")
    private String projectUnitName;

    @ApiModelProperty(value = "项目ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long projectId;

    @ApiModelProperty(value = "备注摘要")
    private String remark;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "是否需要开票")
    private String isNeedInvoice;

    @ApiModelProperty(value = "合计金额外币")
    private BigDecimal totalAmountOtherCurrency;

    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "收款信息")
    private List<RecieveLineMsgVo> recieveLineMsgVos;

//    @ApiModelProperty(value = "收入信息")
//    private List<RecieveIncomeMsgVo> recieveIncomeMsgVos;
//
//    @ApiModelProperty(value = "认领范围")
//    private List<RecieveClaimAreaVo> recieveClaimAreaVos;

    @ApiModelProperty(value = "审批历史")
    private List<BpmProcessTaskVo> bpmProcessTaskVos;

    @ApiModelProperty(value = "支持性附件数量")
    private Long attachCount;

    @ApiModelProperty(value = "收入认领状态 全部认领:ALL 部分认领:SOME 已认领:Y 未认领:N")
    private String incomeClaimStatus;

    @ApiModelProperty(value = "收入结转方式")
     private String paymentConfirmType;

    @ApiModelProperty(value = "收款总金额")
    private BigDecimal skTotal;

    @ApiModelProperty(value = "已认领金额")
    private BigDecimal incomeClaimAmount;

    @ApiModelProperty(value = "未认领金额")
    private BigDecimal noIncomeClaimAmount;

    @ApiModelProperty(value = "预留字段1 是否推送")
    private String ex1;
}
