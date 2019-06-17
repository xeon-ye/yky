package com.deloitte.platform.api.fssc.travle.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description : TasCostInformationLine新增修改form对象
 * @Modified :
 */
@ApiModel("新增TasCostInformationLine表单")
@Data
public class TasCostInformationLineForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "预留字段1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4,行政区域代码")
    private String ext4;

    @ApiModelProperty(value = "预留字段5,是什么补助类型，住宿，交通")
    private String ext5;

    @ApiModelProperty(value = "预留字段6")
    private String ext6;

    @ApiModelProperty(value = "预留字段7")
    private String ext7;

    @ApiModelProperty(value = "预留字段8")
    private String ext8;

    @ApiModelProperty(value = "预留字段9")
    private String ext9;

    @ApiModelProperty(value = "预留字段10")
    private String ext10;

    @ApiModelProperty(value = "预留字段11")
    private String ext11;

    @ApiModelProperty(value = "预留字段12")
    private String ext12;

    @ApiModelProperty(value = "预留字段13")
    private String ext13;

    @ApiModelProperty(value = "预留字段14")
    private String ext14;

    @ApiModelProperty(value = "预留字段15")
    private String ext15;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "工号")
    private String jobNumber;

    @ApiModelProperty(value = "级别")
    private String levelName;

    @ApiModelProperty(value = "差旅开始时间")
    private LocalDateTime travelBeginTime;

    @ApiModelProperty(value = "差旅结束时间")
    private LocalDateTime travelEndTime;

    @ApiModelProperty(value = "差旅开始地点")
    private String locationBegTravel;

    @ApiModelProperty(value = "差旅结束地点")
    private String locationEndTravel;

    @ApiModelProperty(value = "总额")
    private BigDecimal totalForehead;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "天数")
    private Long dayNum;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalSum;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;

    @ApiModelProperty(value = "单价id")
    private Long documentId;

    @ApiModelProperty(value = "单价类型")
    private String documentType;

    @ApiModelProperty(value = "关联费用类型")
    private String connectCostType;

    @ApiModelProperty(value = "${field.comment}")
    private Long orgId;

    @ApiModelProperty(value = "${field.comment}")
    private String orgPath;

    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "大类编码")
    private String mainTypeCode;

    @ApiModelProperty(value = "支出小类编码",required = true)
    private String subTypeCode;

    @ApiModelProperty(value = "支出小类ID",required = true)
    private Long subTypeId;

    @ApiModelProperty(value = "支出大类名称")
    private String mainTypeName;

    @ApiModelProperty(value = "支出小类名称",required = true)
    private String subTypeName;

    @ApiModelProperty("会计科目代码")
    private String accountCode;

    @ApiModelProperty("预算会计科目代码")
    private String budgetAccountCode;

    @ApiModelProperty(value = "发票号")
    private String invoiceNumber;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;


}
