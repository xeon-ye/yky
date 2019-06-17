package com.deloitte.platform.api.fssc.engine.manual.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description :   AvManualVoucherHead查询from对象
 * @Modified :
 */
@ApiModel("AvManualVoucherHead查询表单")
@Data
public class AvManualVoucherHeadQueryForm extends BaseQueryForm<AvManualVoucherHeadQueryParam>  {


    @ApiModelProperty(value = "表头ID")
    private Long id;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "审批状态")
    private String documentStatus;

    @ApiModelProperty(value = "归属单位ID")
    private Long unitId;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

    @ApiModelProperty(value = "费率")
    private BigDecimal cost;

    @ApiModelProperty(value = "原币金额合计")
    private BigDecimal totalOriginalAmount;

    @ApiModelProperty(value = "本位币金额合计")
    private BigDecimal totalStandardAmount;

    @ApiModelProperty(value = "过账状态")
    private String postStatus;

    @ApiModelProperty(value = "支持性附件数量")
    private Integer attachCount;

    @ApiModelProperty(value = "账薄ID")
    private Long ledgerId;

    @ApiModelProperty(value = "业务类别(凭证类别)")
    private String jeCategory;

    @ApiModelProperty(value = "凭证来源")
    private String jeSource;

    @ApiModelProperty(value = "周期(月份)")
    private String periodName;

    @ApiModelProperty(value = "凭证名")
    private String name;

    @ApiModelProperty(value="凭证编码")
    private String docSeqNum;

    @ApiModelProperty(value = "冲销标志")
    private String accrualRevFlag;

    @ApiModelProperty(value = "会计日期")
    private LocalDateTime defaultEffectiveDate;

    @ApiModelProperty(value = "标记原单据ID")
    private Long fromRecurringHeaderId;

    @ApiModelProperty(value = "过账时间")
    private LocalDateTime postedDate;

    @ApiModelProperty(value = "冲销凭证头ID")
    private Long accrualRevJeHeaderId;

    @ApiModelProperty(value = "凭证头说明")
    private String description;

    @ApiModelProperty(value = "汇率")
    private BigDecimal currencyConversionRate;

    @ApiModelProperty(value = "汇率类型")
    private String currencyConversionType;

    @ApiModelProperty(value = "汇率日期")
    private LocalDateTime currencyConversionDate;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

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

    @ApiModelProperty(value="单据时间开始")
    private LocalDateTime createDateStart;

    @ApiModelProperty(value = "单据时间结束")
    private LocalDateTime createDateEnd;

    @ApiModelProperty(value = "原币金额开始")
    private BigDecimal totalOriginalAmountStart;

    @ApiModelProperty(value="原币金额结束")
    private BigDecimal totalOriginalAmountEnd;

    @ApiModelProperty(value="本币金额开始")
    private BigDecimal totalStandardAmountStart;

    @ApiModelProperty(value="本币金额结束")
    private BigDecimal totalStandardAmountEnd;

    @ApiModelProperty(value="会计日期开始")
    private LocalDateTime defaultEffectiveDateStart;

    @ApiModelProperty(value="会计日期结束")
    private LocalDateTime defaultEffectiveDateEnd;

    @ApiModelProperty(value = "结转ID")
    private String carrId;


    private List<AvManualVoucherLineQueryParam> lineList;

}
