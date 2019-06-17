package com.deloitte.services.fssc.common.vo;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class FsscCurrentUserInfo {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "单位id")
    private Long deptId;

    @ApiModelProperty(value = "单位名称")
    private String deptName;

    @ApiModelProperty(value = "单位code")
    private String deptCode;

    @ApiModelProperty(value = "部门id")
    private Long orgId;

    @ApiModelProperty(value = "部门名称")
    private String orgName;

    @ApiModelProperty(value = "部门code")
    private String orgCode;

    @ApiModelProperty(value = "角色code")
    private String roleCode;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色codes")
    private List<String> roleCodeList;

    @ApiModelProperty(value = "菜单树")
    private JSONArray menu;
}
