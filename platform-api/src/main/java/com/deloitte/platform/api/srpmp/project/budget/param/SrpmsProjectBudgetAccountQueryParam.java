package com.deloitte.platform.api.srpmp.project.budget.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : LIJUN
 * @Date : Create in 2019-04-19
 * @Description :  SrpmsProjectBudgetAccount查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectBudgetAccountQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long parentId;
    private String projectCategory;
    private String budgetAccountYear;
    private String budgetAccountCode;
    private String budgetAccountName;
    private Integer budgetAccountStatus;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
