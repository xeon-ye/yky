package com.deloitte.platform.api.srpmp.project.mpr.vo;

import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectAttachmentVo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author:wuhebiao
 * Date:27/03/2019
 * Description:
 */
@Data
@ApiModel("新增或者更新年度报告")
public class MprSaveOrUpdateForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "报告基本信息")
    @NotNull
    private MprEvaBaseInfoForm baseInfoForm;

    @ApiModelProperty(value = "附件列表")
    private List<SrpmsProjectAttachmentVo> srpmsProjectAttachmentFormList;

}

