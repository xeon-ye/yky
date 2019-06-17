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
 * @Description : MprJouPostTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprJouPostTable表单")
@Data
public class MprJouPostTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "期刊名称")
    private String jouName;

    @ApiModelProperty(value = "期刊级别")
    private String jouLevel;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "任职职务")
    private String position;

}
