package com.deloitte.services.fssc.business.bpm.vo;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FsscBpmTaskVo extends BpmProcessTaskVo {

    @ApiModelProperty(value = "单据金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

}
