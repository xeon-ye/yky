package com.deloitte.platform.api.fssc.base.param;
import com.deloitte.platform.api.fssc.config.LongJsonDeserializer;
import com.deloitte.platform.api.fssc.config.LongJsonSerializer;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-18
 * @Description :   单据类型-查询from对象
 * @Modified :
 */
@ApiModel("单据类型-查询表单")
@Data
public class BaseDocumentTypeQueryForm extends BaseQueryForm<BaseDocumentTypeQueryParam>  {

    @ApiModelProperty(value = "ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "类型编码")
    private String typeCode;

    @ApiModelProperty(value = "单据名称")
    private String name;

    @ApiModelProperty(value = "功能模块")
    private String functionModule;

//    @ApiModelProperty(value = "是否预算控制")
//    private String budgetControlFlag;
//
//    @ApiModelProperty(value = "是否预算保留")
//    private String budgetRemainFlag;
//
//    @ApiModelProperty(value = "是否占用预算")
//    private String budgetOccupyFlag;

    @ApiModelProperty(value = "是否需要审批")
    private String auditFlag;

    @ApiModelProperty(value = "是否入账")
    private String postFlag;

    @ApiModelProperty(value = "是否有效")
    private String validFlag;

    @ApiModelProperty(value = "组织ID")
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long orgId;

    @ApiModelProperty(value = "单位编码",required = true)
    private String unitCode;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

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

    @ApiModelProperty(value = "排序字段")
    private String sort;

    @ApiModelProperty(value = "排序方向")
    private String sortOrder;
}
