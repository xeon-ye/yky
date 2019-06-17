package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccGraduateCourse新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccGraduateCourse表单")
@Data
public class GccGraduateCourseForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "人员编号")
    private Long userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "课程名称（上传附件）")
    private String courseName;

    @ApiModelProperty(value = "学期数")
    private Integer semesterNumber;

    @ApiModelProperty(value = "总学时数")
    private Integer totalSemester;

    @ApiModelProperty(value = "选学总人次")
    private Double totalEnrollment;

    @ApiModelProperty(value = "是否为核心课程 0 否，1是")
    private String coreCourse;

    @ApiModelProperty(value = "是否为精品课程 0 否，1是")
    private String qualityCourse;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "类型 0 本科，1 研究生")
    private String type;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentForm attachmentForm;

}
