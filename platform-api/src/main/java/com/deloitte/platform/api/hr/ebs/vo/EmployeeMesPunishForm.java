package com.deloitte.platform.api.hr.ebs.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description : EmployeeMesPunish新增修改form对象
 * @Modified :
 */
@ApiModel("新增EmployeeMesPunish表单")
@Data
public class EmployeeMesPunishForm extends BaseForm {
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

    @ApiModelProperty(value = "处分类别")
    private String punishCategory;

    @ApiModelProperty(value = "处分名称")
    private String punishName;

    @ApiModelProperty(value = "受处分时间")
    private LocalDate punishDate;

    @ApiModelProperty(value = "受处分给予单位")
    private String punishUnit;

    @ApiModelProperty(value = "撤销处分时间")
    private LocalDate unpunishDate;

    @ApiModelProperty(value = "是否监察机关直接给予")
    private String supervisory;

    @ApiModelProperty(value = "申诉时间")
    private LocalDate appealDate;

    @ApiModelProperty(value = "申诉内容")
    private String appealContent;

    @ApiModelProperty(value = "申诉处理结果")
    private String appealResult;

    @ApiModelProperty(value = "申诉受理单位")
    private String appealUnit;

    @ApiModelProperty(value = "证件号码")
    private String nationalIdentifier;

    @ApiModelProperty(value = "特殊信息名称")
    private String specialInformation;

    @ApiModelProperty(value = "处分原因")
    private String punishReson;

    @ApiModelProperty(value = "申诉原因")
    private String appealReson;
}
