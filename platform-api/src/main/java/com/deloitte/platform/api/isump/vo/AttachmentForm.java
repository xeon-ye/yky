package com.deloitte.platform.api.isump.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-04
 * @Description : Attachment新增修改form对象
 * @Modified :
 */
@ApiModel("新增Attachment表单")
@Data
public class AttachmentForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "文件系统ID")
    private String fileId;

    @ApiModelProperty(value = "主表类型")
    private String masterType;

    @ApiModelProperty(value = "主表ID")
    private Long masterId;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件地址")
    private String fileUrl;

    @ApiModelProperty(value = "序号")
    private Integer sort;

}
