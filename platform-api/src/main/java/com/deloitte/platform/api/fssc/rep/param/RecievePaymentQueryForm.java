package com.deloitte.platform.api.fssc.rep.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description :   RecievePayment查询from对象
 * @Modified :
 */
@ApiModel("RecievePayment查询表单")
@Data
public class RecievePaymentQueryForm extends BaseQueryForm<RecievePaymentQueryParam>  {



    @ApiModelProperty(value = "创建人姓名申请人")
    private String createUserName;

    @ApiModelProperty(value = "收入认领状态 全部认领:ALL 部分认领:SOME 已认领:Y 未认领:N")
    private String incomeClaimStatus;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "收款情况收款状态")
    private String recieveStatus;


    @ApiModelProperty(value = "收款方式")
    private String recievePaymentType;

    @ApiModelProperty(value = "款项类型")
    private String paymentType;

    @ApiModelProperty(value = "收入大类ID")
    private Long inComeMainTypeId;

    @ApiModelProperty(value = "收入大类CODE")
    private String inComeMainTypeCode;

    @ApiModelProperty(value = "收入大类名称")
    private String inComeMainTypeName;



    @ApiModelProperty(value = "归属单位ID")
    private Long unitId;


    @ApiModelProperty(value = "管理部门ID")
    private Long deptId;


    @ApiModelProperty(value = "项目ID")
    private Long projectId;


    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalAmount;


    @ApiModelProperty(value = "是否事后补单")
    private String isAfterPatch;

    @ApiModelProperty(value = "金额开始范围")
    private BigDecimal moneyStart;

    @ApiModelProperty(value = "金额结束范围")
    private BigDecimal moneyEnd;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime timeStart;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime timeEnd;

    @ApiModelProperty(value = "单据状态")
    private String ex1;
}
