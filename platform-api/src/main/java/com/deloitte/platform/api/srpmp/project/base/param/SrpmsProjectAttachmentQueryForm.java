package com.deloitte.platform.api.srpmp.project.base.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-22
 * @Description :   SrpmsProjectAttachment查询from对象
 * @Modified :
 */
@ApiModel("SrpmsProjectAttachment查询表单")
@Data
public class SrpmsProjectAttachmentQueryForm extends BaseQueryForm<SrpmsProjectAttachmentQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
