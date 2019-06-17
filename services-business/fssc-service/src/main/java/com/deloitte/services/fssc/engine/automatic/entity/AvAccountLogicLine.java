package com.deloitte.services.fssc.engine.automatic.entity;

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

/**
 * <p>
 * 凭证逻辑凭证行信息
 * </p>
 *
 * @author chenx
 * @since 2019-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("AV_ACCOUNT_LOGIC_LINE")
@ApiModel(value="AvAccountLogicLine对象", description="凭证逻辑凭证行信息")
public class AvAccountLogicLine extends Model<AvAccountLogicLine> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "判断条件")
    @TableField("JUDGE_CONDITION")
    private String judgeCondition;

    @ApiModelProperty(value = "凭证逻辑ID")
    @TableField("LOGIC_ID")
    private Long logicId;

    @ApiModelProperty(value = "借贷")
    @TableField("DEBIT_CREDIT_FROM")
    private String debitCreditFrom;

    @ApiModelProperty(value = "分录类型")
    @TableField("ENTRY_TYPE_FROM")
    private String entryTypeFrom;

    @ApiModelProperty(value = "行说明")
    @TableField("ROW_COMMENT_FROM")
    private String rowCommentFrom;

    @ApiModelProperty(value = "金额")
    @TableField("MONEY_FROM")
    private String moneyFrom;

    @ApiModelProperty(value = "COA段1")
    @TableField("SEGMENT1")
    private String segment1;

    @ApiModelProperty(value = "COA段2")
    @TableField("SEGMENT2")
    private String segment2;

    @ApiModelProperty(value = "COA段3")
    @TableField("SEGMENT3")
    private String segment3;

    @ApiModelProperty(value = "COA段4")
    @TableField("SEGMENT4")
    private String segment4;

    @ApiModelProperty(value = "COA段5")
    @TableField("SEGMENT5")
    private String segment5;

    @ApiModelProperty(value = "COA段6")
    @TableField("SEGMENT6")
    private String segment6;

    @ApiModelProperty(value = "COA段7")
    @TableField("SEGMENT7")
    private String segment7;

    @ApiModelProperty(value = "COA段8")
    @TableField("SEGMENT8")
    private String segment8;

    @ApiModelProperty(value = "COA段9")
    @TableField("SEGMENT9")
    private String segment9;

    @ApiModelProperty(value = "COA段10")
    @TableField("SEGMENT10")
    private String segment10;

    @ApiModelProperty(value = "COA段11")
    @TableField("SEGMENT11")
    private String segment11;

    @ApiModelProperty(value = "COA段12")
    @TableField("SEGMENT12")
    private String segment12;

    @ApiModelProperty(value = "COA段13")
    @TableField("SEGMENT13")
    private String segment13;

    @ApiModelProperty(value = "COA段14")
    @TableField("SEGMENT14")
    private String segment14;

    @ApiModelProperty(value = "COA段15")
    @TableField("SEGMENT15")
    private String segment15;

    @ApiModelProperty(value = "COA段16")
    @TableField("SEGMENT16")
    private String segment16;

    @ApiModelProperty(value = "COA段17")
    @TableField("SEGMENT17")
    private String segment17;

    @ApiModelProperty(value = "COA段18")
    @TableField("SEGMENT18")
    private String segment18;

    @ApiModelProperty(value = "COA段19")
    @TableField("SEGMENT19")
    private String segment19;

    @ApiModelProperty(value = "COA段20")
    @TableField("SEGMENT20")
    private String segment20;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_DATE")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_DATE")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "预留属性1")
    @TableField("EXT_1")
    private String ext1;

    @ApiModelProperty(value = "预留属性2")
    @TableField("EXT_2")
    private String ext2;

    @ApiModelProperty(value = "预留属性3")
    @TableField("EXT_3")
    private String ext3;

    @ApiModelProperty(value = "预留属性4")
    @TableField("EXT_4")
    private String ext4;

    @ApiModelProperty(value = "预留属性5")
    @TableField("EXT_5")
    private String ext5;

    @ApiModelProperty(value = "和主表映射关系Id")
    @TableField("AV_DIC_ID")
    private String avDicId;


    public static final String ID = "ID";

    public static final String JUDGE_CONDITION = "JUDGE_CONDITION";

    public static final String LOGIC_ID = "LOGIC_ID";

    public static final String DEBIT_CREDIT_FROM = "DEBIT_CREDIT_FROM";

    public static final String ENTRY_TYPE_FROM = "ENTRY_TYPE_FROM";

    public static final String ROW_COMMENT_FROM = "ROW_COMMENT_FROM";

    public static final String MONEY_FROM = "MONEY_FROM";

    public static final String SEGMENT1 = "SEGMENT1";

    public static final String SEGMENT2 = "SEGMENT2";

    public static final String SEGMENT3 = "SEGMENT3";

    public static final String SEGMENT4 = "SEGMENT4";

    public static final String SEGMENT5 = "SEGMENT5";

    public static final String SEGMENT6 = "SEGMENT6";

    public static final String SEGMENT7 = "SEGMENT7";

    public static final String SEGMENT8 = "SEGMENT8";

    public static final String SEGMENT9 = "SEGMENT9";

    public static final String SEGMENT10 = "SEGMENT10";

    public static final String SEGMENT11 = "SEGMENT11";

    public static final String SEGMENT12 = "SEGMENT12";

    public static final String SEGMENT13 = "SEGMENT13";

    public static final String SEGMENT14 = "SEGMENT14";

    public static final String SEGMENT15 = "SEGMENT15";

    public static final String SEGMENT16 = "SEGMENT16";

    public static final String SEGMENT17 = "SEGMENT17";

    public static final String SEGMENT18 = "SEGMENT18";

    public static final String SEGMENT19 = "SEGMENT19";

    public static final String SEGMENT20 = "SEGMENT20";

    public static final String CREATE_DATE = "CREATE_DATE";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_DATE = "UPDATE_DATE";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT_1 = "EXT_1";

    public static final String EXT_2 = "EXT_2";

    public static final String EXT_3 = "EXT_3";

    public static final String EXT_4 = "EXT_4";

    public static final String EXT_5 = "EXT_5";

    public static final String AV_DIC_ID ="AV_DIC_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
