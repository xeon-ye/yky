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
 * @Description : MprEvaTheoTechProTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaTheoTechProTable表单")
@Data
public class MprEvaTheoTechProTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "产出类型")
    private String outputType;

    @ApiModelProperty(value = "产出名称")
    private String outputName;

    @ApiModelProperty(value = "产生意义")
    private String bringMean;

}
