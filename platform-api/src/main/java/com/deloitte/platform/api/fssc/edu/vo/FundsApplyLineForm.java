package com.deloitte.platform.api.fssc.edu.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-29
 * @Description : FundsApplyLine新增修改form对象
 * @Modified :
 */
@ApiModel("新增FundsApplyLine表单")
@Data
public class FundsApplyLineForm extends BaseForm {
    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "说明")
    private String remark;

    @ApiModelProperty(value = "分配学院ID")
    private Long schoolId;

    @ApiModelProperty(value = "分配学院code")
    private String schoolCode;
    @ApiModelProperty(value = "分配学院名称")
    private String schoolName;

    @ApiModelProperty(value = "申请金额")
    private BigDecimal applyAmount;

    @ApiModelProperty(value = "用途类型")
    private String useType;

    @ApiModelProperty(value = "受款负责人ID")
    private Long recieveUserId;

    @ApiModelProperty(value = "受款负责人姓名")
    private String recieveUserName;

    @ApiModelProperty(value = "受款负责人工号")
    private String recieveEmpNo;

    @ApiModelProperty(value = "申请单位名称")
    private String applyUnitName;

    @ApiModelProperty(value = "拨款负责人")
    private String applyUserName;

    @ApiModelProperty(value = "是否被调整申请关联")
    private String isConnected;

    @ApiModelProperty(value = "行号")
    private Long lineNumber;
}
