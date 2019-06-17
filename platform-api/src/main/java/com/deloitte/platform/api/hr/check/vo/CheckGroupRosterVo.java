package com.deloitte.platform.api.hr.check.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description : CheckGroup新增修改form对象
 * @Modified :
 */
@ApiModel("CheckGroupRosterVo")
@Data
public class CheckGroupRosterVo extends BaseVo {
    private static final long serialVersionUID = 1L;
//序号	部门	姓名	教职工编号	职务	被评价人分组	备注

    @ApiModelProperty(value = "序号")
    private String orderNum;

    @ApiModelProperty(value = "部门")
    private String depName;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "职务")
    private String specTechJob;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "不参加原因")
    private String notPartakeReason;

}
