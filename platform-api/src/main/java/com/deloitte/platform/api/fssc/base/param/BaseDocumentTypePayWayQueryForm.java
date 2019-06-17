package com.deloitte.platform.api.fssc.base.param;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-11
 * @Description :   BaseDocumentTypePayWay查询from对象
 * @Modified :
 */
@ApiModel("BaseDocumentTypePayWay查询表单")
@Data
public class BaseDocumentTypePayWayQueryForm extends BaseQueryForm<BaseDocumentTypePayWayQueryParam>  {

    @ApiModelProperty(value = "ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "付款方式编码")
    private String code;

    @ApiModelProperty(value = "付款方式名称")
    private String name;

    @ApiModelProperty(value = "说明")
    private String description;

    @ApiModelProperty(value = "是否有效")
    private String validFlag;

    @ApiModelProperty(value = "单据类型ID",required = true)
    @NotBlank(message = "单据类型ID不能为空")
    private String documentTypeId;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    @ApiModelProperty(value = "扩展字段2")
    private String ext2;

    @ApiModelProperty(value = "扩展字段3")
    private String ext3;

    @ApiModelProperty(value = "扩展字段4")
    private String ext4;

    @ApiModelProperty(value = "扩展字段5")
    private String ext5;

    @ApiModelProperty(value = "组织ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;
}
