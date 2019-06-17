package com.deloitte.services.fssc.report.entity;

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
 * 院校部门预算执行进度统计表
 * </p>
 *
 * @author jaws
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("REPORT_DEPT_BUDGET_DO_STATIS")
@ApiModel(value="ReportDeptBudgetDoStatis对象", description="院校部门预算执行进度统计表")
public class ReportDeptBudgetDoStatis extends Model<ReportDeptBudgetDoStatis> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单位ID")
    @TableField("UNIT_ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    @TableField("UNIT_CODE")
    private String unitCode;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "年份")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "月份")
    @TableField("MONTH")
    private String month;

    @ApiModelProperty(value = "报表状态")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "周期类型(Y:年度，M:月)")
    @TableField("PERIOD_TYPE")
    private String periodType;

    @ApiModelProperty(value = "是否多个单位合并")
    @TableField("MERGE_FLAG")
    private String mergeFlag;

    @ApiModelProperty(value = "年度预算-输血研究所")
    @TableField("ANNUAL_BUDGET_ITEM_1")
    private Double annualBudgetItem1;

    @ApiModelProperty(value = "年度预算-阜外心血管病医院")
    @TableField("ANNUAL_BUDGET_ITEM_2")
    private Double annualBudgetItem2;

    @ApiModelProperty(value = "年度预算-医放射医学研究所")
    @TableField("ANNUAL_BUDGET_ITEM_3")
    private Double annualBudgetItem3;

    @ApiModelProperty(value = "年度预算-血液学研究所血液病医院")
    @TableField("ANNUAL_BUDGET_ITEM_4")
    private Double annualBudgetItem4;

    @ApiModelProperty(value = "年度预算-药用植物研究所")
    @TableField("ANNUAL_BUDGET_ITEM_5")
    private Double annualBudgetItem5;

    @ApiModelProperty(value = "年度预算-北京协和医院")
    @TableField("ANNUAL_BUDGET_ITEM_6")
    private Double annualBudgetItem6;

    @ApiModelProperty(value = "年度预算-医生物医学工程研究所")
    @TableField("ANNUAL_BUDGET_ITEM_7")
    private Double annualBudgetItem7;

    @ApiModelProperty(value = "年度预算-实验动物研究所")
    @TableField("ANNUAL_BUDGET_ITEM_8")
    private Double annualBudgetItem8;

    @ApiModelProperty(value = "年度预算-医学生物学研究所")
    @TableField("ANNUAL_BUDGET_ITEM_9")
    private Double annualBudgetItem9;

    @ApiModelProperty(value = "年度预算-药物研究所")
    @TableField("ANNUAL_BUDGET_ITEM_10")
    private Double annualBudgetItem10;

    @ApiModelProperty(value = "年度预算-北京协和医学院")
    @TableField("ANNUAL_BUDGET_ITEM_11")
    private Double annualBudgetItem11;

    @ApiModelProperty(value = "年度预算-医药生物技术研究所")
    @TableField("ANNUAL_BUDGET_ITEM_12")
    private Double annualBudgetItem12;

    @ApiModelProperty(value = "年度预算-皮肤病医院")
    @TableField("ANNUAL_BUDGET_ITEM_13")
    private Double annualBudgetItem13;

    @ApiModelProperty(value = "年度预算-医学信息研究所")
    @TableField("ANNUAL_BUDGET_ITEM_14")
    private Double annualBudgetItem14;

    @ApiModelProperty(value = "年度预算-肿瘤医院")
    @TableField("ANNUAL_BUDGET_ITEM_15")
    private Double annualBudgetItem15;

    @ApiModelProperty(value = "年度预算-基础医学研究所")
    @TableField("ANNUAL_BUDGET_ITEM_16")
    private Double annualBudgetItem16;

    @ApiModelProperty(value = "年度预算-整形外科医院")
    @TableField("ANNUAL_BUDGET_ITEM_17")
    private Double annualBudgetItem17;

    @ApiModelProperty(value = "年度预算-医科院本级")
    @TableField("ANNUAL_BUDGET_ITEM_18")
    private Double annualBudgetItem18;

    @ApiModelProperty(value = "年度预算-病原生物学研究所")
    @TableField("ANNUAL_BUDGET_ITEM_19")
    private Double annualBudgetItem19;

    @ApiModelProperty(value = "年度预算-合计")
    @TableField("ANNUAL_BUDGET_ITEM_20")
    private Double annualBudgetItem20;

    @ApiModelProperty(value = "支出金额-输血研究所")
    @TableField("EXPENSE_AMOUNT_ITEM_1")
    private Double expenseAmountItem1;

    @ApiModelProperty(value = "支出金额--阜外心血管病医院")
    @TableField("EXPENSE_AMOUNT_ITEM_2")
    private Double expenseAmountItem2;

    @ApiModelProperty(value = "支出金额--医放射医学研究所")
    @TableField("EXPENSE_AMOUNT_ITEM_3")
    private Double expenseAmountItem3;

    @ApiModelProperty(value = "支出金额--血液学研究所血液病医院")
    @TableField("EXPENSE_AMOUNT_ITEM_4")
    private Double expenseAmountItem4;

    @ApiModelProperty(value = "支出金额--药用植物研究所")
    @TableField("EXPENSE_AMOUNT_ITEM_5")
    private Double expenseAmountItem5;

    @ApiModelProperty(value = "支出金额--北京协和医院")
    @TableField("EXPENSE_AMOUNT_ITEM_6")
    private Double expenseAmountItem6;

    @ApiModelProperty(value = "支出金额--医生物医学工程研究所")
    @TableField("EXPENSE_AMOUNT_ITEM_7")
    private Double expenseAmountItem7;

    @ApiModelProperty(value = "支出金额--实验动物研究所")
    @TableField("EXPENSE_AMOUNT_ITEM_8")
    private Double expenseAmountItem8;

    @ApiModelProperty(value = "支出金额--医学生物学研究所")
    @TableField("EXPENSE_AMOUNT_ITEM_9")
    private Double expenseAmountItem9;

    @ApiModelProperty(value = "支出金额--药物研究所")
    @TableField("EXPENSE_AMOUNT_ITEM_10")
    private Double expenseAmountItem10;

    @ApiModelProperty(value = "支出金额--北京协和医学院")
    @TableField("EXPENSE_AMOUNT_ITEM_11")
    private Double expenseAmountItem11;

    @ApiModelProperty(value = "支出金额--医药生物技术研究所")
    @TableField("EXPENSE_AMOUNT_ITEM_12")
    private Double expenseAmountItem12;

    @ApiModelProperty(value = "支出金额--皮肤病医院")
    @TableField("EXPENSE_AMOUNT_ITEM_13")
    private Double expenseAmountItem13;

    @ApiModelProperty(value = "支出金额--医学信息研究所")
    @TableField("EXPENSE_AMOUNT_ITEM_14")
    private Double expenseAmountItem14;

    @ApiModelProperty(value = "支出金额--肿瘤医院")
    @TableField("EXPENSE_AMOUNT_ITEM_15")
    private Double expenseAmountItem15;

    @ApiModelProperty(value = "支出金额--基础医学研究所")
    @TableField("EXPENSE_AMOUNT_ITEM_16")
    private Double expenseAmountItem16;

    @ApiModelProperty(value = "支出金额--整形外科医院")
    @TableField("EXPENSE_AMOUNT_ITEM_17")
    private Double expenseAmountItem17;

    @ApiModelProperty(value = "支出金额--医科院本级")
    @TableField("EXPENSE_AMOUNT_ITEM_18")
    private Double expenseAmountItem18;

    @ApiModelProperty(value = "支出金额--病原生物学研究所")
    @TableField("EXPENSE_AMOUNT_ITEM_19")
    private Double expenseAmountItem19;

    @ApiModelProperty(value = "支出金额-合计")
    @TableField("EXPENSE_AMOUNT_ITEM_20")
    private Double expenseAmountItem20;

    @ApiModelProperty(value = "执行率--输血研究所")
    @TableField("RPOCESS_RATE_ITEM_1")
    private Double rpocessRateItem1;

    @ApiModelProperty(value = "执行率--阜外心血管病医院")
    @TableField("RPOCESS_RATE_ITEM_2")
    private Double rpocessRateItem2;

    @ApiModelProperty(value = "执行率--医放射医学研究所")
    @TableField("RPOCESS_RATE_ITEM_3")
    private Double rpocessRateItem3;

    @ApiModelProperty(value = "执行率--血液学研究所血液病医院")
    @TableField("RPOCESS_RATE_ITEM_4")
    private Double rpocessRateItem4;

    @ApiModelProperty(value = "执行率--药用植物研究所")
    @TableField("RPOCESS_RATE_ITEM_5")
    private Double rpocessRateItem5;

    @ApiModelProperty(value = "执行率--北京协和医院")
    @TableField("RPOCESS_RATE_ITEM_6")
    private Double rpocessRateItem6;

    @ApiModelProperty(value = "执行率--医生物医学工程研究所")
    @TableField("RPOCESS_RATE_ITEM_7")
    private Double rpocessRateItem7;

    @ApiModelProperty(value = "执行率--实验动物研究所")
    @TableField("RPOCESS_RATE_ITEM_8")
    private Double rpocessRateItem8;

    @ApiModelProperty(value = "执行率--医学生物学研究所")
    @TableField("RPOCESS_RATE_ITEM_9")
    private Double rpocessRateItem9;

    @ApiModelProperty(value = "执行率--药物研究所")
    @TableField("RPOCESS_RATE_ITEM_10")
    private Double rpocessRateItem10;

    @ApiModelProperty(value = "执行率--北京协和医学院")
    @TableField("RPOCESS_RATE_ITEM_11")
    private Double rpocessRateItem11;

    @ApiModelProperty(value = "执行率--医药生物技术研究所")
    @TableField("RPOCESS_RATE_ITEM_12")
    private Double rpocessRateItem12;

    @ApiModelProperty(value = "执行率--皮肤病医院")
    @TableField("RPOCESS_RATE_ITEM_13")
    private Double rpocessRateItem13;

    @ApiModelProperty(value = "执行率--医学信息研究所")
    @TableField("RPOCESS_RATE_ITEM_14")
    private Double rpocessRateItem14;

    @ApiModelProperty(value = "执行率--肿瘤医院")
    @TableField("RPOCESS_RATE_ITEM_15")
    private Double rpocessRateItem15;

    @ApiModelProperty(value = "执行率--基础医学研究所")
    @TableField("RPOCESS_RATE_ITEM_16")
    private Double rpocessRateItem16;

    @ApiModelProperty(value = "执行率--整形外科医院")
    @TableField("RPOCESS_RATE_ITEM_17")
    private Double rpocessRateItem17;

    @ApiModelProperty(value = "执行率--医科院本级")
    @TableField("RPOCESS_RATE_ITEM_18")
    private Double rpocessRateItem18;

    @ApiModelProperty(value = "执行率--病原生物学研究所")
    @TableField("RPOCESS_RATE_ITEM_19")
    private Double rpocessRateItem19;

    @ApiModelProperty(value = "执行率-合计")
    @TableField("RPOCESS_RATE_ITEM_20")
    private Double rpocessRateItem20;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

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


    public static final String ID = "ID";

    public static final String UNIT_ID = "UNIT_ID";

    public static final String UNIT_CODE = "UNIT_CODE";

    public static final String NAME = "NAME";

    public static final String YEAR = "YEAR";

    public static final String MONTH = "MONTH";

    public static final String STATUS = "STATUS";

    public static final String PERIOD_TYPE = "PERIOD_TYPE";

    public static final String MERGE_FLAG = "MERGE_FLAG";

    public static final String ANNUAL_BUDGET_ITEM_1 = "ANNUAL_BUDGET_ITEM_1";

    public static final String ANNUAL_BUDGET_ITEM_2 = "ANNUAL_BUDGET_ITEM_2";

    public static final String ANNUAL_BUDGET_ITEM_3 = "ANNUAL_BUDGET_ITEM_3";

    public static final String ANNUAL_BUDGET_ITEM_4 = "ANNUAL_BUDGET_ITEM_4";

    public static final String ANNUAL_BUDGET_ITEM_5 = "ANNUAL_BUDGET_ITEM_5";

    public static final String ANNUAL_BUDGET_ITEM_6 = "ANNUAL_BUDGET_ITEM_6";

    public static final String ANNUAL_BUDGET_ITEM_7 = "ANNUAL_BUDGET_ITEM_7";

    public static final String ANNUAL_BUDGET_ITEM_8 = "ANNUAL_BUDGET_ITEM_8";

    public static final String ANNUAL_BUDGET_ITEM_9 = "ANNUAL_BUDGET_ITEM_9";

    public static final String ANNUAL_BUDGET_ITEM_10 = "ANNUAL_BUDGET_ITEM_10";

    public static final String ANNUAL_BUDGET_ITEM_11 = "ANNUAL_BUDGET_ITEM_11";

    public static final String ANNUAL_BUDGET_ITEM_12 = "ANNUAL_BUDGET_ITEM_12";

    public static final String ANNUAL_BUDGET_ITEM_13 = "ANNUAL_BUDGET_ITEM_13";

    public static final String ANNUAL_BUDGET_ITEM_14 = "ANNUAL_BUDGET_ITEM_14";

    public static final String ANNUAL_BUDGET_ITEM_15 = "ANNUAL_BUDGET_ITEM_15";

    public static final String ANNUAL_BUDGET_ITEM_16 = "ANNUAL_BUDGET_ITEM_16";

    public static final String ANNUAL_BUDGET_ITEM_17 = "ANNUAL_BUDGET_ITEM_17";

    public static final String ANNUAL_BUDGET_ITEM_18 = "ANNUAL_BUDGET_ITEM_18";

    public static final String ANNUAL_BUDGET_ITEM_19 = "ANNUAL_BUDGET_ITEM_19";

    public static final String ANNUAL_BUDGET_ITEM_20 = "ANNUAL_BUDGET_ITEM_20";

    public static final String EXPENSE_AMOUNT_ITEM_1 = "EXPENSE_AMOUNT_ITEM_1";

    public static final String EXPENSE_AMOUNT_ITEM_2 = "EXPENSE_AMOUNT_ITEM_2";

    public static final String EXPENSE_AMOUNT_ITEM_3 = "EXPENSE_AMOUNT_ITEM_3";

    public static final String EXPENSE_AMOUNT_ITEM_4 = "EXPENSE_AMOUNT_ITEM_4";

    public static final String EXPENSE_AMOUNT_ITEM_5 = "EXPENSE_AMOUNT_ITEM_5";

    public static final String EXPENSE_AMOUNT_ITEM_6 = "EXPENSE_AMOUNT_ITEM_6";

    public static final String EXPENSE_AMOUNT_ITEM_7 = "EXPENSE_AMOUNT_ITEM_7";

    public static final String EXPENSE_AMOUNT_ITEM_8 = "EXPENSE_AMOUNT_ITEM_8";

    public static final String EXPENSE_AMOUNT_ITEM_9 = "EXPENSE_AMOUNT_ITEM_9";

    public static final String EXPENSE_AMOUNT_ITEM_10 = "EXPENSE_AMOUNT_ITEM_10";

    public static final String EXPENSE_AMOUNT_ITEM_11 = "EXPENSE_AMOUNT_ITEM_11";

    public static final String EXPENSE_AMOUNT_ITEM_12 = "EXPENSE_AMOUNT_ITEM_12";

    public static final String EXPENSE_AMOUNT_ITEM_13 = "EXPENSE_AMOUNT_ITEM_13";

    public static final String EXPENSE_AMOUNT_ITEM_14 = "EXPENSE_AMOUNT_ITEM_14";

    public static final String EXPENSE_AMOUNT_ITEM_15 = "EXPENSE_AMOUNT_ITEM_15";

    public static final String EXPENSE_AMOUNT_ITEM_16 = "EXPENSE_AMOUNT_ITEM_16";

    public static final String EXPENSE_AMOUNT_ITEM_17 = "EXPENSE_AMOUNT_ITEM_17";

    public static final String EXPENSE_AMOUNT_ITEM_18 = "EXPENSE_AMOUNT_ITEM_18";

    public static final String EXPENSE_AMOUNT_ITEM_19 = "EXPENSE_AMOUNT_ITEM_19";

    public static final String EXPENSE_AMOUNT_ITEM_20 = "EXPENSE_AMOUNT_ITEM_20";

    public static final String RPOCESS_RATE_ITEM_1 = "RPOCESS_RATE_ITEM_1";

    public static final String RPOCESS_RATE_ITEM_2 = "RPOCESS_RATE_ITEM_2";

    public static final String RPOCESS_RATE_ITEM_3 = "RPOCESS_RATE_ITEM_3";

    public static final String RPOCESS_RATE_ITEM_4 = "RPOCESS_RATE_ITEM_4";

    public static final String RPOCESS_RATE_ITEM_5 = "RPOCESS_RATE_ITEM_5";

    public static final String RPOCESS_RATE_ITEM_6 = "RPOCESS_RATE_ITEM_6";

    public static final String RPOCESS_RATE_ITEM_7 = "RPOCESS_RATE_ITEM_7";

    public static final String RPOCESS_RATE_ITEM_8 = "RPOCESS_RATE_ITEM_8";

    public static final String RPOCESS_RATE_ITEM_9 = "RPOCESS_RATE_ITEM_9";

    public static final String RPOCESS_RATE_ITEM_10 = "RPOCESS_RATE_ITEM_10";

    public static final String RPOCESS_RATE_ITEM_11 = "RPOCESS_RATE_ITEM_11";

    public static final String RPOCESS_RATE_ITEM_12 = "RPOCESS_RATE_ITEM_12";

    public static final String RPOCESS_RATE_ITEM_13 = "RPOCESS_RATE_ITEM_13";

    public static final String RPOCESS_RATE_ITEM_14 = "RPOCESS_RATE_ITEM_14";

    public static final String RPOCESS_RATE_ITEM_15 = "RPOCESS_RATE_ITEM_15";

    public static final String RPOCESS_RATE_ITEM_16 = "RPOCESS_RATE_ITEM_16";

    public static final String RPOCESS_RATE_ITEM_17 = "RPOCESS_RATE_ITEM_17";

    public static final String RPOCESS_RATE_ITEM_18 = "RPOCESS_RATE_ITEM_18";

    public static final String RPOCESS_RATE_ITEM_19 = "RPOCESS_RATE_ITEM_19";

    public static final String RPOCESS_RATE_ITEM_20 = "RPOCESS_RATE_ITEM_20";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
