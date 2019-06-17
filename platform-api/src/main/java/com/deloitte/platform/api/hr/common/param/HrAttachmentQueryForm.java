package com.deloitte.platform.api.hr.common.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-04
 * @Description :   Attachment查询from对象
 * @Modified :
 */
@ApiModel("Attachment查询表单")
@Data
public class HrAttachmentQueryForm extends BaseQueryForm<HrAttachmentQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

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
