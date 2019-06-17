package com.deloitte.platform.api.srpmp.project.base.vo.ext;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by lixin on 04/03/2019.
 */
@ApiModel("Word导入通用请求对象")
@Data
public class WordImportReqVo extends BaseForm implements Serializable {

    @ApiModelProperty(value = "文件URL")
    @NotBlank
    private String fileUrl;


}
