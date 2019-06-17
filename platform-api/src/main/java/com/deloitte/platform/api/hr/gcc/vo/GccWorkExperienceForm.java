package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccWorkExperience新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccWorkExperience表单")
@Data
public class GccWorkExperienceForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "人员编号")
    private Long userId;

    @ApiModelProperty(value = "人员姓名")
    private String userName;

    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "单位名称")
    private String companyName;

    @ApiModelProperty(value = "从事专业")
    private String workMajor;

    @ApiModelProperty(value = "职务")
    private String duty;

    @ApiModelProperty(value = "职称")
    private String jobTitle;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentForm attachmentForm;
}
