package com.deloitte.platform.api.fssc.borrow.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description :   BorrowMoneyLine查询from对象
 * @Modified :
 */
@ApiModel("BorrowMoneyLine查询表单")
@Data
public class BorrowMoneyLineQueryForm extends BaseQueryForm<BorrowMoneyLineQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "预留字段1")
    private String ex1;

    @ApiModelProperty(value = "预留字段2")
    private String ex2;

    @ApiModelProperty(value = "预留字段3")
    private String ex3;

    @ApiModelProperty(value = "预留字段4")
    private String ex4;

    @ApiModelProperty(value = "预留字段5")
    private String ex5;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    private String mainTypeCode;

    @ApiModelProperty(value = "支出小类ID")
    private Long subTypeId;

    private String subTypeCode;

    @ApiModelProperty(value = "借款金额")
    private BigDecimal borrowAmount;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "借款单据ID")
    private Long documentId;

    @ApiModelProperty(value = "关联的表名")
    private String documentType;
    @ApiModelProperty(value = "支出大类名称")
    private String mainTypeName;
    @ApiModelProperty(value = "支出小类名称")
    private String subTypeName;
}
