package com.deloitte.platform.api.hr.check.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :   CheckGroup查询from对象
 * @Modified :
 */
@ApiModel("CheckGroup查询表单")
@Data
public class CheckGroupQueryForm extends BaseQueryForm<CheckGroupQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "考核工作id")
    private String checkWorkId;

    @ApiModelProperty(value = "考核组织id")
    private String checkOrgId;

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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "考核分组id集合")
    private List<String> checkGroupIdList;
}
