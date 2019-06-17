package com.deloitte.platform.api.fssc.engine.manual.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : chenx
 * @Date : Create in 2019-05-08
 * @Description : AvCarryManualRelation新增修改form对象
 * @Modified :
 */
@ApiModel("新增AvCarryManualRelation表单")
@Data
public class AvCarryManualRelationForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "凭证ID")
    private Long jeHeaderId;

    @ApiModelProperty(value = "结转ID")
    private Long carrayId;

}
