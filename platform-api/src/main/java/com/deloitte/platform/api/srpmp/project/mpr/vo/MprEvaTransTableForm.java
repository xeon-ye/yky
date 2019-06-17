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
 * @Description : MprEvaTransTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaTransTable表单")
@Data
public class MprEvaTransTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "成果名称")
    private String outcomeName;

    @ApiModelProperty(value = "技术转让")
    private String techTrans;

    @ApiModelProperty(value = "联合研发")
    private String unionDev;

    @ApiModelProperty(value = "技术服务")
    private String techService;

    @ApiModelProperty(value = "合同签订年份")
    private String contractSignYear;

    @ApiModelProperty(value = "合同金额（万元）")
    private String contractAmount;

}
