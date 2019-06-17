package com.deloitte.platform.api.fssc.ppc.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :   NoProjectConfirmation查询from对象
 * @Modified :
 */
@ApiModel("NoProjectConfirmation查询表单")
@Data
public class NoProjectConfirmationQueryForm extends BaseQueryForm<NoProjectConfirmationQueryParam>  {


    @ApiModelProperty(value = "创建人姓名申请人")
    private String createUserName;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "收款情况收款状态")
    private String recieveStatus;

    @ApiModelProperty(value = "款项类型")
    private String paymentType;

    @ApiModelProperty(value = "管理部门ID")
    private Long deptId;

    @ApiModelProperty(value = "收入大类ID")
    private Long inComeMainTypeId;

    @ApiModelProperty(value = "金额开始")
    private BigDecimal moneyStart;

    @ApiModelProperty(value = "金额结束")
    private BigDecimal moneyEnd;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime timeStart;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime timeEnd;
}
