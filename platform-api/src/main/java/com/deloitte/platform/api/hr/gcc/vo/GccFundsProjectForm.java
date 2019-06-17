package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : LJH
 * @Date : Create in 2019-05-13
 * @Description : GccFundsProject新增修改form对象
 * @Modified :
 */
@ApiModel("新增GccFundsProject表单")
@Data
public class GccFundsProjectForm extends BaseForm {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "申报项目名称")
    private String projectName;

    @ApiModelProperty(value = "申报项目编号")
    private Long projectId;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "是否项目拨款 0否 1是")
    private String projectFunding;

    @ApiModelProperty(value = "申请情况")
    private String apply;

    @ApiModelProperty(value = "经费到账")
    private String fundsAccount;

    @ApiModelProperty(value = "到账金额")
    private String arrivalAmount;

    @ApiModelProperty(value = "到账时间")
    private LocalDateTime accountingDate;

    @ApiModelProperty(value = "拨款情况")
    private String fundingSituation;

    @ApiModelProperty(value = "拨款金额")
    private String allocateFunds;

    @ApiModelProperty(value = "拨款时间")
    private LocalDateTime allocateDate;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "组织代码")
    private String orgCode;

    @ApiModelProperty(value = "经费来源")
    private String fundingSource;

    @ApiModelProperty(value = "拨付标准")
    private String allocatedStandard;

}
