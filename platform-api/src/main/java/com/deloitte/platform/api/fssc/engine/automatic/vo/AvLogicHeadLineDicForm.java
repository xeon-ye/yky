package com.deloitte.platform.api.fssc.engine.automatic.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : chenx
 * @Date : Create in 2019-04-26
 * @Description : AvLogicHeadLineDic新增修改form对象
 * @Modified :
 */
@ApiModel("新增AvLogicHeadLineDic表单")
@Data
public class AvLogicHeadLineDicForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "单据类型")
    private String documentModule;

    @ApiModelProperty(value = "头表")
    private String headTable;

    @ApiModelProperty(value = "行表")
    private String lineTable;

    @ApiModelProperty(value = "行类型")
    private String lineType;

    @ApiModelProperty(value = "扩展字段1")
    private String etx1;

    @ApiModelProperty(value = "扩展字段2")
    private String etx2;

    @ApiModelProperty(value = "扩展字段3")
    private String etx3;

    @ApiModelProperty(value = "扩展字段4")
    private String etx4;

    @ApiModelProperty(value = "扩展字段5")
    private String etx5;

}
