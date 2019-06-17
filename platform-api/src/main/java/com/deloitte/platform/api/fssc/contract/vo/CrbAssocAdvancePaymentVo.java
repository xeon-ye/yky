package com.deloitte.platform.api.fssc.contract.vo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-25
 * @Description : CrbAssocAdvancePayment返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrbAssocAdvancePaymentVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "ID")
    private Long id;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;


    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "单据编号",required = true)
    private String documentNum;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "支出大类ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalSum;

    @ApiModelProperty(value = "大类名称")
    private String mainTypeName;

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

    @ApiModelProperty(value = "支出大类编码")
    private String mainTypeCode;

    @ApiModelProperty(value = "关联表名")
    private String documentType;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "关联ID")
    private Long documentId;

    @ApiModelProperty(value = "已核销金额")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "本次核销金额")
    private BigDecimal thisVerAmount;

    @ApiModelProperty(value = "核销说明")
    private String verInstructions;

    @ApiModelProperty(value = "预付金额")
    private BigDecimal prepaidAmount;


    @ApiModelProperty(value = "核销日期")
    private LocalDateTime cancellationTime;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "支出小类ID")
    private Long subTypeId;

    @ApiModelProperty(value = "支出小类CODE")
    private String subTypeCode;

    @ApiModelProperty(value = "支出小类NAME")
    private String subTypeName;

    @ApiModelProperty(value = "行号",required = true)
    private Long lineNumber;

}
