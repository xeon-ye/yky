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
 * @Date : Create in 2019-05-25
 * @Description : ExeBudget新增修改form对象
 * @Modified :
 */
@ApiModel("新增ExeBudget表单")
@Data
public class ExeBudgetForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目id")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    private String applicationId;

    @ApiModelProperty(value = "项目执行id")
    private String executionId;

    @ApiModelProperty(value = "预算种类code")
    private String budgetCode;

    @ApiModelProperty(value = "预算种类name")
    private String budgetName;

    @ApiModelProperty(value = "预算费用")
    private String budgetAmount;

    @ApiModelProperty(value = "执行费用")
    private String exeAmount;

    @ApiModelProperty(value = "年度")
    private String budgetYear;

    @ApiModelProperty(value = "拓展1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    private String ext3;

}
