package com.deloitte.platform.api.hr.employee.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-05-20
 * @Description : EmployeeWages返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWagesInfoVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "员工姓名")
    private String name;

    @ApiModelProperty(value = "部门名称")
    private String depName;

    @ApiModelProperty(value = "单位名称")
    private String company;

    @ApiModelProperty(value = "岗位级别")
    private String positionLevel;

    @ApiModelProperty(value = "工资列表")
    private List<EmployeeWagesVo> wagesList;

}
