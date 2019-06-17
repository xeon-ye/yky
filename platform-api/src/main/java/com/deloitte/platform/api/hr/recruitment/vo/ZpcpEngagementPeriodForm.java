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
 * @Description : ZpcpEngagementPeriod新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpEngagementPeriod表单")
@Data
public class ZpcpEngagementPeriodForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "是否有效(0无效,1有效)")
    private String status;

    @ApiModelProperty(value = "聘用开始时间")
    private LocalDate startTime;

    @ApiModelProperty(value = "聘用结束时间")
    private LocalDate endTime;

    @ApiModelProperty(value = "任职年限(月)")
    private String tenureService;

    @ApiModelProperty(value = "聘用状态")
    private String employmentStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;

    @ApiModelProperty(value = "员工编码")
    private String empCode;

    @ApiModelProperty(value = "再评信息表id")
    private Long infoId;

    @ApiModelProperty(value = "附件信息对象")
    private HrAttachmentForm attachmentForm;

}
