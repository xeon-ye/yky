package com.deloitte.platform.api.hr.gcc.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccTalentProjectCategory新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccTalentProjectCategory表单")
@Data
public class GccTalentProjectCategoryForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "编号")
    private String code;

    @ApiModelProperty(value = "所属项目")
    private Long projectCode;

    @ApiModelProperty(value = "项目类别名称")
    private String projectCategoryName;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "是否有效")
    private String status;


    @ApiModelProperty(value = "备注")
    private String remarks;


    @ApiModelProperty(value = "组织代码")
    private String orgCode;

}
