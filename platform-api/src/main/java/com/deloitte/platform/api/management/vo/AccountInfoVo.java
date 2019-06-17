package com.deloitte.platform.api.management.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 15:10 18/04/2019
 * @Description :
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户信息集合")
public class AccountInfoVo extends BaseVo {


    @ApiModelProperty(value = "账户信息")
    private SysAccountVo sysAccountVO;


/*
    @ApiModelProperty(value = "用户信息")
    private SysUserVo sysUserVo;

    @ApiModelProperty(value = "权限信息")
    private List<SysPermissionVo> sysPermissionVo;

    @ApiModelProperty(value = "权限信息")
    private List<SysRoleVo> sysRoleVo;

*/


    @ApiModelProperty(value = "权限信息")
    private String[] permissions;

    @ApiModelProperty(value = "权限信息")
    private Integer[] roles;

}
