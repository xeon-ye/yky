package com.deloitte.services.fssc.budget.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
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
 * 预算-事前借款行
 * </p>
 *
 * @author jaws
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BUDGET_ADVANCE_BORROW_LINE")
@ApiModel(value="BudgetAdvanceBorrowLine对象", description="预算-事前借款行")
public class BudgetAdvanceBorrowLine extends Model<BudgetAdvanceBorrowLine> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "预算事前借款ID")
    @TableId(value = "ADVANCE_BORROW_ID")
    private Long advanceBorrowId;

    @ApiModelProperty(value = "单据ID")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "单据类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "借款金额")
    @TableField("BORROW_AMOUNT")
    private BigDecimal borrowAmount;

    @ApiModelProperty(value = "支出小类ID")
    @TableField("SUB_TYPE_ID")
    private Long subTypeId;

    @ApiModelProperty(value = "预算保留金额")
    @TableField("BUDGET_REMAIN_AMOUNT")
    private BigDecimal budgetRemainAmount;

    @ApiModelProperty(value = "可用保留金额")
    @TableField("USABLE_REMAIN_AMOUNT")
    private BigDecimal usableRemainAmount;

    @ApiModelProperty(value = "已用保留金额")
    @TableField("USED_REMAIN_AMOUNT")
    private BigDecimal usedRemainAmount;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

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

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    @TableField("ORG_PATH")
    private String orgPath;

    @TableField("LINE_NUMBER")
    @ApiModelProperty(value = "行号")
    private Long lineNumber;


    public static final String ID = "ID";

    public static final String ADVANCE_BORROW_ID = "ID";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String BORROW_AMOUNT = "BORROW_AMOUNT";

    public static final String SUB_TYPE_ID = "SUB_TYPE_ID";

    public static final String BUDGET_REMAIN_AMOUNT = "BUDGET_REMAIN_AMOUNT";

    public static final String USABLE_REMAIN_AMOUNT = "USABLE_REMAIN_AMOUNT";

    public static final String USED_REMAIN_AMOUNT = "USED_REMAIN_AMOUNT";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String LINE_NUMBER = "LINE_NUMBER";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
