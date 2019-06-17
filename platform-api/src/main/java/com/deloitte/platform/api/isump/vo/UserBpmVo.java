package com.deloitte.platform.api.isump.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * bpm所需人员信息
 * @author zhangdi
 * @Date 18/05/2019
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBpmVo extends BaseVo {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "员工号")
    private String empNo;

    @ApiModelProperty(value = "单位编号")
    private String dept;

    @ApiModelProperty(value = "单位名称")
    private String deptName;

    @ApiModelProperty(value = "部门id")
    private String orgId;

    @ApiModelProperty(value = "部门编号")
    private String orgCode;

    @ApiModelProperty(value = "部门名称")
    private String orgName;

    @ApiModelProperty(value = "岗位名称")
    private String stationName;

    @ApiModelProperty(value = "类型")
    private String orgType;
}
