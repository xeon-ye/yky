package com.deloitte.services.dss.finance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 三年项目库预处理表
 * </p>
 *
 * @author chitose
 * @since 2019-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("DSS_FIN_ETL_PROJECT_PRET")
@ApiModel(value="DssFinEtlProjectPret对象", description="三年项目库预处理表")
public class FinEtlProjectPret extends Model<FinEtlProjectPret> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @TableField("ID")
    @TableId(value = "ID",type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "指标编码")
    @TableField("INDEX_CODE")
    private String indexCode;

    @ApiModelProperty(value = "单位编码")
    @TableField("COM_CODE")
    private String comCode;

    @TableField("SPARE1")
    private Double spare1;

    @TableField("SPARE2")
    private Double spare2;

    @TableField("SPARE3")
    private Double spare3;

    @ApiModelProperty(value = "支出计划 T+1")
    @TableField("YTD_T1")
    private BigDecimal ytdT1;

    @ApiModelProperty(value = "支出计划 T+2")
    @TableField("YTD_T2")
    private BigDecimal ytdT2;

    @ApiModelProperty(value = "支出计划 T+3")
    @TableField("YTD_T3")
    private BigDecimal ytdT3;

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

    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    public static final String ID = "ID";

    public static final String INDEX_CODE = "INDEX_CODE";

    public static final String COM_CODE = "COM_CODE";

    public static final String SPARE1 = "SPARE1";

    public static final String SPARE2 = "SPARE2";

    public static final String SPARE3 = "SPARE3";

    public static final String YTD_T1 = "YTD_T1";

    public static final String YTD_T2 = "YTD_T2";

    public static final String YTD_T3 = "YTD_T3";

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
