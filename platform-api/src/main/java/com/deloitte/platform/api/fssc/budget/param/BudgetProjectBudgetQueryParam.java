package com.deloitte.platform.api.fssc.budget.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jaws
 * @Date : Create in 2019-03-20
 * @Description :  BudgetProjectBudget查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetProjectBudgetQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long orgUnitId;
    private String orgUnitCode;
    private String orgPath;
    private Long budgetProjectId;
    private String projectCode;
    private String subjectCode;
    private String taskCode;
    private String projectStatus;
    private String budgetAccountCode;
    private Long budgetAccountId;
    private String budgetPeriod;
    private String budgetAnnual;
    private BigDecimal budgetAmount;
    private BigDecimal budgetRemainAmount;
    private BigDecimal budgetOccupyAmount;
    private BigDecimal budgetUsableAmount;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;

}
