package com.deloitte.platform.api.fssc.budget.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-06
 * @Description :   BudgetDetailingAdjustHead查询from对象
 * @Modified :
 */
@ApiModel("BudgetDetailingAdjustHead查询表单")
@Data
public class BudgetDetailingAdjustHeadQueryForm extends BaseQueryForm<BudgetDetailingAdjustHeadQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "单据编号")
    private String documentNum;

    @ApiModelProperty(value = "单据状态")
    private String documentStatus;

    @ApiModelProperty(value = "归属单位ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "归属单位名称")
    private String unitName;

    @ApiModelProperty(value = "归属部门id")
    private Long deptId;

    @ApiModelProperty(value = "部门编码")
    private String deptCode;

    @ApiModelProperty(value = "归属部门名称")
    private String deptName;

    @ApiModelProperty(value = "申请金费类型")
    private String fundType;

    @ApiModelProperty(value = "年度预算金额-上限")
    private BigDecimal applyTotalAmountUp;

    @ApiModelProperty(value = "年度预算金额-下限")
    private BigDecimal applyTotalAmountDown;

    @ApiModelProperty(value = "制单日期-起始")
    private LocalDateTime makeDocumentTimeStart;

    @ApiModelProperty(value = "制单日期-结束")
    private LocalDateTime makeDocumentTimeEnd;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    @ApiModelProperty(value = "组织路径")
    private String orgPath;

    @ApiModelProperty(value = "申请人")
    private String applyForPerson;

    @ApiModelProperty(value = "申请人名称")
    private String applyForPersonName;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

}
