package com.deloitte.platform.api.hr.check.param;

import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author : woo
 * @Date : Create in 2019-04-09
 * @Description :   CheckTime查询from对象
 * @Modified :
 */
@ApiModel("CheckTime查询表单")
@Data
public class CheckTimeQueryForm extends BaseQueryForm<CheckTimeQueryParam>  {


    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "考核年度")
    private String checkYear;

    @ApiModelProperty(value = "考核期间名称")
    private String checkTimeName;

    @ApiModelProperty(value = "考核期间类型")
    private String checkTimeType;

    @ApiModelProperty(value = "开始日期")
    private LocalDate startTime;

    @ApiModelProperty(value = "结束日期")
    private LocalDate endTime;

    @ApiModelProperty(value = "是否有效")
    private String isValid;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "组织code")
    private String orgCode;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
