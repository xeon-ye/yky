package com.deloitte.platform.api.hr.gcc.vo;

import com.deloitte.platform.common.core.entity.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GccHighLevelFundsVo extends BaseVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "申报项目名称")
    private String projectName;

    @ApiModelProperty(value = "申报项目编号")
    private String projectId;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "是否项目拨款")
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

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createBy;

    @ApiModelProperty(value = "修改人id")
    private Long updateBy;

    @ApiModelProperty(value = "经费来源")
    private String fundingSource;

    @ApiModelProperty(value = "拨付标准")
    private String allocatedStandard;

    @ApiModelProperty(value = "用户编号")
    private String userId;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "人员申领情况 0未申领，1 申领")
    private String applicationSituation;

    @ApiModelProperty(value = " 项目到账情况 0未到账，1 已到账")
    private String projectFundsAccount;

    @ApiModelProperty(value = "项目到账金额")
    private String projectArrivalAmount;

    @ApiModelProperty(value = "项目到账时间")
    private LocalDate projectAccountingDate;

    @ApiModelProperty(value = "项目拨款情况 0未拨款，1 已拨款")
    private String projectFundingSituation;

    @ApiModelProperty(value = "拨款金额")
    private String projectAllocateFunds;

    @ApiModelProperty(value = "拨款时间")
    private LocalDate projectAllocateDate;
}


