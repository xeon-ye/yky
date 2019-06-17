package com.deloitte.platform.api.srpmp.project.budget.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectBudgetDetail新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectBudgetDetail表单")
@Data
public class SrpmsProjectBudgetDetailForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "项目编号")
    private String projectNum;

    @ApiModelProperty(value = "预算类型CODE")
    private String budgetCategory;

    @ApiModelProperty(value = "预算年度")
    private Integer budgetYear;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "序号")
    private Integer serial;

    @ApiModelProperty(value = "参与任务的单位名称")
    private String taskDeptName;

    @ApiModelProperty(value = "任务负责人ID")
    private Long taskLeadPersonId;

    @ApiModelProperty(value = "任务参与人员ID")
    private Long taskJoinPersonId;

    @ApiModelProperty(value = "预算金额")
    private Double budgetAmount;

    @ApiModelProperty(value = "单位名称")
    private String deptName;

    @ApiModelProperty(value = "组织机构代码")
    private String orgCode;

    @ApiModelProperty(value = "承担单位类型")
    private String deptCategory;

    @ApiModelProperty(value = "单位承担的任务分工")
    private String deptTaskContent;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "预算明细")
    private String budgetDetail;

}
