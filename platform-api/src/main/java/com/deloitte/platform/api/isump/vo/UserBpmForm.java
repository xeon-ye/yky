package com.deloitte.platform.api.isump.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhangdi
 * @Date 18/05/2019
 */
@ApiModel("查询参数")
@Data
public class UserBpmForm {

    @ApiModelProperty(value = "员工号")
    private String empNo;

    @ApiModelProperty(value = "单位CODE")
    private String orgCode;

    @ApiModelProperty(value = "组织编号")
    private List<String> orgCodeList;

    @ApiModelProperty(value = "角色编号")
    private List<String> roleCodeList;

    @ApiModelProperty(value = "岗位名称")
    private List<String> orgNameList;

    @ApiModelProperty(value = "来源系统")
    private String systemName;

    @ApiModelProperty(value = "来源系统Code")
    private String systemCode;

    @ApiModelProperty(value = "页码")
    int page = 0;

    @ApiModelProperty(value = "每页条数")
    int size = 10;
}
