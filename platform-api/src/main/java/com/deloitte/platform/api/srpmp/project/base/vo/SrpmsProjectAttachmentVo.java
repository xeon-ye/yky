package com.deloitte.platform.api.srpmp.project.base.vo;
import com.alibaba.fastjson.annotation.JSONField;
import com.deloitte.platform.api.srpmp.common.config.LongJsonSerializer;
import com.deloitte.platform.api.srpmp.common.config.LongToStringSerializer;
import com.deloitte.platform.common.core.entity.vo.BaseVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-22
 * @Description : SrpmsProjectAttachment返回的VO对象
 * @Modified :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class SrpmsProjectAttachmentVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JSONField(serializeUsing = LongToStringSerializer.class)
    private Long id;

    @ApiModelProperty(value = "项目ID")
    @JsonSerialize(using = LongJsonSerializer.class)
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
