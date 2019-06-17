package com.deloitte.platform.api.fssc.bank.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-26
 * @Description :   BankUnitInfo查询from对象
 * @Modified :
 */
@ApiModel("BankUnitInfo查询表单")
@Data
public class BankUnitInfoQueryForm extends BaseQueryForm<BankUnitInfoQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    @ApiModelProperty(value = "单位code")
    private String unitCode;

    @ApiModelProperty(value = "银行账号")
    private String bankAccount;

    @ApiModelProperty(value = "会计科目ID")
    private Long subjectId;

    @ApiModelProperty(value = "会计科目名称")
    private String subjectName;

    @ApiModelProperty(value = "提交的审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "创建人ID")
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @ApiModelProperty(value = "账户性质")
    private String bankType;

    @ApiModelProperty(value = "是否银企直联")
    private String isBankDrectLink;

    @ApiModelProperty(value = "状态")
    private String isValid;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "银行ID")
    private Long bankId;

    private String currencyCode;

    private String branchBankName;

    private String interBranchNumber;

    private String bankCode;

    private String bankName;

    private String unitType;
}
