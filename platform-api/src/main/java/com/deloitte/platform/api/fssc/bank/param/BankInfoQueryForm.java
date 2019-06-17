package com.deloitte.platform.api.fssc.bank.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-26
 * @Description :   BankInfo查询from对象
 * @Modified :
 */
@ApiModel("BankInfo查询表单")
@Data
public class BankInfoQueryForm extends BaseQueryForm<BankInfoQueryParam>  {


    @ApiModelProperty(value = "银行ID")
    private Long id;

    @ApiModelProperty(value = "银行代码")
    private String bankCode;

    @ApiModelProperty(value = "银行名称")
    private String bankName;

    @ApiModelProperty(value = "分行名称")
    private String branchBankName;

    @ApiModelProperty(value = "银行简称")
    private String bankSimpleName;

    @ApiModelProperty(value = "联行号")
    private String interBranchNumber;

    @ApiModelProperty(value = "银行国际代码")
    private String bankInternationalCode;

    @ApiModelProperty(value = "地址")
    private String address;

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

    @ApiModelProperty(value = "版本")
    private Long version;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态")
    private String isValid;
    @ApiModelProperty(value = "提交审核状态")
    private String auditAStatus;
}
