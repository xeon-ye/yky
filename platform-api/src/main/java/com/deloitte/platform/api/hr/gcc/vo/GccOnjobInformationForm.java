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
 * @Description : GccOnjobInformation新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccOnjobInformation表单")
@Data
public class GccOnjobInformationForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "人员编号")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "所属人才项目")
    private Long projectId;

    @ApiModelProperty(value = "人才项目状态")
    private String projectStatus;

    @ApiModelProperty(value = "人才项目类型")
    private Long projectTypeId;

    @ApiModelProperty(value = "人才项目申请时间")
    private LocalDateTime projectApplyTime;

    @ApiModelProperty(value = "到岗时间")
    private LocalDateTime onjobTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "是否到岗")
    private String whetherWork;

    @ApiModelProperty(value = "入选人员编号")
    private Long highLevelId;


    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentForm attachmentForms;
}
