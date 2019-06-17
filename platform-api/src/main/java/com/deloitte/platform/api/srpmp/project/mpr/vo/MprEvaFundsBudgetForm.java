package com.deloitte.platform.api.srpmp.project.mpr.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-12
 * @Description : MprEvaFundsBudget新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaFundsBudget表单")
@Data
public class MprEvaFundsBudgetForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "任务")
    private String taskName;

    @ApiModelProperty(value = "预算")
    private String budget;

    @ApiModelProperty(value = "支出")
    private String expenses;

    @ApiModelProperty(value = "执行率")
    private String exacutiveRate;

}
