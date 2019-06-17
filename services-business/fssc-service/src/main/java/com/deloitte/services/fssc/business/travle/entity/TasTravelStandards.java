package com.deloitte.services.fssc.business.travle.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hjy
 * @since 2019-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("TAS_TRAVEL_STANDARDS")
@ApiModel(value="TasTravelStandards对象", description="")
public class TasTravelStandards extends Model<TasTravelStandards> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "行政区划代码")
    @TableField("NATION_ADMIN_CODE")
    private String nationAdminCode;

    @ApiModelProperty(value = "国家")
    @TableField("COUNTRY")
    private String country;

    @ApiModelProperty(value = "地点名称")
    @TableField("PLACE_NAME")
    private String placeName;

    @ApiModelProperty(value = "部级标准")
    @TableField("MINISTERIAL_LEVEL")
    private BigDecimal ministerialLevel;

    @ApiModelProperty(value = "司局级标准")
    @TableField("SECRETARIES")
    private BigDecimal secretaries;

    @ApiModelProperty(value = "其他人员标准")
    @TableField("OTHER_PERSONNEL")
    private BigDecimal otherPersonnel;

    @ApiModelProperty(value = "伙食补助")
    @TableField("FOOD_ALLOWANCE")
    private BigDecimal foodAllowance;

    @ApiModelProperty(value = "交通补助")
    @TableField("TRAFFIC_SUBSIDY")
    private BigDecimal trafficSubsidy;

    @ApiModelProperty(value = "旺季月份")
    @TableField("PEAK_MONTH")
    private String peakMonth;


    public static final String ID = "ID";

    public static final String NATION_ADMIN_CODE = "行政区划代码";

    public static final String COUNTRY = "国家";

    public static final String PLACE_NAME = "地点名称";

    public static final String MINISTERIAL_LEVEL = "部级住宿标准";

    public static final String SECRETARIES = "司局级住宿标准";

    public static final String OTHER_PERSONNEL = "其他人员住宿标准";

    public static final String FOOD_ALLOWANCE = "伙食费补助标准";

    public static final String TRAFFIC_SUBSIDY = "交通费补助标准";

    public static final String PEAK_MONTH = "旺季月份";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
