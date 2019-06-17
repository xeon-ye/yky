package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccSpecialistGroup新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccSpecialistGroup表单")
@Data
public class GccSpecialistGroupForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "小组名称")
    private String groupName;

    @ApiModelProperty(value = "评审项目")
    private Long reviewProject;

    @ApiModelProperty(value = "评审年份")
    private String reviewYear;

    @ApiModelProperty(value = "所属评审单位")
    private String reviewUnit;

    @ApiModelProperty(value = "是否有效")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "类型（所院/院校）")
    private String type;

    @ApiModelProperty(value = "分类（评审/考核）")
    private String classify;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentForm attachmentForms;

    @ApiModelProperty(value ="提醒时间设置")
    private LocalDate remindTime;

}
