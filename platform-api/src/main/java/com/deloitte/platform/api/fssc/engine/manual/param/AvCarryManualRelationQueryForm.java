package com.deloitte.platform.api.fssc.engine.manual.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : chenx
 * @Date : Create in 2019-05-08
 * @Description :   AvCarryManualRelation查询from对象
 * @Modified :
 */
@ApiModel("AvCarryManualRelation查询表单")
@Data
public class AvCarryManualRelationQueryForm extends BaseQueryForm<AvCarryManualRelationQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "凭证ID")
    private Long jeHeaderId;

    @ApiModelProperty(value = "结转ID")
    private Long carrayId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private Long createBy;
}
