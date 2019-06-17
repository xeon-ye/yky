package com.deloitte.platform.api.hr.gcc.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : liangjinghai
 * @Date : Create in 2019-04-12
 * @Description :   GccEmployContract查询from对象
 * @Modified :
 */
@ApiModel("GccEmployContract查询表单")
@Data
public class GccEmployContractQueryForm extends BaseQueryForm<GccEmployContractQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "教职工编号")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "所属单位")
    private String unit;

    @ApiModelProperty(value = "合同开始日期")
    private LocalDate contractStartdate;

    @ApiModelProperty(value = "合同结束日期")
    private LocalDate contractEnddate;

    @ApiModelProperty(value = "是否需签合同")
    private String signContract;

    @ApiModelProperty(value = "附件")
    private Long enclosure;

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

    @ApiModelProperty(value = "是否被提醒")
    private String warn;

}
