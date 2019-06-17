package com.deloitte.platform.api.srpmp.project.base.vo.ext;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-22
 * @Description : SrpmsProjectAttachment新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectAttachment表单")
@Data
public class SrpmsProjectAttachmentUploadForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目ID")
    @NotNull
    @JsonSerialize(using = LongJsonSerializer.class)
    private Long projectId;

    @ApiModelProperty(value = "序号")
    private Long serial;

    @ApiModelProperty(value = "附件类型 01:通用项目申请书相关附件, 02:审核意见相关附件")
    @NotBlank
    private String attachmentCategory;

    @ApiModelProperty(value = "字段名称，当attachmentCategory为01时必填")
    @NotBlank
    private String columnName;

    @ApiModelProperty(value = "文档类型")
    private String fileType;

    @ApiModelProperty(value = "附件名称")
    private String attachmentName;

    @ApiModelProperty(value = "附件路径")
    private String fileUrl;

    @ApiModelProperty(value = "附件说明")
    private String attachmentRemark;

}
