package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-17
 * @Description : HrZpcpDeclare新增修改form对象
 * @Modified :
 */
@ApiModel("新增HrZpcpDeclare表单")
@Data
public class HrZpcpDeclareForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "年分")
    private String year;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "申报人姓名")
    private String name;

    @ApiModelProperty(value = "申报一级学科1")
    private String firstClassDiscipline1;

    @ApiModelProperty(value = "申报一级学科2")
    private String firstClassDiscipline2;

    @ApiModelProperty(value = "申报岗位1")
    private String declarationPost1;

    @ApiModelProperty(value = "申报岗位2")
    private String declarationPost2;

    @ApiModelProperty(value = "申报二级学科1")
    private String secondClassDiscipline1;

    @ApiModelProperty(value = "申报二级学科2")
    private String secondClassDiscipline2;

    @ApiModelProperty(value = "申报单位")
    private String declarationUnit;

    @ApiModelProperty(value = "申报时间")
    private LocalDate declaration;

    @ApiModelProperty(value = "申报人详情")
    private String declarationDateil;

    @ApiModelProperty(value = "状态(0.审核未通过,1已保存,2已提交申报,3.聘任工作组审核通过,4学术委员会审核通过,5教授委员会审核通过,6.教职聘任委员会审核通过)")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "申报类型")
    private String declarationType;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "通知ID")
    private Long noticeId;

    @ApiModelProperty(value = "科技成果转化说明")
    private String techResultDesc;

    @ApiModelProperty(value = "科技成果转化得分")
    private String techResultScore;

    @ApiModelProperty(value = "主要学术成绩、影响力、科学意义及社会经济价值")
    private String impactDescription;

    @ApiModelProperty(value = "最后一级学科")
    private String finalFirstClass;

    @ApiModelProperty(value = "最终岗位")
    private String finalPost;

    @ApiModelProperty(value = "员工编号")
    private String empCode;
}
