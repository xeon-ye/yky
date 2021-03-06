package com.deloitte.platform.api.hr.staffAllotment.param;


import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-03
 * @Description :   HrInternalTransfer查询from对象
 * @Modified :
 */
@ApiModel("HrInternalTransfer查询表单")
@Data
public class HrInternalTransferQueryForm extends BaseQueryForm<HrInternalTransferQueryParam> {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "申请部门")
    private String department;

    @ApiModelProperty(value = "申请岗位")
    private String position;

    @ApiModelProperty(value = "申请说明")
    private String explain;

    @ApiModelProperty(value = "申请状态：1.未提交 2.已提交")
    private String status;

    @ApiModelProperty(value = "申请人编号")
    private String staffCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String careteBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "申请人单位")
    private String company;

    @ApiModelProperty(value = "组织编码")
    private String orgCode;
}
