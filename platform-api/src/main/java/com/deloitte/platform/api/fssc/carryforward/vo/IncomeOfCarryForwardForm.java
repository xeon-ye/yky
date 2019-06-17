package com.deloitte.platform.api.fssc.carryforward.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : chenx
 * @Date : Create in 2019-05-06
 * @Description : IncomeOfCarryForward新增修改form对象
 * @Modified :
 */
@ApiModel("新增IncomeOfCarryForward表单")
@Data
public class IncomeOfCarryForwardForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "支出大类")
    private Long mainTypeId;

    @ApiModelProperty(value = "单据编码")
    private String documentNum;

    @ApiModelProperty(value = "单据类型")
    private String documentType;

    @ApiModelProperty(value = "项目编码")
    private String projectCode;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "归属单位ID")
    private Long unitId;

    @ApiModelProperty(value = "归属部门ID")
    private Long deptId;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "归属部门名称")
    private String deptName;

    @ApiModelProperty(value = "入账日期")
    private LocalDateTime enterDate;

    @ApiModelProperty(value = "金额")
    private Double money;

    @ApiModelProperty(value = "结转状态")
    private String status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "单据编号")
    private Long documentId;

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

    @ApiModelProperty(value = "凭证ID")
    private Long jeHeaderId;
    @ApiModelProperty(value = "支出大类name")
    private String mainTypeName;

}
