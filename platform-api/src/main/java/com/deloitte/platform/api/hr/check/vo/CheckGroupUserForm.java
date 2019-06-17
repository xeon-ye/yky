package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckGroupUser新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckGroupUser表单")
@Data
public class CheckGroupUserForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核分组id")
    private Long checkGroupId;

    @ApiModelProperty(value = "人员id")
    private Long userId;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

}
