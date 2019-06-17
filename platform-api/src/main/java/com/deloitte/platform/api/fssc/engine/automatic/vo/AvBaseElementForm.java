package com.deloitte.platform.api.fssc.engine.automatic.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description : AvBaseElement新增修改form对象
 * @Modified :
 */
@ApiModel("新增AvBaseElement表单")
@Data
public class AvBaseElementForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "数据类型(ACCOUNT/CURRENCY/VOUCHER)")
    private String dataType;

    @ApiModelProperty(value = "数据编码")
    private String dataCode;

    @ApiModelProperty(value = "数据名称")
    private String dataDesc;

    @ApiModelProperty(value = "数据状态（N/Y）")
    private String dataStatus;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "预留字段1")
    private String etx1;

    @ApiModelProperty(value = "预留字段2")
    private String etx2;

    @ApiModelProperty(value = "预留字段3")
    private String etx3;

    @ApiModelProperty(value = "预留字段4")
    private String etx4;

    @ApiModelProperty(value = "预留字段5")
    private String etx5;

    @ApiModelProperty(value = "是否是父类")
    private String summaryFlag;


}
