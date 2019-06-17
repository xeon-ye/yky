package com.deloitte.platform.api.srpmp.project.mpr.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author:LIJUN
 * Date:01/04/2019
 * Description:
 */
@ApiModel("文件信息字段Form")
@Data
public class MprEvaFileInfoFieldForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    /**
     * 文件ID
     */
    @ApiModelProperty(value = "文件ID")
    private String fileId;

    @ApiModelProperty(value = "文件名字")
    private String fileName;

    @ApiModelProperty(value = "文件地址")
    private String fileUrl;

}
