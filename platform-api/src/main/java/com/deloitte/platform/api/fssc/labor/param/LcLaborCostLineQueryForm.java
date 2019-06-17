package com.deloitte.platform.api.fssc.labor.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class LcLaborCostLineQueryForm extends BaseQueryForm {

    @ApiModelProperty(value = "收款人姓名")
    private String recieveUserName;
    @ApiModelProperty(value = "是否外籍Y N")
    private String isForeign="N";
    @ApiModelProperty(value = "付款单位名称")
    private String payUnitName;
    @ApiModelProperty(value = "付款部门名称")
    private String payDeptName;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "单据编号")
    private String documentNum;
    @ApiModelProperty(value = "支付状态")
    private String payStatus;
    @ApiModelProperty(value = "开始时间范围")
    private LocalDateTime startTime;
    @ApiModelProperty(value = "结束时间范围")
    private LocalDateTime endTime;
    @ApiModelProperty(value = "金额开始范围")
    private BigDecimal startAmount;
    @ApiModelProperty(value = "金额结束范围")
    private BigDecimal endAmount;


}
