package com.deloitte.platform.api.srpmp.project.budget.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :  SrpmsProjectBudgetDetail查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SrpmsProjectBudgetDetailQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long projectId;
    private String projectNum;
    private String budgetCategory;
    private Integer budgetYear;
    private String taskName;
    private Integer serial;
    private String taskDeptName;
    private Long taskLeadPersonId;
    private Long taskJoinPersonId;
    private Double budgetAmount;
    private String deptName;
    private String orgCode;
    private String deptCategory;
    private String deptTaskContent;
    private String remark;
    private String budgetDetail;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;

}
