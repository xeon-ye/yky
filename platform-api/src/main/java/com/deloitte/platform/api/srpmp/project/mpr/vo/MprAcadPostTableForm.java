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
 * @Description : MprAcadPostTable新增修改form对象
 * @Modified :
 */
@ApiModel("新增MprAcadPostTable表单")
@Data
public class MprAcadPostTableForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目编号")
    private String projectNo;

    @ApiModelProperty(value = "学术任职组织")
    private String acadEmpOrg;

    @ApiModelProperty(value = "国内/国外")
    private String domesticForeign;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "职务")
    private String position;

}
