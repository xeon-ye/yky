package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description : HrZpcpGroupUser新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrZpcpGroupUser表单")
@Data
public class HrZpcpGroupUserForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "员工的empCode编号")
    private String userId;

    @ApiModelProperty(value = "聘任工作组编号")
    private Long workgroupId;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    private String status;

}
