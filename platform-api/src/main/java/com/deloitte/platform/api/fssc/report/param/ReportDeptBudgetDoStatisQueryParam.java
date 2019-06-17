package com.deloitte.platform.api.fssc.report.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : jaws
 * @Date : Create in 2019-06-14
 * @Description :  ReportDeptBudgetDoStatis查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDeptBudgetDoStatisQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long unitId;
    private String unitCode;
    private String name;
    private String year;
    private String month;
    private String status;
    private String periodType;
    private String mergeFlag;
    private Double annualBudgetItem1;
    private Double annualBudgetItem2;
    private Double annualBudgetItem3;
    private Double annualBudgetItem4;
    private Double annualBudgetItem5;
    private Double annualBudgetItem6;
    private Double annualBudgetItem7;
    private Double annualBudgetItem8;
    private Double annualBudgetItem9;
    private Double annualBudgetItem10;
    private Double annualBudgetItem11;
    private Double annualBudgetItem12;
    private Double annualBudgetItem13;
    private Double annualBudgetItem14;
    private Double annualBudgetItem15;
    private Double annualBudgetItem16;
    private Double annualBudgetItem17;
    private Double annualBudgetItem18;
    private Double annualBudgetItem19;
    private Double annualBudgetItem20;
    private Double expenseAmountItem1;
    private Double expenseAmountItem2;
    private Double expenseAmountItem3;
    private Double expenseAmountItem4;
    private Double expenseAmountItem5;
    private Double expenseAmountItem6;
    private Double expenseAmountItem7;
    private Double expenseAmountItem8;
    private Double expenseAmountItem9;
    private Double expenseAmountItem10;
    private Double expenseAmountItem11;
    private Double expenseAmountItem12;
    private Double expenseAmountItem13;
    private Double expenseAmountItem14;
    private Double expenseAmountItem15;
    private Double expenseAmountItem16;
    private Double expenseAmountItem17;
    private Double expenseAmountItem18;
    private Double expenseAmountItem19;
    private Double expenseAmountItem20;
    private Double rpocessRateItem1;
    private Double rpocessRateItem2;
    private Double rpocessRateItem3;
    private Double rpocessRateItem4;
    private Double rpocessRateItem5;
    private Double rpocessRateItem6;
    private Double rpocessRateItem7;
    private Double rpocessRateItem8;
    private Double rpocessRateItem9;
    private Double rpocessRateItem10;
    private Double rpocessRateItem11;
    private Double rpocessRateItem12;
    private Double rpocessRateItem13;
    private Double rpocessRateItem14;
    private Double rpocessRateItem15;
    private Double rpocessRateItem16;
    private Double rpocessRateItem17;
    private Double rpocessRateItem18;
    private Double rpocessRateItem19;
    private Double rpocessRateItem20;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;

}
