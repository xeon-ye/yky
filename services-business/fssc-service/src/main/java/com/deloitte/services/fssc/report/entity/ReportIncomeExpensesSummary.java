package com.deloitte.services.fssc.report.entity;

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
 * 部门收支总表（预算编制）
 * </p>
 *
 * @author jaws
 * @since 2019-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("REPORT_INCOME_EXPENSES_SUMMARY")
@ApiModel(value="ReportIncomeExpensesSummary对象", description="部门收支总表（预算编制）")
public class ReportIncomeExpensesSummary extends Model<ReportIncomeExpensesSummary> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "报表名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "年份")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "单位ID")
    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "收入_一般公共预算拨款收入")
    @TableField("INCOME_ITEM_1")
    private BigDecimal incomeItem1;

    @ApiModelProperty(value = "政府性基金预算拨款收入")
    @TableField("INCOME_ITEM_2")
    private BigDecimal incomeItem2;

    @ApiModelProperty(value = "事业收入")
    @TableField("INCOME_ITEM_3")
    private BigDecimal incomeItem3;

    @ApiModelProperty(value = "事业单位经营收入")
    @TableField("INCOME_ITEM_4")
    private BigDecimal incomeItem4;

    @ApiModelProperty(value = "其他收入")
    @TableField("INCOME_ITEM_5")
    private BigDecimal incomeItem5;

    @ApiModelProperty(value = "本年收入合计")
    @TableField("INCOME_ITEM_6")
    private BigDecimal incomeItem6;

    @ApiModelProperty(value = "用事业基金弥补收支差额")
    @TableField("INCOME_ITEM_7")
    private BigDecimal incomeItem7;

    @ApiModelProperty(value = "用事业基金弥补收支差额1")
    @TableField("INCOME_ITEM_8")
    private BigDecimal incomeItem8;

    @ApiModelProperty(value = "收入总计")
    @TableField("INCOME_ITEM_9")
    private BigDecimal incomeItem9;

    @ApiModelProperty(value = "支出-科学技术支出")
    @TableField("EXPENSE_ITEM_1")
    private BigDecimal expenseItem1;

    @ApiModelProperty(value = "支出-住房保障支出")
    @TableField("EXPENSE_ITEM_2")
    private BigDecimal expenseItem2;

    @ApiModelProperty(value = "支出-本年支出合计")
    @TableField("EXPENSE_ITEM_3")
    private BigDecimal expenseItem3;

    @ApiModelProperty(value = "支出-结转下年")
    @TableField("EXPENSE_ITEM_4")
    private BigDecimal expenseItem4;

    @ApiModelProperty(value = "支出总计")
    @TableField("EXPENSE_ITEM_5")
    private BigDecimal expenseItem5;

    @ApiModelProperty(value = "报表状态")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "周期类型(Y:年度，M:月)")
    @TableField("PERIOD_TYPE")
    private String periodType;

    @ApiModelProperty(value = "属性1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "属性2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "属性3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "属性4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "属性5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "月份")
    @TableField("MONTH")
    private String month;

    @ApiModelProperty(value = "是否多个单位合并")
    @TableField("MERGE_FLAG")
    private String mergeFlag;


    public static final String ID = "ID";

    public static final String NAME = "NAME";

    public static final String YEAR = "YEAR";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String INCOME_ITEM_1 = "INCOME_ITEM_1";

    public static final String INCOME_ITEM_2 = "INCOME_ITEM_2";

    public static final String INCOME_ITEM_3 = "INCOME_ITEM_3";

    public static final String INCOME_ITEM_4 = "INCOME_ITEM_4";

    public static final String INCOME_ITEM_5 = "INCOME_ITEM_5";

    public static final String INCOME_ITEM_6 = "INCOME_ITEM_6";

    public static final String INCOME_ITEM_7 = "INCOME_ITEM_7";

    public static final String INCOME_ITEM_8 = "INCOME_ITEM_8";

    public static final String INCOME_ITEM_9 = "INCOME_ITEM_9";

    public static final String EXPENSE_ITEM_1 = "EXPENSE_ITEM_1";

    public static final String EXPENSE_ITEM_2 = "EXPENSE_ITEM_2";

    public static final String EXPENSE_ITEM_3 = "EXPENSE_ITEM_3";

    public static final String EXPENSE_ITEM_4 = "EXPENSE_ITEM_4";

    public static final String EXPENSE_ITEM_5 = "EXPENSE_ITEM_5";

    public static final String STATUS = "STATUS";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String PERIOD_TYPE = "PERIOD_TYPE";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String MONTH = "MONTH";

    public static final String MERGE_FLAG = "MERGE_FLAG";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
