package com.deloitte.platform.api.fssc.engine.automatic.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description : AvAccountBaseRele新增修改form对象
 * @Modified :
 */
@ApiModel("新增AvAccountBaseRele表单")
@Data
public class AvAccountBaseReleForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "核算要素列ID(COA结构段和其它类型)")
    private Long elementId;

    @ApiModelProperty(value = "会计凭证最基础信息ID")
    private Long baseId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "预留字段1")
    private String etx1;

    @ApiModelProperty(value = "预留字段2")
    private String etx2;

    @ApiModelProperty(value = "预留字段3")
    private String etx3;

}
