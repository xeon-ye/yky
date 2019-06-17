package com.deloitte.services.dss.finance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 国库系统（实际项目支出）
 * </p>
 *
 * @author chitose
 * @since 2019-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("DSS_FIN_ETL_NATIONAL")
@ApiModel(value="DssFinEtlNational对象", description="国库系统（实际项目支出）")
public class FinEtlNational extends Model<FinEtlNational> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "年度")
    @TableField("BUD_YEAR")
    private String budYear;

    @ApiModelProperty(value = "单位编码")
    @TableField("ENTITY_CODE")
    private String entityCode;

    @ApiModelProperty(value = "单位预算码")
    @TableField("ENTITY_BUD_CODE")
    private String entityBudCode;

    @ApiModelProperty(value = "单位名称")
    @TableField("ENTITY_NAME")
    private String entityName;

    @ApiModelProperty(value = "项目编码")
    @TableField("ITEM_CODE")
    private String itemCode;

    @ApiModelProperty(value = "项目名称")
    @TableField("ITEM_NAME")
    private String itemName;

    @ApiModelProperty(value = "预算信息关联号")
    @TableField("BUD_INFO_CODE")
    private String budInfoCode;

    @ApiModelProperty(value = "科目编码")
    @TableField("ACC_CODE")
    private String accCode;

    @ApiModelProperty(value = "科目名称")
    @TableField("ACC_NAME")
    private String accName;

    @ApiModelProperty(value = "年度指标")
    @TableField("YEAR_TAG")
    private BigDecimal yearTag;

    @ApiModelProperty(value = "直接支付(计划)")
    @TableField("PLAN_DIRECT")
    private BigDecimal planDirect;

    @ApiModelProperty(value = "授权支付(计划)")
    @TableField("PLAN_AUTHORIZE")
    private BigDecimal planAuthorize;

    @ApiModelProperty(value = "支出合计(计划)")
    @TableField("PLAN_TOTAL")
    private BigDecimal planTotal;

    @ApiModelProperty(value = "直接支付(支出)")
    @TableField("EXP_DIRECT")
    private BigDecimal expDirect;

    @ApiModelProperty(value = "授权支付(支出)")
    @TableField("EXP_AUTHORIZE")
    private BigDecimal expAuthorize;

    @ApiModelProperty(value = "支出合计(支出)")
    @TableField("EXP_OTAL")
    private BigDecimal expOtal;

    @ApiModelProperty(value = "剩余指标（年度指标-支出）")
    @TableField("SURPLUS_INDEX")
    private BigDecimal surplusIndex;

    @ApiModelProperty(value = "剩余(计划-支出)")
    @TableField("SURPLUS")
    private BigDecimal surplus;

    @ApiModelProperty(value = "实际支出/年度指标")
    @TableField("FACT_NOW")
    private BigDecimal factNow;

    @ApiModelProperty(value = "实际支出/计划")
    @TableField("FACT_PLAN")
    private BigDecimal factPlan;

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

    public static final String BUD_YEAR = "BUD_YEAR";

    public static final String ENTITY_CODE = "ENTITY_CODE";

    public static final String ENTITY_BUD_CODE = "ENTITY_BUD_CODE";

    public static final String ENTITY_NAME = "ENTITY_NAME";

    public static final String ITEM_CODE = "ITEM_CODE";

    public static final String ITEM_NAME = "ITEM_NAME";

    public static final String BUD_INFO_CODE = "BUD_INFO_CODE";

    public static final String ACC_CODE = "ACC_CODE";

    public static final String ACC_NAME = "ACC_NAME";

    public static final String YEAR_TAG = "YEAR_TAG";

    public static final String PLAN_DIRECT = "PLAN_DIRECT";

    public static final String PLAN_AUTHORIZE = "PLAN_AUTHORIZE";

    public static final String PLAN_TOTAL = "PLAN_TOTAL";

    public static final String EXP_DIRECT = "EXP_DIRECT";

    public static final String EXP_AUTHORIZE = "EXP_AUTHORIZE";

    public static final String EXP_OTAL = "EXP_OTAL";

    public static final String SURPLUS_INDEX = "SURPLUS_INDEX";

    public static final String SURPLUS = "SURPLUS";

    public static final String FACT_NOW = "FACT_NOW";

    public static final String FACT_PLAN = "FACT_PLAN";

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
