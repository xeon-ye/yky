package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-21
 * @Description : DeclareBase新增修改form对象
 * @Modified :
 */
@ApiModel("新增DeclareBase表单")
@Data
public class DeclareBaseForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "生日")
    private LocalDate birth;

    @ApiModelProperty(value = "政治面貌")
    private String politics;

    @ApiModelProperty(value = "所在部门")
    private String department;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "最高学历")
    private String education;

    @ApiModelProperty(value = "最高学位")
    private String degree;

    @ApiModelProperty(value = "毕业院校")
    private String academy;

    @ApiModelProperty(value = "毕业专业")
    private String major;

    @ApiModelProperty(value = "毕业时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate graduationDate;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "职工编号")
    private Long userId;

    @ApiModelProperty(value = "申报人姓名")
    private String name;

    @ApiModelProperty(value = "所在单位编码")
    private String deptCode;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "性别(性别 1 男 2女 3不详)")
    private String gender;

    @ApiModelProperty(value = "证件名称")
    private String idType;

    @ApiModelProperty(value = "证件号码")
    private String idNumber;

    @ApiModelProperty(value = "专业技术职务")
    private String specTechJob;

    @ApiModelProperty(value = "专业研究方向")
    private String researchDir;

    @ApiModelProperty(value = "研究生导师资格")
    private String tutorQualification;

    @ApiModelProperty(value = "从事专业")
    private String profession;

    @ApiModelProperty(value = "一级学科")
    private String subject1;

    @ApiModelProperty(value = "二级学科")
    private String subject2;

    @ApiModelProperty(value = "三级学科")
    private String subject3;

    @ApiModelProperty(value = "电子邮箱")
    private String personalEmail;

    @ApiModelProperty(value = "电话")
    private String mobilePhone;

    @ApiModelProperty(value = "通知ID")
    private String noticeId;

    @ApiModelProperty(value = "职务研究系列")
    private String specTechSeries;

}