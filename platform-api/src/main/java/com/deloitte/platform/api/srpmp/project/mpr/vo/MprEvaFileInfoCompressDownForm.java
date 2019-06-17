package com.deloitte.platform.api.srpmp.project.mpr.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : JeaChen
 * @Date : Create in 2019/4/11 14:56
 * @Description :
 * @Modified:
 */
@ApiModel("附件打包ZIP并下载")
@Data
public class MprEvaFileInfoCompressDownForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "附件")
    List<MprEvaFileInfoFieldForm> mprEvaFileInfoFieldForms;

}
