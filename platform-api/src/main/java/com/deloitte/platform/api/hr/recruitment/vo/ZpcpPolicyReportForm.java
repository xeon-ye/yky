package com.deloitte.platform.api.hr.recruitment.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description : ZpcpPolicyReport新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpPolicyReport表单")
@Data
public class ZpcpPolicyReportForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "政策报告名称")
    private String reportName;

    @ApiModelProperty(value = "等级名称")
    private String levelName;

    @ApiModelProperty(value = "采用时间")
    private LocalDate adoptionTime;

    @ApiModelProperty(value = "采用单位")
    private String adoptionUnit;

    @ApiModelProperty(value = "级别")
    private String grade;

    @ApiModelProperty(value = "所获分数")
    private String score;

    @ApiModelProperty(value = "通知id")
    private Long noticeId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "员工编号")
    private String empCode;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentForm attachmentForm;

}
