package com.deloitte.platform.api.hr.ebs.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description : EmployeeMesAward新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeMesAward表单")
@Data
public class EmployeeMesAwardForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "头像地址")
    private String headPhoto;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

    @ApiModelProperty(value = "员工自助ID")
    private String empMesId;

    @ApiModelProperty(value = "流程申请ID")
    private String mesTid;

    @ApiModelProperty(value = "奖励级别")
    private String awardLevel;

    @ApiModelProperty(value = "奖励类别")
    private String awardCategory;

    @ApiModelProperty(value = "奖励名称")
    private String awardName;

    @ApiModelProperty(value = "荣誉称号名称")
    private String honoraryName;

    @ApiModelProperty(value = "奖励批准/荣誉授予原因")
    private String honoraryReason;

    @ApiModelProperty(value = "奖励批准/荣誉授予时间")
    private LocalDate honoraryDate;

    @ApiModelProperty(value = "奖励批准/荣誉授予单位")
    private String honoraryUnit;

    @ApiModelProperty(value = "证件号码")
    private String nationalIdentifier;

    @ApiModelProperty(value = "特殊信息名称")
    private String specialInformation;
}
