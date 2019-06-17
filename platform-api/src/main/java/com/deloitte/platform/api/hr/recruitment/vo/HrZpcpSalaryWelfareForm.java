package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-24
 * @Description : HrZpcpSalaryWelfare新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrZpcpSalaryWelfare表单")
@Data
public class HrZpcpSalaryWelfareForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "在聘信息表ID")
    private String infoId;

    @ApiModelProperty(value = "薪酬制度")
    private String salarySystem;

    @ApiModelProperty(value = "基本年薪")
    private Long salaryYear;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "安家费")
    private Double settle;

    @ApiModelProperty(value = "上传的附件对象")
    private HrAttachmentForm hrAttachmentForm;


}
