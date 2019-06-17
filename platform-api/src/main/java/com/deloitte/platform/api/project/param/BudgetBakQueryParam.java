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
 * @Date : Create in 2019-06-04
 * @Description :  BudgetBak查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetBakQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String budgetId;
    private String applicationId;
    private String expenseProjectCode;
    private String expenseProject;
    private String budgetaryYear;
    private String applyTotal;
    private String centralFin;
    private String department;
    private String other;
    private String basisEstimatingAppFunds;
    private String reviewId;
    private String reviewCentralFinance;
    private String reviewDepartemntFund;
    private String reviewOthers;
    private String replayId;
    private String replayOther;
    private String replayCenter;
    private String replayDep;
    private String foundingForward;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private Long orgId;
    private String orgPath;
    private String projectId;
    private String expenseCodes;

}
