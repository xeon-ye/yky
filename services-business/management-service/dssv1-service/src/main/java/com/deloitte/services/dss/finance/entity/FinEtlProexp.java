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
 * 一般公共预算项目支出表
 * </p>
 *
 * @author chitose
 * @since 2019-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("DSS_FIN_ETL_PROEXP")
@ApiModel(value="DssFinEtlProexp对象", description="一般公共预算项目支出表")
public class FinEtlProexp extends Model<FinEtlProexp> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "期间")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "科目编码")
    @TableField("ACCOUNT_CODE")
    private String accountCode;

    @ApiModelProperty(value = "科目名称")
    @TableField("ACCOUNT _NAME")
    private String accountName;

    @ApiModelProperty(value = "项目名称")
    @TableField("ITEM_NAME")
    private String itemName;

    @ApiModelProperty(value = "项目代码")
    @TableField("ITEM_CODE")
    private String itemCode;

    @ApiModelProperty(value = "项目单位")
    @TableField("ENTITY_NAME")
    private String entityName;

    @ApiModelProperty(value = "项目密级")
    @TableField("ENTITY_LV")
    private String entityLv;

    @ApiModelProperty(value = "是否发改委基建项目")
    @TableField("TAG")
    private String tag;

    @ApiModelProperty(value = "项目周期")
    @TableField("PERIOD")
    private String period;

    @ApiModelProperty(value = "项目支出-小计")
    @TableField("BUD_TOTAL")
    private BigDecimal budTotal;

    @ApiModelProperty(value = "项目支出-本年拨款")
    @TableField("BUD_NOW")
    private BigDecimal budNow;

    @ApiModelProperty(value = "项目支出-结转资金")
    @TableField("BUD_TRANS")
    private BigDecimal budTrans;

    @ApiModelProperty(value = "项目支出-教育收费安排支出")
    @TableField("BUD_EDU")
    private BigDecimal budEdu;

    @ApiModelProperty(value = "项目支出-其他资金")
    @TableField("BUD_OTHER")
    private BigDecimal budOther;

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

    public static final String YEAR = "YEAR";

    public static final String ACCOUNT_CODE = "ACCOUNT_CODE";

    public static final String ACCOUNT_NAME = "ACCOUNT _NAME";

    public static final String ITEM_NAME = "ITEM_NAME";

    public static final String ITEM_CODE = "ITEM_CODE";

    public static final String ENTITY_NAME = "ENTITY_NAME";

    public static final String ENTITY_LV = "ENTITY_LV";

    public static final String TAG = "TAG";

    public static final String PERIOD = "PERIOD";

    public static final String BUD_TOTAL = "BUD_TOTAL";

    public static final String BUD_NOW = "BUD_NOW";

    public static final String BUD_TRANS = "BUD_TRANS";

    public static final String BUD_EDU = "BUD_EDU";

    public static final String BUD_OTHER = "BUD_OTHER";

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
