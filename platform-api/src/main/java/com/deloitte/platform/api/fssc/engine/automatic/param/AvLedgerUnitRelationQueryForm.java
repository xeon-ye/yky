package com.deloitte.platform.api.fssc.engine.automatic.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-30
 * @Description :   AvLedgerUnitRelation查询from对象
 * @Modified :
 */
@ApiModel("AvLedgerUnitRelation查询表单")
@Data
public class AvLedgerUnitRelationQueryForm extends BaseQueryForm<AvLedgerUnitRelationQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "平衡段值")
    private String balanceCode;

    @ApiModelProperty(value = "账薄ID")
    private Long ledgerId;

    @ApiModelProperty(value = "预留字段1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    private String ext5;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;
}
