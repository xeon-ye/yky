package com.deloitte.services.fssc.business.contract.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hjy
 * @since 2019-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CRB_ASSOC_ADVANCE_PAYMENT")
@ApiModel(value="CrbAssocAdvancePayment对象", description="")
public class CrbAssocAdvancePayment extends Model<CrbAssocAdvancePayment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("CREATE_USER_NAME")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    @Version
    private Long version;

    @ApiModelProperty(value = "单据编号")
    @TableField("DOCUMENT_NUM")
    private String documentNum;

    @ApiModelProperty(value = "支出大类ID")
    @TableField("MAIN_TYPE_ID")
    private Long mainTypeId;

    @ApiModelProperty(value = "合计金额")
    @TableField("TOTAL_SUM")
    private Double totalSum;

    @ApiModelProperty(value = "大类名称")
    @TableField("MAIN_TYPE_NAME")
    private String mainTypeName;

    @ApiModelProperty(value = "预留字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "预留字段6")
    @TableField("EXT6")
    private String ext6;

    @ApiModelProperty(value = "预留字段7")
    @TableField("EXT7")
    private String ext7;

    @ApiModelProperty(value = "预留字段8")
    @TableField("EXT8")
    private String ext8;

    @ApiModelProperty(value = "预留字段9")
    @TableField("EXT9")
    private String ext9;

    @ApiModelProperty(value = "预留字段10")
    @TableField("EXT10")
    private String ext10;

    @ApiModelProperty(value = "预留字段11")
    @TableField("EXT11")
    private String ext11;

    @ApiModelProperty(value = "预留字段12")
    @TableField("EXT12")
    private String ext12;

    @ApiModelProperty(value = "预留字段13")
    @TableField("EXT13")
    private String ext13;

    @ApiModelProperty(value = "预留字段14")
    @TableField("EXT14")
    private String ext14;

    @ApiModelProperty(value = "预留字段15")
    @TableField("EXT15")
    private String ext15;

    @ApiModelProperty(value = "支出大类编码")
    @TableField("MAIN_TYPE_CODE")
    private String mainTypeCode;

    @ApiModelProperty(value = "关联表名")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "关联ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "已核销金额")
    @TableField("HAS_VER_AMOUNT")
    private BigDecimal hasVerAmount;

    @ApiModelProperty(value = "未核销金额")
    @TableField("NO_VER_AMOUNT")
    private BigDecimal noVerAmount;

    @ApiModelProperty(value = "本次核销金额")
    @TableField("THIS_VER_AMOUNT")
    private BigDecimal thisVerAmount;

    @ApiModelProperty(value = "核销说明")
    @TableField("VER_INSTRUCTIONS")
    private String verInstructions;

    @ApiModelProperty(value = "预付金额")
    @TableField("PREPAID_AMOUNT")
    private BigDecimal prepaidAmount;

    @ApiModelProperty(value = "核销日期")
    @TableField("CANCELLATION_TIME")
    private LocalDateTime cancellationTime;

    @ApiModelProperty(value = "支出小类ID")
    @TableField("SUB_TYPE_ID")
    private Long subTypeId;

    @ApiModelProperty(value = "支出小类CODE")
    @TableField("SUB_TYPE_CODE")
    private String subTypeCode;

    @ApiModelProperty(value = "支出小类NAME")
    @TableField("SUB_TYPE_NAME")
    private String subTypeName;

    @ApiModelProperty(value = "行号")
    @TableField("LINE_NUMBER")
    private Long lineNumber;


    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String VERSION = "VERSION";

    public static final String DOCUMENT_NUM = "DOCUMENT_NUM";

    public static final String MAIN_TYPE_ID = "MAIN_TYPE_ID";

    public static final String TOTAL_SUM = "TOTAL_SUM";

    public static final String MAIN_TYPE_NAME = "MAIN_TYPE_NAME";

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

    public static final String MAIN_TYPE_CODE = "MAIN_TYPE_CODE";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String HAS_VER_AMOUNT = "HAS_VER_AMOUNT";

    public static final String NO_VER_AMOUNT = "NO_VER_AMOUNT";

    public static final String THIS_VER_AMOUNT = "THIS_VER_AMOUNT";

    public static final String VER_INSTRUCTIONS = "VER_INSTRUCTIONS";

    public static final String PREPAID_AMOUNT = "PREPAID_AMOUNT";

    public static final String CANCELLATION_TIME = "CANCELLATION_TIME";

    public static final String SUB_TYPE_ID = "SUB_TYPE_ID";

    public static final String SUB_TYPE_CODE = "SUB_TYPE_CODE";

    public static final String SUB_TYPE_NAME = "SUB_TYPE_NAME";

    public static final String LINE_NUMBER = "LINE_NUMBER";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
