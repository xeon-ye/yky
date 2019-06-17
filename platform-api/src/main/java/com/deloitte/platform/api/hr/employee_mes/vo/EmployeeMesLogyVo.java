package com.deloitte.platform.api.hr.employee_mes.vo;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : woo
 * @Date : Create in 2019-05-18
 * @Description : EmployeeMesLogy返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeMesLogyVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "${field.comment}")
    private String id;

    @ApiModelProperty(value = "专业技术岗位系列")
    private String segment1;

    @ApiModelProperty(value = "聘任专业技术职务名称")
    private String segment2;

    @ApiModelProperty(value = "聘任专业技术岗位职称级别")
    private String segment3;

    @ApiModelProperty(value = "聘任起始时间")
    private LocalDate segment5;

    @ApiModelProperty(value = "专业技术资格名称")
    private String segment6;

    @ApiModelProperty(value = "聘任文号")
    private String segment4;

    @ApiModelProperty(value = "取得资格时间")
    private LocalDate segment7;

    @ApiModelProperty(value = "资格审批单位")
    private String segment8;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "${field.comment}")
    private String applyState;

    @ApiModelProperty(value = "${field.comment}")
    private String empMesId;

    @ApiModelProperty(value = "${field.comment}")
    private String updateBy;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "${field.comment}")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "部门")
    private String dept;

    @ApiModelProperty(value = "三定岗位名称")
    private String column1;

    private String mesLid;

}
