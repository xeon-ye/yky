package com.deloitte.platform.api.fssc.travle.vo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description : TasCostInformationLine返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasCostInformationLineVo extends BaseVo {
    private static final long serialVersionUID = 1L;


    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
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
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
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

    @ApiModelProperty(value = "姓名",required = true)
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

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "单据id")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "关联费用类型")
    private String connectCostType;

    @ApiModelProperty(value = "${field.comment}")
    private Long orgId;

    @ApiModelProperty(value = "${field.comment}")
    private String orgPath;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "大类编码")
    private String mainTypeCode;

    @ApiModelProperty(value = "支出小类编码",required = true)
    private String subTypeCode;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
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
