package com.deloitte.services.dss.finance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 部门收支总表
 * </p>
 *
 * @author chitose
 * @since 2019-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("DSS_FIN_ETL_INCOME")
@ApiModel(value="FinEtlIncome对象", description="部门收支总表")
public class FinEtlIncome extends Model<FinEtlIncome> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @TableField("ID")
    private Long id;

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

    @TableField("EX6")
    private String ex6;

    @TableField("EX7")
    private String ex7;

    @TableField("EX8")
    private String ex8;

    @TableField("EX9")
    private String ex9;

    @TableField("EX10")
    private String ex10;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private String createTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private String updateTime;


    public static final String ID = "ID";

    public static final String PERIOD = "PERIOD";

    public static final String ENTITY = "ENTITY";

    public static final String ITEM = "ITEM";

    public static final String YTD = "YTD";

    public static final String BUD_YTD = "BUD_YTD";

    public static final String EX1 = "EX1";

    public static final String EX2 = "EX2";

    public static final String EX3 = "EX3";

    public static final String EX4 = "EX4";

    public static final String EX5 = "EX5";

    public static final String EX6 = "EX6";

    public static final String EX7 = "EX7";

    public static final String EX8 = "EX8";

    public static final String EX9 = "EX9";

    public static final String EX10 = "EX10";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
