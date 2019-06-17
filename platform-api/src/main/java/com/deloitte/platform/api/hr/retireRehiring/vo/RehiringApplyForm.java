package com.deloitte.platform.api.hr.retireRehiring.vo;

import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description : RetireRehiringApply新增修改form对象
 * @Modified :
 */
@ApiModel("新增RetireRehiringApply表单")
@Data
public class RehiringApplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "返聘原因")
    private String rehiringReason;

    @ApiModelProperty(value = "备用字段(审批转态)")
    private String approval;

    @ApiModelProperty(value = "0保存,1.提交")
    private String status;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "1.部门年度申请,2.部门月度申请,3人力资源处申请")
    private String applyType;

    @ApiModelProperty(value = "申请人数组")
    private List<RehiringPersonForm> rehiringPersonForms;

    @ApiModelProperty(value = "附件对象数组")
    private List<HrAttachmentForm> attachmentForms;
}
