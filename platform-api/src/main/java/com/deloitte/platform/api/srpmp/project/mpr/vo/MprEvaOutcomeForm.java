package com.deloitte.platform.api.srpmp.project.mpr.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : LIJUN
 * @Date : Create in 2019-04-02
 * @Description : MprEvaOutcome新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaOutcome表单")
@Data
public class MprEvaOutcomeForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "代表性成果名称")
    private String repOutcomeName;

    @ApiModelProperty(value = "成果类型")
    private String outcomeType;

    @ApiModelProperty(value = "成果水平（国际领先/国际先进/国内领先/国内先进）")
    private String outcomeLevel;

    @ApiModelProperty(value = "对应的任务")
    private String correspondTask;

}
