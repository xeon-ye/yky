package com.deloitte.platform.api.project.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-06-04
 * @Description : BudgetBak新增修改form对象
 * @Modified :
 */
@ApiModel("新增BudgetBak表单")
@Data
public class BudgetBakForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "预算ID")
    private String budgetId;

    @ApiModelProperty(value = "申报书ID")
    private String applicationId;

    @ApiModelProperty(value = "支出大类CODE")
    private String expenseProjectCode;

    @ApiModelProperty(value = "支出大类")
    private String expenseProject;

    @ApiModelProperty(value = "预算年度")
    private String budgetaryYear;

    @ApiModelProperty(value = "申请合计")
    private String applyTotal;

    @ApiModelProperty(value = "申报中央财政")
    private String centralFin;

    @ApiModelProperty(value = "申报主管部门")
    private String department;

    @ApiModelProperty(value = "申报其他")
    private String other;

    @ApiModelProperty(value = "申请经费测算依据")
    private String basisEstimatingAppFunds;

    @ApiModelProperty(value = "评审ID")
    private String reviewId;

    @ApiModelProperty(value = "评审中央财政金额")
    private String reviewCentralFinance;

    @ApiModelProperty(value = "评审主管部门金额")
    private String reviewDepartemntFund;

    @ApiModelProperty(value = "评审其他金额")
    private String reviewOthers;

    @ApiModelProperty(value = "立项批复ID")
    private String replayId;

    @ApiModelProperty(value = "批复其它")
    private String replayOther;

    @ApiModelProperty(value = "批复中央财政")
    private String replayCenter;

    @ApiModelProperty(value = "批复部门")
    private String replayDep;

    @ApiModelProperty(value = "结转资金")
    private String foundingForward;

    @ApiModelProperty(value = "拓展字段")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    private String orgPath;

    @ApiModelProperty(value = "项目ID")
    private String projectId;

    @ApiModelProperty(value = "类型")
    private String expenseCodes;

}
