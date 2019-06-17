package com.deloitte.platform.api.srpmp.project.mpr.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description : MprProjEvaFundInfo新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprProjEvaFundInfo表单")
@Data
public class MprProjEvaFundInfoForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "任务")
    private String task;

    @ApiModelProperty(value = "预算")
    private String budget;

    @ApiModelProperty(value = "支出")
    private String expenditure;

    @ApiModelProperty(value = "执行率")
    private String exacutiveRate;

}
