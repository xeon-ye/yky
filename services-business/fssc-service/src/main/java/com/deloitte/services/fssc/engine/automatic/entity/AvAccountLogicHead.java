package com.deloitte.services.fssc.engine.automatic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 核算逻辑凭证头表信息
 * </p>
 *
 * @author chenx
 * @since 2019-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("AV_ACCOUNT_LOGIC_HEAD")
@ApiModel(value="AvAccountLogicHead对象", description="核算逻辑凭证头表信息")
public class AvAccountLogicHead extends Model<AvAccountLogicHead> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "逻辑凭证ID")
    @TableField("LOGIC_ID")
    private Long logicId;

    @ApiModelProperty(value = "判断条件")
    @TableField("JUDGE_CONDITION")
    private String judgeCondition;

    @ApiModelProperty(value = "业务类别来源")
    @TableField("TYPE_FROM")
    private String typeFrom;

    @ApiModelProperty(value = "附件数量来源")
    @TableField("NUM_FROM")
    private String numFrom;

    @ApiModelProperty(value = "币种来源")
    @TableField("VOUCHER_FROM")
    private String voucherFrom;

    @ApiModelProperty(value = "汇率来源")
    @TableField("RATE_FROM")
    private String rateFrom;

    @ApiModelProperty(value = "头说明来源")
    @TableField("HEAD_FROM")
    private String headFrom;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_DATE")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人")
    @TableField("CRRETE_BY")
    private Long crreteBy;

    @ApiModelProperty(value = "修改时间")
    @TableField("UPDATE_DATE")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "预留属性1")
    @TableField("ETX_1")
    private String etx1;

    @ApiModelProperty(value = "预留属性2")
    @TableField("ETX_2")
    private String etx2;

    @ApiModelProperty(value = "预留属性3")
    @TableField("ETX_3")
    private String etx3;

    @ApiModelProperty(value = "预留属性4")
    @TableField("ETX_4")
    private String etx4;

    @ApiModelProperty(value = "预留属性5")
    @TableField("ETX_5")
    private String etx5;


    public static final String ID = "ID";

    public static final String LOGIC_ID = "LOGIC_ID";

    public static final String JUDGE_CONDITION = "JUDGE_CONDITION";

    public static final String TYPE_FROM = "TYPE_FROM";

    public static final String NUM_FROM = "NUM_FROM";

    public static final String VOUCHER_FROM = "VOUCHER_FROM";

    public static final String RATE_FROM = "RATE_FROM";

    public static final String HEAD_FROM = "HEAD_FROM";

    public static final String CREATE_DATE = "CREATE_DATE";

    public static final String CRRETE_BY = "CRRETE_BY";

    public static final String UPDATE_DATE = "UPDATE_DATE";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String ETX_1 = "ETX_1";

    public static final String ETX_2 = "ETX_2";

    public static final String ETX_3 = "ETX_3";

    public static final String ETX_4 = "ETX_4";

    public static final String ETX_5 = "ETX_5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
