package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccTalentProjectDepart新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccTalentProjectDepart表单")
@Data
public class GccTalentProjectDepartForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "编号")
    private String code;

    @ApiModelProperty(value = "名称")
    private String departName;

    @ApiModelProperty(value = "简称")
    private String shortName;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "联系电话")
    private String telephone;

    @ApiModelProperty(value = "是否有效")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

}
