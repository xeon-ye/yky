package com.deloitte.platform.api.hr.employee.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-05-20
 * @Description : EmployeeWages返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWagesVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "员工编号")
    private String employeeCode;

    @ApiModelProperty(value = "工资年份")
    private String yearDate;

    @ApiModelProperty(value = "工资月份")
    private String monthDate;

    @ApiModelProperty(value = "项目名称")
    private String label;

    @ApiModelProperty(value = "金额（元）")
    private Double money;

    @ApiModelProperty(value = "项目类型")
    private String labelType;

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

}
