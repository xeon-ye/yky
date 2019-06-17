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
 * @Description : RetireApply新增修改form对象
 * @Modified :
 */
@ApiModel("新增RetireApply表单")
@Data
public class RetireApplyForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "备注/退休原由")
    private String remark;

    @ApiModelProperty(value = "0保存,1提交")
    private String status;

    @ApiModelProperty(value = "审批转态(保留字段)")
    private String approval;

    @ApiModelProperty(value = "申请中包含员工数组")
    private List<RetirePersonForm> retirePersonForms;

    @ApiModelProperty(value = "1.人力资源处,2.机关职工-处级,副高级女干部,3.所院高级干部")
    private String retireType;

    @ApiModelProperty(value = "${field.comment}")
    private String orgCode;

    @ApiModelProperty(value = "附件数组")
    private List<HrAttachmentForm> attachmentForms;

}
