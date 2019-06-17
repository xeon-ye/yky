package com.deloitte.platform.api.fssc.labor.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description :   LcLaborCostLineForeign查询from对象
 * @Modified :
 */
@ApiModel("LcLaborCostLineForeign查询表单")
@Data
public class LcLaborCostLineForeignQueryForm extends BaseQueryForm<LcLaborCostLineForeignQueryParam>  {


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

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据ID")
    private String documentId;

    @ApiModelProperty(value = "支出小类ID")
    private Long subTypeId;

    @ApiModelProperty(value = "支出小类CODe")
    private String subTypeCode;

    @ApiModelProperty(value = "收款人姓名")
    private String recieveUserName;

    @ApiModelProperty(value = "国籍")
    private String nationality;

    @ApiModelProperty(value = "首次来华时间")
    private LocalDateTime firstInChina;

    @ApiModelProperty(value = "出生年月")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "出生地")
    private String birthAddress;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "有无境内住所")
    private String hasDomicileInChina;

    @ApiModelProperty(value = "预计离境时间")
    private LocalDateTime estimatedTimeOfDeparture;

    @ApiModelProperty(value = "任职受雇从业类型")
    private String typeOfEmployment;

    @ApiModelProperty(value = "任职受雇从业时间")
    private LocalDateTime typeOfEmploymentTime;

    @ApiModelProperty(value = "收款人ID没有可不填")
    private Long recieveUserId;

    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "单位code")
    private String unitCode;

    @ApiModelProperty(value = "单位id")
    private Long unitId;

    @ApiModelProperty(value = "职称")
    private String position;

    @ApiModelProperty(value = "证件类型")
    private String certType;

    @ApiModelProperty(value = "证件号")
    private String certNum;

    @ApiModelProperty(value = "发放标准")
    private String distributionStandard;

    @ApiModelProperty(value = "开户行")
    private String bankName;

    @ApiModelProperty(value = "银行code没有可不填")
    private String bankCode;

    @ApiModelProperty(value = "银行账号")
    private String bankAccount;

    @ApiModelProperty(value = "联行号")
    private String interBranchNumber;

    @ApiModelProperty(value = "应发金额")
    private BigDecimal shouldGiveAmount;

    @ApiModelProperty(value = "扣税金额")
    private BigDecimal deductedAmount;

    @ApiModelProperty(value = "实发金额")
    private BigDecimal realGiveAmount;

    @ApiModelProperty(value = "小类名称")
    private String subTypeName;

    @ApiModelProperty(value = "备注")
    private String remark;

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

    @ApiModelProperty(value = "预留字段6")
    private String ex6;

    @ApiModelProperty(value = "预留字段7")
    private String ex7;

    @ApiModelProperty(value = "预留字段8")
    private String ex8;

    @ApiModelProperty(value = "预留字段9")
    private String ex9;

    @ApiModelProperty(value = "预留字段10")
    private String ex10;

    @ApiModelProperty(value = "预留字段11")
    private String ex11;

    @ApiModelProperty(value = "预留字段12")
    private String ex12;

    @ApiModelProperty(value = "预留字段13")
    private String ex13;

    @ApiModelProperty(value = "预留字段14")
    private String ex14;

    @ApiModelProperty(value = "预留字段15")
    private String ex15;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;
}
