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
 * @Description : AvChartOfAccount新增修改form对象
 * @Modified :
 */
@ApiModel("新增AvChartOfAccount表单")
@Data
public class AvChartOfAccountForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "账套ID")
    private Long ledgerId;
    @ApiModelProperty(value = "科目表ID（跟COA关联）")
    @TableField("CHART_OF_ACCOUNTS_ID")
    private Long chartOfAccountsId;
    @ApiModelProperty(value = "账套名称")
    private String name;

    @ApiModelProperty(value = "账套说明")
    private String description;

    @ApiModelProperty(value = "币种")
    private String currencyCode;

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

    @ApiModelProperty(value = "简称")
    private String shortName;

    @ApiModelProperty(value = "是否有效")
    private String status;

}
