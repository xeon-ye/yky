package com.deloitte.platform.api.fssc.base.vo;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-18
 * @Description : BaseDocumentType新增修改form对象
 * @Modified :
 */
@ApiModel("新增BaseDocumentType表单")
@Data
public class BaseDocumentTypeForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID",notes = "修改时,必须传,否则新增处理")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "类型编码",required = true)
    @NotBlank(message = "类型编码不能为空")
    private String typeCode;

    @ApiModelProperty(value = "类型名称",required = true)
    @NotBlank(message = "类型名称不能为空")
    private String name;

    @ApiModelProperty(value = "功能模块",required = true)
    @NotBlank(message = "功能模块不能为空")
    private String functionModule;

    @ApiModelProperty(value = "是否预算控制",notes = "不需要传递,不支持修改")
    private String budgetControlFlag;

    @ApiModelProperty(value = "是否预算保留",notes = "不需要传递,不支持修改")
    private String budgetRemainFlag;

    @ApiModelProperty(value = "是否占用预算",notes = "不需要传递,不支持修改")
    private String budgetOccupyFlag;

    @ApiModelProperty(value = "是否需要审批",required = true)
    @NotBlank(message = "请设置是否需要审批")
    private String auditFlag;

    @ApiModelProperty(value = "是否需要移动审批")
    @NotBlank(message = "请设置是否需要移动审批")
    private String phoneAuditFlag;

    @ApiModelProperty(value = "是否入账",required = true)
    @NotBlank(message = "是否入账不能为空")
    private String postFlag;

    @ApiModelProperty(value = "是否有效",required = true)
    @NotBlank(message = "是否有效不能为空")
    private String validFlag;

    @ApiModelProperty(value = "组织ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long orgId;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

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

}
