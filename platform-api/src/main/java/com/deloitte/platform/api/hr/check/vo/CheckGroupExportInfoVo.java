package com.deloitte.platform.api.hr.check.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckGroupExportInfoVo
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckGroupExportInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考核工作名称")
    private String cheakWorkName;

    @ApiModelProperty(value = "考核组织")
    private String checkOrgName;

    @ApiModelProperty(value = "教职工编号")
    private String checkUserCode;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "考核分组名称")
    private String groupName;

    @ApiModelProperty(value = "分组类别")
    private String groupType;

    @ApiModelProperty(value = "评价人/被评价人角色")
    private String checkObjectRole;

    @ApiModelProperty(value = "操作人编号")
    private String operateCode;
}
