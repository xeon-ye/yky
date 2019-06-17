package com.deloitte.platform.api.hr.retireRehiring.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description : RetireDelayApply新增修改form对象
 * @Modified :
 */
@ApiModel("新增RetireDelayApply表单")
@Data
public class DelayApplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "职工编号")
    private String empCode;

    @ApiModelProperty(value = "0.所院,1机关")
    private String institute;

    @ApiModelProperty(value = "近几年工作情况")
    private String recentWorking;

    @ApiModelProperty(value = "拟定担任工作")
    private String intendedWork;

    @ApiModelProperty(value = "个人意见(机关)")
    private String personalOpinion;

    @ApiModelProperty(value = "延缓退休时间")
    private LocalDate delayTime;

    @ApiModelProperty(value = "状态0保存,1提交")
    private String status;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "通知记录表Id")
    private Long recordId;

    @ApiModelProperty(value = "附件对象数组")
    private List<HrAttachmentForm> attachmentForms;

}
