package com.deloitte.platform.api.hr.retireRehiring.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-15
 * @Description : RetireRemindRecord新增修改form对象
 * @Modified :
 */
@ApiModel("新增RetireRemindRecord表单")
@Data
public class RemindRecordForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别 1 男 2女 3不详")
    private String gender;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "岗位编码")
    private String positionCode;

    @ApiModelProperty(value = "岗位名称")
    private String positionName;

    @ApiModelProperty(value = "部门编码")
    private String organizationCode;

    @ApiModelProperty(value = "部门名称")
    private String organizationName;

    @ApiModelProperty(value = "拟定退休日期")
    private LocalDate retireDate;

    @ApiModelProperty(value = "拟定返聘截止日期")
    private LocalDate rebateData;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "提醒表id")
    private Long remindId;

    @ApiModelProperty(value = "是否属于干部 0 机关职工,所院高级干部 1 机关职工处级，副高级女干部 2 所院高级干 3高级专家")
    private String iscadre;

    @ApiModelProperty(value = "专业技术职务")
    private String specTechJob;

}
