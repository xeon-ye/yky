package com.deloitte.platform.api.fssc.base.param;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description :   单据类型-支出大类关联 查询form对象
 * @Modified :
 */
@ApiModel("单据类型-支出大类关联 查询表单")
@Data
public class BaseDocumentTypeExpenseQueryForm extends
        BaseQueryForm<BaseDocumentTypeExpenseQueryParam> {

    @ApiModelProperty(value = "ID")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "支出大类ID")
    private Long expenseMainTypeId;

    @ApiModelProperty(value = "支出大类编码")
    private String expenseMainTypeCode;

    @ApiModelProperty(value = "支出大类名称")
    private String expenseMainTypeName;

    @ApiModelProperty(value = "单据类型ID")
    private Long documentTypeId;

    @ApiModelProperty(value = "启用标志")
    private String validFlag;

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

    private String sort;

    private String sortOrder;
}
