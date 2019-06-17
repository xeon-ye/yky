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
 * 手工录入表行信息
 * </p>
 *
 * @author chenx
 * @since 2019-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("AV_MANUAL_VOUCHER_LINE")
@ApiModel(value="AvManualVoucherLine对象", description="手工录入表行信息")
public class AvManualVoucherLine extends Model<AvManualVoucherLine> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "凭证行号")
    @TableField("JE_LINE_NUM")
    private Long jeLineNum;

    @ApiModelProperty(value = "单据编号")
    @TableField("DOCUMENT_NUM")
    private String documentNum;

    @ApiModelProperty(value = "会计科目结构")
    @TableField("ACCOUNT_STRUCTURE")
    private String accountStructure;

    @ApiModelProperty(value = "会计科目结构编码")
    @TableField("ACCOUNT_STRUCTURE_CODE")
    private String accountStructureCode;

    @ApiModelProperty(value = "会计科目结构描述")
    @TableField("ACCOUNT_STRUCTURE_DESC")
    private String accountStructureDesc;

    @ApiModelProperty(value = "凭证类型")
    @TableField("VOUCHER_TYPE")
    private String voucherType;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("CREATE_USER_NAME")
    private String createUserName;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "凭证头ID")
    @TableField("JE_HEADER_ID")
    private Long jeHeaderId;

    @ApiModelProperty(value = "账薄ID")
    @TableField("LEDGER_ID")
    private Long ledgerId;

    @ApiModelProperty(value = "会计期间")
    @TableField("PERIOD_NAME")
    private String periodName;

    @ApiModelProperty(value = "会计日期")
    @TableField("EFFECTIVE_DATE")
    private LocalDateTime effectiveDate;

    @ApiModelProperty(value = "原币借方金额")
    @TableField("ENTERED_DR")
    private BigDecimal enteredDr;

    @ApiModelProperty(value = "原币贷方金额")
    @TableField("ENTERED_CR")
    private BigDecimal enteredCr;

    @ApiModelProperty(value = "本币借方金额")
    @TableField("ACCOUNTED_DR")
    private BigDecimal accountedDr;

    @ApiModelProperty(value = "本币贷方金额")
    @TableField("ACCOUNTED_CR")
    private BigDecimal accountedCr;

    @ApiModelProperty(value = "凭证行说明")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "行类型")
    @TableField("LINE_TYPE_CODE")
    private String lineTypeCode;

    @ApiModelProperty(value = "审批状态")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "过账状态")
    @TableField("POST_STATUS")
    private String postStatus;

    @ApiModelProperty(value = "EBS返回错误信息")
    @TableField("ERROR_MESSAGE")
    private String errorMessage;

    @ApiModelProperty(value = "科目结构1")
    @TableField("SEGMENT1")
    private String segment1;

    @ApiModelProperty(value = "科目结构2")
    @TableField("SEGMENT2")
    private String segment2;

    @ApiModelProperty(value = "科目结构3")
    @TableField("SEGMENT3")
    private String segment3;

    @ApiModelProperty(value = "科目结构4")
    @TableField("SEGMENT4")
    private String segment4;

    @ApiModelProperty(value = "科目结构5")
    @TableField("SEGMENT5")
    private String segment5;

    @ApiModelProperty(value = "科目结构6")
    @TableField("SEGMENT6")
    private String segment6;

    @ApiModelProperty(value = "科目结构7")
    @TableField("SEGMENT7")
    private String segment7;

    @ApiModelProperty(value = "科目结构8")
    @TableField("SEGMENT8")
    private String segment8;

    @ApiModelProperty(value = "科目结构9")
    @TableField("SEGMENT9")
    private String segment9;

    @ApiModelProperty(value = "科目结构10")
    @TableField("SEGMENT10")
    private String segment10;

    @ApiModelProperty(value = "科目结构11")
    @TableField("SEGMENT11")
    private String segment11;

    @ApiModelProperty(value = "科目结构12")
    @TableField("SEGMENT12")
    private String segment12;

    @ApiModelProperty(value = "科目结构13")
    @TableField("SEGMENT13")
    private String segment13;

    @ApiModelProperty(value = "科目结构14")
    @TableField("SEGMENT14")
    private String segment14;

    @ApiModelProperty(value = "科目结构15")
    @TableField("SEGMENT15")
    private String segment15;

    @ApiModelProperty(value = "科目结构16")
    @TableField("SEGMENT16")
    private String segment16;

    @ApiModelProperty(value = "科目结构17")
    @TableField("SEGMENT17")
    private String segment17;

    @ApiModelProperty(value = "科目结构18")
    @TableField("SEGMENT18")
    private String segment18;

    @ApiModelProperty(value = "科目结构19")
    @TableField("SEGMENT19")
    private String segment19;

    @ApiModelProperty(value = "科目结构20")
    @TableField("SEGMENT20")
    private String segment20;

    @ApiModelProperty(value = "扩展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "扩展字段6")
    @TableField("EXT6")
    private String ext6;

    @ApiModelProperty(value = "扩展字段7")
    @TableField("EXT7")
    private String ext7;

    @ApiModelProperty(value = "扩展字段8")
    @TableField("EXT8")
    private String ext8;

    @ApiModelProperty(value = "扩展字段9")
    @TableField("EXT9")
    private String ext9;

    @ApiModelProperty(value = "扩展字段10")
    @TableField("EXT10")
    private String ext10;

    @ApiModelProperty(value = "扩展字段11")
    @TableField("EXT11")
    private String ext11;

    @ApiModelProperty(value = "扩展字段12")
    @TableField("EXT12")
    private String ext12;

    @ApiModelProperty(value = "扩展字段13")
    @TableField("EXT13")
    private String ext13;

    @ApiModelProperty(value = "扩展字段14")
    @TableField("EXT14")
    private String ext14;

    @ApiModelProperty(value = "扩展字段15")
    @TableField("EXT15")
    private String ext15;


    public static final String ID = "ID";

    public static final String JE_LINE_NUM = "JE_LINE_NUM";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String ACCOUNT_STRUCTURE = "ACCOUNT_STRUCTURE";

    public static final String ACCOUNT_STRUCTURE_CODE = "ACCOUNT_STRUCTURE_CODE";

    public static final String ACCOUNT_STRUCTURE_DESC = "ACCOUNT_STRUCTURE_DESC";

    public static final String VOUCHER_TYPE = "VOUCHER_TYPE";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String JE_HEADER_ID = "JE_HEADER_ID";

    public static final String LEDGER = "LEDGER";

    public static final String PERIOD_NAME = "PERIOD_NAME";

    public static final String EFFECTIVE_DATE = "EFFECTIVE_DATE";

    public static final String ENTERED_DR = "ENTERED_DR";

    public static final String ENTERED_CR = "ENTERED_CR";

    public static final String ACCOUNTED_DR = "ACCOUNTED_DR";

    public static final String ACCOUNTED_CR = "ACCOUNTED_CR";

    public static final String DESCRIPTION = "DESCRIPTION";

    public static final String LINE_TYPE_CODE = "LINE_TYPE_CODE";

    public static final String STATUS = "STATUS";

    public static final String POST_STATUS = "POST_STATUS";

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

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String EXT6 = "EXT6";

    public static final String EXT7 = "EXT7";

    public static final String EXT8 = "EXT8";

    public static final String EXT9 = "EXT9";

    public static final String EXT10 = "EXT10";

    public static final String EXT11 = "EXT11";

    public static final String EXT12 = "EXT12";

    public static final String EXT13 = "EXT13";

    public static final String EXT14 = "EXT14";

    public static final String EXT15 = "EXT15";

    public static final String ERROR_MESSAGE= "ERROR_MESSAGE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
