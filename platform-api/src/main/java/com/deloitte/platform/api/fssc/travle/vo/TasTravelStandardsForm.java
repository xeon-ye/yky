package com.deloitte.platform.api.fssc.travle.vo;
import com.deloitte.platform.common.core.entity.form.BaseForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.Blob;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-17
 * @Description : TasTravelStandards新增修改form对象
 * @Modified :
 */
@ApiModel("新增TasTravelStandards表单")
@Data
public class TasTravelStandardsForm extends BaseForm {
    private static final long serialVersionUID = 1L;

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

}
