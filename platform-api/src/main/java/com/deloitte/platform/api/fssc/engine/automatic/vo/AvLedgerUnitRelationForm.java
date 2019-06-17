package com.deloitte.platform.api.fssc.engine.automatic.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-30
 * @Description : AvLedgerUnitRelation新增修改form对象
 * @Modified :
 */
@ApiModel("新增AvLedgerUnitRelation表单")
@Data
public class AvLedgerUnitRelationForm extends BaseForm {
    private static final long serialVersionUID = 1L;


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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

}
