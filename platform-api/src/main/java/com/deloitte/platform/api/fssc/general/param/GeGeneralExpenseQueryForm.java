package com.deloitte.platform.api.fssc.general.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :   GeGeneralExpense查询from对象
 * @Modified :
 */
@ApiModel("GeGeneralExpense查询表单")
@Data
public class GeGeneralExpenseQueryForm extends BaseQueryForm<GeGeneralExpenseQueryParam>  {



    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;


    @ApiModelProperty(value = "创建时间")
    private LocalDateTime timeStart;


    @ApiModelProperty(value = "创建时间")
    private LocalDateTime timeEnd;


    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "付款状态")
    private String payStatus;

    @ApiModelProperty(value = "归属单位ID")
    private Long unitId;

    @ApiModelProperty(value = "归属部门ID")
    private Long deptId;

    @ApiModelProperty(value = "付款方式")
    private String paymentType;

    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "关联事项申请ID")
    private Long applyForId;


    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "金额开始")
    private BigDecimal moneyStart;
    @ApiModelProperty(value = "金额结束")
    private BigDecimal moneyEnd;

    @ApiModelProperty(value = "关联付款单Id")
    private Long payMentId;
}
