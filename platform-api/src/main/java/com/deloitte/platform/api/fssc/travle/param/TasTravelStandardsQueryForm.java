package com.deloitte.platform.api.fssc.travle.param;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-17
 * @Description :   TasTravelStandards查询from对象
 * @Modified :
 */
@ApiModel("TasTravelStandards查询表单")
@Data
public class TasTravelStandardsQueryForm extends BaseQueryForm<TasTravelStandardsQueryParam>  {


    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "行政区划代码")
    private String nationAdminCode;

    @ApiModelProperty(value = "国家")
    private String country;

    @ApiModelProperty(value = "地点名称")
    private String placeName;

    @ApiModelProperty(value = "部级标准")
    private BigDecimal ministerialLevel;

    @ApiModelProperty(value = "司局级标准")
    private BigDecimal secretaries;

    @ApiModelProperty(value = "其他人员标准")
    private BigDecimal otherPersonnel;

    @ApiModelProperty(value = "伙食补助")
    private BigDecimal foodAllowance;

    @ApiModelProperty(value = "交通补助")
    private BigDecimal trafficSubsidy;

    @ApiModelProperty(value = "旺季月份")
    private String peakMonth;

    @ApiModelProperty(value = "创建开始时间")
    private LocalDateTime timeStart;

    @ApiModelProperty(value="创建结束时间")
    private LocalDateTime timeEnd;

    @ApiModelProperty(value="差旅费类型：subsidies，transportation,accommodation ,expenses")
    private String  travelType;

    @ApiModelProperty(value="人员级别")
    private String personnelLevel;
}
