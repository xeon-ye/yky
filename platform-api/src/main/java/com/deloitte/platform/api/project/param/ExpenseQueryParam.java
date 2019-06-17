package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : zhengchun
 * @Date : Create in 2019-05-30
 * @Description :  Expense查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String expenseId;
    private String projectApprovalId;
    private String budgetYear;
    private String expensePlan;
    private String finAllocationEarly;
    private String finCarryOverEarly;
    private String adjFinancialAllocation;
    private String adjFinancialCarryOver;
    private String finAllocationAllYear;
    private String finCarryOverAllYear;
    private String finAllocationBudget;
    private String finCarryOverBudget;
    private String finAllocationSurplus;
    private String finCarryOverSurplus;
    private String finAllocationFactSurplus;
    private String finCarryOverFactSurplus;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private Long orgId;
    private String orgPath;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String projectId;
    private String applicationId;

}
