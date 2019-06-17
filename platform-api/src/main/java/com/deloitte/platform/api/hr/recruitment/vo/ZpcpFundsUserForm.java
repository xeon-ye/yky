package com.deloitte.platform.api.hr.recruitment.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-25
 * @Description : ZpcpFundsUser新增修改form对象
 * @Modified :
 */
@ApiModel("新增ZpcpFundsUser表单")
@Data
public class ZpcpFundsUserForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "教职工编号")
    private String empCode;

    @ApiModelProperty(value = "拨付时间")
    private String disbursementTime;

    @ApiModelProperty(value = "使用原因")
    private String useReason;

    @ApiModelProperty(value = "经费类型(0.年薪金额,1安家费)")
    private String fundsType;

    @ApiModelProperty(value = "组织代码")
    private String organizationCode;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "拨付金额")
    private Double amount;

    @ApiModelProperty(value = "在聘信息表id")
    private Long infoId;
}
