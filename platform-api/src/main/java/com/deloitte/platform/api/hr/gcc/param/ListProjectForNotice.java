package com.deloitte.platform.api.hr.gcc.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :   GccProDecNotice查询from对象
 * @Modified :
 */
@ApiModel("GccProDecNotice查询表单")
@Data
public class ListProjectForNotice {

    @ApiModelProperty(value = "是否立即发布 0 否，1是")
    private String publishNow;

    @ApiModelProperty(value = "申请年份")
    private String applyYear;

    @ApiModelProperty(value = "类型（所院/院校）")
    private String type;

    @ApiModelProperty(value = "批次")
    private String batch;

    @ApiModelProperty(value = "是否有效")
    private String status;

    @ApiModelProperty(value = "所院是否启动0否1是")
    private String acaStart;


}
