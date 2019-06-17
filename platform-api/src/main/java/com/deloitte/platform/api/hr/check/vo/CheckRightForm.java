package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckRight新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckRight表单")
@Data
public class CheckRightForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "教职工id")
    private Long userId;

    @ApiModelProperty(value = "考核组织id")
    private Long checkOrgId;

    @ApiModelProperty(value = "管理部门id")
    private Long departId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "岗位职责")
    private String workDescription;

    @ApiModelProperty(value = "考核工作id")
    private Long checkWorkId;

}
