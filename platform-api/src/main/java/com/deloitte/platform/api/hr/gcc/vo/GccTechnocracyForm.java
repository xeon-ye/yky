package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description : GccTechnocracy新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccTechnocracy表单")
@Data
public class GccTechnocracyForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学员编号")
    private Long id;

    @ApiModelProperty(value = "学员编号")
    private Long userId;

    @ApiModelProperty(value = "学员姓名")
    private String userName;

    @ApiModelProperty(value = "所在单位")
    private String unit;

    @ApiModelProperty(value = "申请年度")
    @Min(4)
    private String year;

    @ApiModelProperty(value = "日期")
    private LocalDateTime dateTime;

    @ApiModelProperty(value = "组织部门")
    private String department;

    @ApiModelProperty(value = "项目")
    private String project;

    @ApiModelProperty(value = "项目类型 0表示 专家培训项目，1 表示专家休养项目")
    private String projectType;

    @ApiModelProperty(value = "地点")
    private String area;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "结束日期")
    private LocalDate endTime;

    @ApiModelProperty(value = "开始日期")
    private LocalDate startTime;

    @ApiModelProperty(value = "完成情况")
    private String performance;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentForm attachmentForm;
}
