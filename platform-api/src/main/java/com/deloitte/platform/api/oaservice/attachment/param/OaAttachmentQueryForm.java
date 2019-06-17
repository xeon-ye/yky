package com.deloitte.platform.api.oaservice.attachment.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-15
 * @Description :   OaAttachment查询from对象
 * @Modified :
 */
@ApiModel("OaAttachment查询表单")
@Data
public class OaAttachmentQueryForm extends BaseQueryForm<OaAttachmentQueryParam>  {


    @ApiModelProperty(value = "附件表id")
    private Long id;

    @ApiModelProperty(value = "业务ID，例如新闻id, 公告id")
    private String busicessId;

    @ApiModelProperty(value = "业务类型名称，例如新闻、公告")
    private String busicessName;

    @ApiModelProperty(value = "附件名")
    private String attachName;

    @ApiModelProperty(value = "附件URL")
    private String attachUrl;

    @ApiModelProperty(value = "是否删除 默认0，表示未删除")
    private String delFlag;

    @ApiModelProperty(value = "附件路径")
    private String attachPath;

    @ApiModelProperty(value = "附件日期")
    private LocalDateTime applyDatetime;

    @ApiModelProperty(value = "文件服务器存储ID")
    private String fileId;

}
