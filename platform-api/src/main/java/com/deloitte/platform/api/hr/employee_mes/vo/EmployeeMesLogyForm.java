package com.deloitte.platform.api.hr.employee_mes.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : woo
 * @Date : Create in 2019-05-18
 * @Description : EmployeeMesLogy新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeMesLogy表单")
@Data
public class EmployeeMesLogyForm extends BaseForm {
    private static final long serialVersionUID = 1L;

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
    private String applyState;

    @ApiModelProperty(value = "${field.comment}")
    private String empMesId;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "单位")
    private String company;

    @ApiModelProperty(value = "部门")
    private String dept;

    @ApiModelProperty(value = "三定岗位名称")
    private String column1;

    private String id;

    private String processNum;

    @ApiModelProperty(value = "教职工编码")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "头像地址")
    private String headPhoto;

    @ApiModelProperty(value = "证件号码")
    private String nationalIdentifier;

    @ApiModelProperty(value = "特殊信息名称")
    private String specialInformation;
}
