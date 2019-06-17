package com.deloitte.platform.api.hr.retire.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author ：Mr.Zhong
 * @Date ：Created in 2019/5/7 14:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetirePeopleVo extends BaseVo {

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "员工姓名")
    private String empName;

    @ApiModelProperty(value = "年龄")
    private String empAge;

    @ApiModelProperty(value = "性别")
    private String empSex;

    @ApiModelProperty(value = "部门")
    private String empDep;

    @ApiModelProperty(value = "职务")
    private String empPosition;

    @ApiModelProperty(value = "拟退休日期")
    private LocalDateTime retireDate;

}
