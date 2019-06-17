package com.deloitte.platform.api.fssc.labor.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description :   LcLaborCost查询from对象
 * @Modified :
 */
@ApiModel("LcLaborCost查询表单都为选填不填为查询全部")
@Data
public class LcLaborCostQueryForm extends BaseQueryForm<LcLaborCostQueryParam>  {


    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "付款状态")
    private String payStatus;

    @ApiModelProperty(value = "归属单位ID")
    private Long unitId;

    @ApiModelProperty(value = "关联付款单Id")
    private Long payMentId;

    @ApiModelProperty(value = "归属部门ID")
    private Long deptId;

    @ApiModelProperty(value = "付款方式")
    private String paymentType;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "时间开始范围")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "时间结束范围")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "金额开始范围")
    private BigDecimal startAmount;

    @ApiModelProperty(value = "金额结束范围")
    private BigDecimal endAmount;

}
