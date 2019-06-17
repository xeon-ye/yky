package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckGroup新增修改form对象
 * @Modified :
 */
@ApiModel("新增CheckGroup表单")
@Data
public class CheckGroupForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "考核工作id")
    private Long checkWorkId;

    @ApiModelProperty(value = "考核组织id")
    private Long checkOrgId;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "分组类别")
    private String groupType;

    @ApiModelProperty(value = "考核对象角色")
    private String checkObjectRole;

    @ApiModelProperty(value = "分组角色")
    private String groupRole;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "人员列表id")
    private List<String> userList;

}
