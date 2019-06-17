package com.deloitte.platform.api.fssc.edu.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-29
 * @Description :   FundsApplyHead查询from对象
 * @Modified :
 */
@ApiModel("FundsApplyHead查询表单")
@Data
public class FundsApplyHeadQueryForm extends BaseQueryForm {


    @ApiModelProperty(value = "创建人ID申请人")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名申请人")
    private String createUserName;


    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;


    @ApiModelProperty(value = "归属单位ID")
    private Long unitId;


    @ApiModelProperty(value = "管理部门ID")
    private Long deptId;


    @ApiModelProperty(value = "申请经费")
    private String fundType;


    private BigDecimal moneyStart;

    private BigDecimal moneyEnd;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;


}
