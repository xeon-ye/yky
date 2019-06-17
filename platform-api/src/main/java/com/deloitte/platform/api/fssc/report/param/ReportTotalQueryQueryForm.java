package com.deloitte.platform.api.fssc.report.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author : jaws
 * @Date : Create in 2019-06-12
 * @Description :   ReportTotalQuery查询from对象
 * @Modified :
 */
@ApiModel("ReportTotalQuery查询表单")
@Data
public class ReportTotalQueryQueryForm extends BaseQueryForm<ReportTotalQueryQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    @ApiModelProperty(value = "报表类型")
    private String reportType;

    @ApiModelProperty(value = "报表名称")
    private String reportName;

    @ApiModelProperty(value = "报表ID")
    private Long reportId;

    @ApiModelProperty(value = "年份")
    private String year;

    @ApiModelProperty(value = "月份")
    private String month;

    @ApiModelProperty(value = "报表状态")
    private String reportStatus;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人")
    private Long updateBy;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "属性1")
    private String ext1;

    @ApiModelProperty(value = "属性2")
    private String ext2;

    @ApiModelProperty(value = "属性3")
    private String ext3;

    @ApiModelProperty(value = "属性4")
    private String ext4;

    @ApiModelProperty(value = "属性5")
    private String ext5;

    @ApiModelProperty(value = "周期类型(Y:年度，M:月)")
    private String periodType;

    @ApiModelProperty(value = "是否多个单位合并")
    private String mergeFlag;
}
