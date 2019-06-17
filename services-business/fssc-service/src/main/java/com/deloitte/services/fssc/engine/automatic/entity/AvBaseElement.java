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
 * COA_凭证类别_币种 三种类型基础数据
 * </p>
 *
 * @author chenx
 * @since 2019-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("AV_BASE_ELEMENT")
@ApiModel(value="AvBaseElement对象", description="COA_凭证类别_币种 三种类型基础数据")
public class AvBaseElement extends Model<AvBaseElement> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "数据类型(ACCOUNT/CURRENCY/VOUCHER)")
    @TableField("DATA_TYPE")
    private String dataType;

    @ApiModelProperty(value = "数据编码")
    @TableField("DATA_CODE")
    private String dataCode;

    @ApiModelProperty(value = "数据名称")
    @TableField("DATA_DESC")
    private String dataDesc;

    @ApiModelProperty(value = "数据状态（N/Y）")
    @TableField("DATA_STATUS")
    private String dataStatus;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_DATE")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "预留字段1")
    @TableField("ETX_1")
    private String etx1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("ETX_2")
    private String etx2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("ETX_3")
    private String etx3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("ETX_4")
    private String etx4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("ETX_5")
    private String etx5;

    @ApiModelProperty(value = "是否是父类")
    @TableField("SUMMARY_FLAG")
    private String summaryFlag;


    public static final String ID = "ID";

    public static final String DATA_TYPE = "DATA_TYPE";

    public static final String DATA_CODE = "DATA_CODE";

    public static final String DATA_DESC = "DATA_DESC";

    public static final String DATA_STATUS = "DATA_STATUS";

    public static final String CREATE_DATE = "CREATE_DATE";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String ETX_1 = "ETX_1";

    public static final String ETX_2 = "ETX_2";

    public static final String ETX_3 = "ETX_3";

    public static final String ETX_4 = "ETX_4";

    public static final String ETX_5 = "ETX_5";

    public static final String SUMMARY_FLAG = "SUMMARY_FLAG";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
