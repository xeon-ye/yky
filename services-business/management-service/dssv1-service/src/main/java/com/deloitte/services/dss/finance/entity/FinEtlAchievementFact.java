package com.deloitte.services.dss.finance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 绩效事实表
 * </p>
 *
 * @author chitose
 * @since 2019-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("DSS_FIN_ETL_ACHIEVEMENT_FACT")
@ApiModel(value="DssFinEtlAchievementFact对象", description="绩效事实表")
public class FinEtlAchievementFact extends Model<FinEtlAchievementFact> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "指标编码")
    @TableField("INDEX_CODE")
    private String indexCode;

    @ApiModelProperty(value = "期间")
    @TableField("PERIOD_CODE")
    private String periodCode;

    @ApiModelProperty(value = "单位编码")
    @TableField("COM_CODE")
    private String comCode;

    @TableField("SPARE1")
    private Double spare1;

    @TableField("SPARE2")
    private Double spare2;

    @TableField("SPARE3")
    private Double spare3;

    @ApiModelProperty(value = "月发生")
    @TableField("PTD")
    private BigDecimal ptd;

    @ApiModelProperty(value = "月发生—分子")
    @TableField("PTD_N")
    private BigDecimal ptdN;

    @ApiModelProperty(value = "月发生—分母")
    @TableField("PTD_D")
    private BigDecimal ptdD;

    @ApiModelProperty(value = "年累计")
    @TableField("YTD")
    private BigDecimal ytd;

    @ApiModelProperty(value = "年累计—分子")
    @TableField("YTD_N")
    private BigDecimal ytdN;

    @ApiModelProperty(value = "年累计—分母")
    @TableField("YTD_D")
    private BigDecimal ytdD;

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

    public static final String PERIOD_CODE = "PERIOD_CODE";

    public static final String COM_CODE = "COM_CODE";

    public static final String SPARE1 = "SPARE1";

    public static final String SPARE2 = "SPARE2";

    public static final String SPARE3 = "SPARE3";

    public static final String PTD = "PTD";

    public static final String PTD_N = "PTD_N";

    public static final String PTD_D = "PTD_D";

    public static final String YTD = "YTD";

    public static final String YTD_N = "YTD_N";

    public static final String YTD_D = "YTD_D";

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
        return this.id;
    }

}
