package com.deloitte.platform.api.hr.retire.vo;
import com.deloitte.platform.api.hr.common.vo.HrAttachmentForm;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-11
 * @Description : RetireWoman新增修改form对象
 * @Modified :
 */
@ApiModel("新增RetireWoman表单")
@Data
public class RetireWomanForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "员工表ID")
    private String empId;

    @ApiModelProperty(value = "退休理由")
    private String retire;

    @ApiModelProperty(value = "${field.comment}")
    private String careteBy;

    @ApiModelProperty(value = "附件信息")
    List<HrAttachmentForm> attachments;

    @ApiModelProperty(value = "表主键ID")
    private String id;

    @ApiModelProperty(value = "申请状态")
    private String applyState;

}
