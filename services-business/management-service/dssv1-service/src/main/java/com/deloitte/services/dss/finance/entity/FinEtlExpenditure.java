package com.deloitte.services.dss.finance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 一般公共预算支出表
 * </p>
 *
 * @author chitose
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("DSS_FIN_ETL_EXPENDITURE")
@ApiModel(value="FinEtlExpenditure对象", description="一般公共预算支出表")
public class FinEtlExpenditure extends Model<FinEtlExpenditure> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @TableField("ID")
    private Integer id;

    @ApiModelProperty(value = "期间")
    @TableField("PERIOD")
    private String period;

    @ApiModelProperty(value = "机构")
    @TableField("ENTITY")
    private String entity;

    @ApiModelProperty(value = "项目")
    @TableField("ITEM")
    private String item;

    @ApiModelProperty(value = "年初至今金额")
    @TableField("YTD")
    private BigDecimal ytd;

    @ApiModelProperty(value = "年度预算")
    @TableField("BUD_YTD")
    private BigDecimal budYtd;

    @TableField("EX1")
    private String ex1;

    @TableField("EX2")
    private String ex2;

    @TableField("EX3")
    private String ex3;

    @TableField("EX4")
    private String ex4;

    @TableField("EX5")
    private String ex5;


    public static final String ID = "ID";

    public static final String PERIOD = "PERIOD";

    public static final String ENTITY = "ENTITY";

    public static final String ITEM = "ITEM";

    public static final String TOA = "TOA";

    public static final String TC = "TC";

    public static final String EX1 = "EX1";

    public static final String EX2 = "EX2";

    public static final String EX3 = "EX3";

    public static final String EX4 = "EX4";

    public static final String EX5 = "EX5";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
