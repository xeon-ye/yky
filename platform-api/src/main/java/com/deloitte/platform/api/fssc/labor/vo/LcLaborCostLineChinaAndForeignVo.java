package com.deloitte.platform.api.fssc.labor.vo;

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
 * @Date : Create in 2019-03-25
 * @Description : LcLaborCostLineChina返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LcLaborCostLineChinaAndForeignVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "单据ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long documentId;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "收款人姓名")
    private String recieveUserName;

    @ApiModelProperty(value = "收款人ID没有可不填")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long recieveUserId;

    @ApiModelProperty(value = "单位名称")
    private String payUnitName;

    @ApiModelProperty(value = "付款部门")
    private String payDeptName;

    @ApiModelProperty(value = "证件类型")
    private String certType;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "证件号")
    private String certNum;

    @ApiModelProperty(value = "应发金额")
    private BigDecimal shouldGiveAmount;

    @ApiModelProperty(value = "扣税金额")
    private BigDecimal deductedAmount;

    @ApiModelProperty(value = "实发金额")
    private BigDecimal realGiveAmount;

    @ApiModelProperty(value = "支付状态")
    private String payStatus;

    @ApiModelProperty(value = "付款单id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long paymentId;

}
