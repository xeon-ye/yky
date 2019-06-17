package com.deloitte.platform.api.srpmp.project.base.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-22
 * @Description : SrpmsProjectAttachment新增修改form对象
 * @Modified :
 */
@ApiModel("新增SrpmsProjectAttachment表单")
@Data
public class SrpmsProjectAttachmentForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "项目ID")
    private Long projectId;

    @ApiModelProperty(value = "序号")
    private Long serial;

    @ApiModelProperty(value = "附件类型")
    private String attachmentCategory;

    @ApiModelProperty(value = "文档类型")
    private String fileType;

    @ApiModelProperty(value = "附件名称")
    private String attachmentName;

    @ApiModelProperty(value = "附件路径")
    private String fileUrl;

    @ApiModelProperty(value = "附件说明")
    private String attachmentRemark;

}
