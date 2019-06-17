package com.deloitte.platform.api.hr.ebs.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description : EmployeeMesWork新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeMesWork表单")
@Data
public class EmployeeMesWorkForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "头像地址")
    private String headPhoto;

    @ApiModelProperty(value = "开始日期")
    private LocalDate startTime;

    @ApiModelProperty(value = "结束日期")
    private LocalDate endTime;

    @ApiModelProperty(value = "工作单位")
    private String workUnit;

    @ApiModelProperty(value = "部门")
    private String department;

    @ApiModelProperty(value = "岗位/职务")
    private String departPostion;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "员工自助ID")
    private String empMesId;

    @ApiModelProperty(value = "流程申请ID")
    private String mesTid;

    @ApiModelProperty(value = "证件号码")
    private String nationalIdentifier;

    @ApiModelProperty(value = "特殊信息名称")
    private String specialInformation;
}
