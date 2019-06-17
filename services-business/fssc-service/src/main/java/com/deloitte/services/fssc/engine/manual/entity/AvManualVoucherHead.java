package com.deloitte.services.fssc.engine.manual.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * <p>
 * 手工录入凭证表头信息表
 * </p>
 *
 * @author chenx
 * @since 2019-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("AV_MANUAL_VOUCHER_HEAD")
@ApiModel(value="AvManualVoucherHead对象", description="手工录入凭证表头信息表")
public class AvManualVoucherHead extends Model<AvManualVoucherHead> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表头ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单据编号")
    @TableField("DOCUMENT_NUM")
    private String documentNum;

    @ApiModelProperty(value = "审批状态")
    @TableField("DOCUMENT_STATUS")
    private String documentStatus;

    @ApiModelProperty(value = "归属单位ID")

    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "归属单位名称")
    @TableField("UNIT_NAME")
    private String unitName;

    @ApiModelProperty(value = "币种")
    @TableField("CURRENCY_CODE")
    private String currencyCode;

    @ApiModelProperty(value = "费率")
    @TableField("COST")
    private BigDecimal cost;

    @ApiModelProperty(value = "原币金额合计")
    @TableField("TOTAL_ORIGINAL_AMOUNT")
    private BigDecimal totalOriginalAmount;

    @ApiModelProperty(value = "本位币金额合计")
    @TableField("TOTAL_STANDARD_AMOUNT")
    private BigDecimal totalStandardAmount;

    @ApiModelProperty(value = "过账状态")
    @TableField("POST_STATUS")
    private String postStatus;

    @ApiModelProperty(value = "支持性附件数量")
    @TableField("ATTACH_COUNT")
    private Integer attachCount;

    @ApiModelProperty(value = "账薄ID")
    @TableField("LEDGER_ID")
    private Long ledgerId;

    @ApiModelProperty(value = "业务类别(凭证类别)")
    @TableField("JE_CATEGORY")
    private String jeCategory;

    @ApiModelProperty(value = "凭证来源")
    @TableField("JE_SOURCE")
    private String jeSource;

    @ApiModelProperty(value = "周期(月份)")
    @TableField("PERIOD_NAME")
    private String periodName;

    @ApiModelProperty(value = "凭证名")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value="凭证编码")
    @TableField("DOC_SEQ_NUM")
    private String docSeqNum;

    @ApiModelProperty(value = "冲销标志")
    @TableField("ACCRUAL_REV_FLAG")
    private String accrualRevFlag;

    @ApiModelProperty(value = "会计日期")
    @TableField("DEFAULT_EFFECTIVE_DATE")
    private LocalDateTime defaultEffectiveDate;

    @ApiModelProperty(value = "标记原单据ID")
    @TableField("FROM_RECURRING_HEADER_ID")
    private Long fromRecurringHeaderId;

    @ApiModelProperty(value = "过账时间")
    @TableField("POSTED_DATE")
    private LocalDateTime postedDate;

    @ApiModelProperty(value = "冲销凭证头ID")
    @TableField("ACCRUAL_REV_JE_HEADER_ID")
    private Long accrualRevJeHeaderId;

    @ApiModelProperty(value = "凭证头说明")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "汇率")
    @TableField("CURRENCY_CONVERSION_RATE")
    private BigDecimal currencyConversionRate;

    @ApiModelProperty(value = "汇率类型")
    @TableField("CURRENCY_CONVERSION_TYPE")
    private String currencyConversionType;

    @ApiModelProperty(value = "汇率日期")
    @TableField("CURRENCY_CONVERSION_DATE")
    private LocalDateTime currencyConversionDate;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("CREATE_USER_NAME")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "预留字段1")
    @TableField("EX1")
    private String ex1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("EX2")
    private String ex2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("EX3")
    private String ex3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("EX4")
    private String ex4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("EX5")
    private String ex5;

    @ApiModelProperty(value = "预留字段6")
    @TableField("EX6")
    private String ex6;

    @ApiModelProperty(value = "预留字段7")
    @TableField("EX7")
    private String ex7;

    @ApiModelProperty(value = "预留字段8")
    @TableField("EX8")
    private String ex8;

    @ApiModelProperty(value = "预留字段9")
    @TableField("EX9")
    private String ex9;

    @ApiModelProperty(value = "预留字段10")
    @TableField("EX10")
    private String ex10;

    @ApiModelProperty(value = "预留字段11")
    @TableField("EX11")
    private String ex11;

    @ApiModelProperty(value = "预留字段12")
    @TableField("EX12")
    private String ex12;

    @ApiModelProperty(value = "预留字段13")
    @TableField("EX13")
    private String ex13;

    @ApiModelProperty(value = "预留字段14")
    @TableField("EX14")
    private String ex14;

    @ApiModelProperty(value = "预留字段15")
    @TableField("EX15")
    private String ex15;

    @ApiModelProperty(value = "会计凭证来源数据")
    @TableField(value = "DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "会计凭证来源数据Id")
    @TableField(value = "ACCOUNT_RESOURCE_TYPE_ID")
    private Long accountResourceTypeId;

    @ApiModelProperty(value = "会计凭证（Y/N(Y代表正式凭证，N是预制凭证)）")
    @TableField(value = "ACCOUNT_STATUS")
    private String accountStatus;

    @ApiModelProperty(value = "结转ID")
    @TableField(value = "CARRY_ID")
    private Long carryId;
    @ApiModelProperty(value = "复核人ID")
    @TableField(value = "APPROVED_BY")
    private Long approvedBy;

    @ApiModelProperty(value = "记账人ID")
    @TableField(value = "POSTED_BY")
    private Long postedBy;

    @ApiModelProperty(value = "复核人姓名")
    @TableField(value = "APPROVED_USER_NAME")
    private String approvedUserName;

    @ApiModelProperty(value = "记账人姓名")
    @TableField(value = "POSTED_USER_NAME")
    private String postedUserName;

    @ApiModelProperty(value = "是否收入结转")
    @TableField(value = "CARRAY_STATUS")
    private String carrayStatus;

    @ApiModelProperty(value = "请求头ID")
    @TableField(value = "REQUEST_ID")
    private Long requestId;

    public static final String CARRAY_STATUS = "CARRAY_STATUS";
    public static final String APPROVED_BY ="APPROVED_BY";
    public static final String POSTED_BY ="POSTED_BY";
    public static final String APPROVED_USER_NAME ="APPROVED_USER_NAME";
    public static final String POSTED_USER_NAME ="POSTED_USER_NAME";
    public static final String REQUEST_ID = "REQUEST_ID";
    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";
    public static final String ACCOUNT_RESOURCE_TYPE_ID = "ACCOUNT_RESOURCE_TYPE_ID";
    public static final String ACCOUNT_STATUS = "ACCOUNT_STATUS";
    public static final String ID = "ID";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String DOCUMENT_STATUS = "DOCUMENT_STATUS";

    public static final String DOC_SEQ_NUM = "DOC_SEQ_NUM";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String UNIT_NAME = "UNIT_NAME";

    public static final String CURRENCY_CODE = "CURRENCY_CODE";

    public static final String COST = "COST";

    public static final String TOTAL_ORIGINAL_AMOUNT = "TOTAL_ORIGINAL_AMOUNT";

    public static final String TOTAL_STANDARD_AMOUNT = "TOTAL_STANDARD_AMOUNT";

    public static final String POST_STATUS = "POST_STATUS";

    public static final String ATTACH_COUNT = "ATTACH_COUNT";

    public static final String LEDGER_ID = "LEDGER_ID";

    public static final String JE_CATEGORY = "JE_CATEGORY";

    public static final String JE_SOURCE = "JE_SOURCE";

    public static final String PERIOD_NAME = "PERIOD_NAME";

    public static final String NAME = "NAME";

    public static final String ACCRUAL_REV_FLAG = "ACCRUAL_REV_FLAG";

    public static final String DEFAULT_EFFECTIVE_DATE = "DEFAULT_EFFECTIVE_DATE";

    public static final String FROM_RECURRING_HEADER_ID = "FROM_RECURRING_HEADER_ID";

    public static final String POSTED_DATE = "POSTED_DATE";

    public static final String ACCRUAL_REV_JE_HEADER_ID = "ACCRUAL_REV_JE_HEADER_ID";

    public static final String DESCRIPTION = "DESCRIPTION";

    public static final String CURRENCY_CONVERSION_RATE = "CURRENCY_CONVERSION_RATE";

    public static final String CURRENCY_CONVERSION_TYPE = "CURRENCY_CONVERSION_TYPE";

    public static final String CURRENCY_CONVERSION_DATE = "CURRENCY_CONVERSION_DATE";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EX1 = "EX1";

    public static final String EX2 = "EX2";

    public static final String EX3 = "EX3";

    public static final String EX4 = "EX4";

    public static final String EX5 = "EX5";

    public static final String EX6 = "EX6";

    public static final String EX7 = "EX7";

    public static final String EX8 = "EX8";

    public static final String EX9 = "EX9";

    public static final String EX10 = "EX10";

    public static final String EX11 = "EX11";

    public static final String EX12 = "EX12";

    public static final String EX13 = "EX13";

    public static final String EX14 = "EX14";

    public static final String EX15 = "EX15";

    public static final String CARRY_ID = "CARRY_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
