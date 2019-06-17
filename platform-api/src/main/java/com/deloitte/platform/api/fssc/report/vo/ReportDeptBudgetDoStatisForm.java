package com.deloitte.platform.api.fssc.report.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description : ReportDeptBudgetDoStatis新增修改form对象
 * @Modified :
 */
@ApiModel("新增ReportDeptBudgetDoStatis表单")
@Data
public class ReportDeptBudgetDoStatisForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "月份")
    private String month;

    @ApiModelProperty(value = "报表状态")
    private String status;

    @ApiModelProperty(value = "周期类型(Y:年度，M:月)")
    private String periodType;

    @ApiModelProperty(value = "是否多个单位合并")
    private String mergeFlag;

    @ApiModelProperty(value = "年度预算-输血研究所")
    private BigDecimal annualBudgetItem1;

    @ApiModelProperty(value = "年度预算-阜外心血管病医院")
    private BigDecimal annualBudgetItem2;

    @ApiModelProperty(value = "年度预算-医放射医学研究所")
    private BigDecimal annualBudgetItem3;

    @ApiModelProperty(value = "年度预算-血液学研究所血液病医院")
    private BigDecimal annualBudgetItem4;

    @ApiModelProperty(value = "年度预算-药用植物研究所")
    private BigDecimal annualBudgetItem5;

    @ApiModelProperty(value = "年度预算-北京协和医院")
    private BigDecimal annualBudgetItem6;

    @ApiModelProperty(value = "年度预算-医生物医学工程研究所")
    private BigDecimal annualBudgetItem7;

    @ApiModelProperty(value = "年度预算-实验动物研究所")
    private BigDecimal annualBudgetItem8;

    @ApiModelProperty(value = "年度预算-医学生物学研究所")
    private BigDecimal annualBudgetItem9;

    @ApiModelProperty(value = "年度预算-药物研究所")
    private BigDecimal annualBudgetItem10;

    @ApiModelProperty(value = "年度预算-北京协和医学院")
    private BigDecimal annualBudgetItem11;

    @ApiModelProperty(value = "年度预算-医药生物技术研究所")
    private BigDecimal annualBudgetItem12;

    @ApiModelProperty(value = "年度预算-皮肤病医院")
    private BigDecimal annualBudgetItem13;

    @ApiModelProperty(value = "年度预算-医学信息研究所")
    private BigDecimal annualBudgetItem14;

    @ApiModelProperty(value = "年度预算-肿瘤医院")
    private BigDecimal annualBudgetItem15;

    @ApiModelProperty(value = "年度预算-基础医学研究所")
    private BigDecimal annualBudgetItem16;

    @ApiModelProperty(value = "年度预算-整形外科医院")
    private BigDecimal annualBudgetItem17;

    @ApiModelProperty(value = "年度预算-医科院本级")
    private BigDecimal annualBudgetItem18;

    @ApiModelProperty(value = "年度预算-病原生物学研究所")
    private BigDecimal annualBudgetItem19;

    @ApiModelProperty(value = "年度预算-合计")
    private BigDecimal annualBudgetItem20;

    @ApiModelProperty(value = "支出金额-输血研究所")
    private BigDecimal expenseAmountItem1;

    @ApiModelProperty(value = "支出金额--阜外心血管病医院")
    private BigDecimal expenseAmountItem2;

    @ApiModelProperty(value = "支出金额--医放射医学研究所")
    private BigDecimal expenseAmountItem3;

    @ApiModelProperty(value = "支出金额--血液学研究所血液病医院")
    private BigDecimal expenseAmountItem4;

    @ApiModelProperty(value = "支出金额--药用植物研究所")
    private BigDecimal expenseAmountItem5;

    @ApiModelProperty(value = "支出金额--北京协和医院")
    private BigDecimal expenseAmountItem6;

    @ApiModelProperty(value = "支出金额--医生物医学工程研究所")
    private BigDecimal expenseAmountItem7;

    @ApiModelProperty(value = "支出金额--实验动物研究所")
    private BigDecimal expenseAmountItem8;

    @ApiModelProperty(value = "支出金额--医学生物学研究所")
    private BigDecimal expenseAmountItem9;

    @ApiModelProperty(value = "支出金额--药物研究所")
    private BigDecimal expenseAmountItem10;

    @ApiModelProperty(value = "支出金额--北京协和医学院")
    private BigDecimal expenseAmountItem11;

    @ApiModelProperty(value = "支出金额--医药生物技术研究所")
    private BigDecimal expenseAmountItem12;

    @ApiModelProperty(value = "支出金额--皮肤病医院")
    private BigDecimal expenseAmountItem13;

    @ApiModelProperty(value = "支出金额--医学信息研究所")
    private BigDecimal expenseAmountItem14;

    @ApiModelProperty(value = "支出金额--肿瘤医院")
    private BigDecimal expenseAmountItem15;

    @ApiModelProperty(value = "支出金额--基础医学研究所")
    private BigDecimal expenseAmountItem16;

    @ApiModelProperty(value = "支出金额--整形外科医院")
    private BigDecimal expenseAmountItem17;

    @ApiModelProperty(value = "支出金额--医科院本级")
    private BigDecimal expenseAmountItem18;

    @ApiModelProperty(value = "支出金额--病原生物学研究所")
    private BigDecimal expenseAmountItem19;

    @ApiModelProperty(value = "支出金额-合计")
    private BigDecimal expenseAmountItem20;

    @ApiModelProperty(value = "执行率--输血研究所")
    private BigDecimal rpocessRateItem1;

    @ApiModelProperty(value = "执行率--阜外心血管病医院")
    private BigDecimal rpocessRateItem2;

    @ApiModelProperty(value = "执行率--医放射医学研究所")
    private BigDecimal rpocessRateItem3;

    @ApiModelProperty(value = "执行率--血液学研究所血液病医院")
    private BigDecimal rpocessRateItem4;

    @ApiModelProperty(value = "执行率--药用植物研究所")
    private BigDecimal rpocessRateItem5;

    @ApiModelProperty(value = "执行率--北京协和医院")
    private BigDecimal rpocessRateItem6;

    @ApiModelProperty(value = "执行率--医生物医学工程研究所")
    private BigDecimal rpocessRateItem7;

    @ApiModelProperty(value = "执行率--实验动物研究所")
    private BigDecimal rpocessRateItem8;

    @ApiModelProperty(value = "执行率--医学生物学研究所")
    private BigDecimal rpocessRateItem9;

    @ApiModelProperty(value = "执行率--药物研究所")
    private BigDecimal rpocessRateItem10;

    @ApiModelProperty(value = "执行率--北京协和医学院")
    private BigDecimal rpocessRateItem11;

    @ApiModelProperty(value = "执行率--医药生物技术研究所")
    private BigDecimal rpocessRateItem12;

    @ApiModelProperty(value = "执行率--皮肤病医院")
    private BigDecimal rpocessRateItem13;

    @ApiModelProperty(value = "执行率--医学信息研究所")
    private BigDecimal rpocessRateItem14;

    @ApiModelProperty(value = "执行率--肿瘤医院")
    private BigDecimal rpocessRateItem15;

    @ApiModelProperty(value = "执行率--基础医学研究所")
    private BigDecimal rpocessRateItem16;

    @ApiModelProperty(value = "执行率--整形外科医院")
    private BigDecimal rpocessRateItem17;

    @ApiModelProperty(value = "执行率--医科院本级")
    private BigDecimal rpocessRateItem18;

    @ApiModelProperty(value = "执行率--病原生物学研究所")
    private BigDecimal rpocessRateItem19;

    @ApiModelProperty(value = "执行率-合计")
    private BigDecimal rpocessRateItem20;

    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    private String ext5;

}
