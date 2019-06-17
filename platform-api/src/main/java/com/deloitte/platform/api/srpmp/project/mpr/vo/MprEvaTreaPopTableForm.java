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
 * @Description : MprEvaTreaPopTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprEvaTreaPopTable表单")
@Data
public class MprEvaTreaPopTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "科普基地名称")
    private String popularBaseName;

    @ApiModelProperty(value = "科普内容")
    private String popularScienceContent;

    @ApiModelProperty(value = "成立年份")
    private String yearEstablished;

    @ApiModelProperty(value = "接待人次（千人）")
    private String receptionNumber;

}
