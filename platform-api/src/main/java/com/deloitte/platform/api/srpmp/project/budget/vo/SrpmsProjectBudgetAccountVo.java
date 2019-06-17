package com.deloitte.platform.api.srpmp.project.budget.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-19
 * @Description : SrpmsProjectBudgetAccount返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SrpmsProjectBudgetAccountVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "父ID")
    private Long parentId;

    @ApiModelProperty(value = "项目类型")
    private String projectCategory;

    @ApiModelProperty(value = "预算年度")
    private String budgetAccountYear;

    @ApiModelProperty(value = "预算CODE")
    private String budgetAccountCode;

    @ApiModelProperty(value = "预算名字")
    private String budgetAccountName;

    @ApiModelProperty(value = "预算状态（0-禁用；1-启用）")
    private Integer budgetAccountStatus;

    @ApiModelProperty(value = "创建日期")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

}
