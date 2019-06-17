package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-25
 * @Description : ZpcpBudget新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpBudget表单")
@Data
public class ZpcpBudgetForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "职工编号")
    private String empCode;

    @ApiModelProperty(value = "在聘信息表id")
    private Long infoId;

    @ApiModelProperty(value = "基本年薪总额")
    private Long totalSalay;

    @ApiModelProperty(value = "安家预算总额（元）")
    private Long totalSettle;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "年度")
    private String year;

    @ApiModelProperty(value = "拨付安家费")
    private Long settlPay;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

}
