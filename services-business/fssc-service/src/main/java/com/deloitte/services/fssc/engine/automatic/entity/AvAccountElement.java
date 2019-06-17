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
 * 核算要素信息
 * </p>
 *
 * @author chenx
 * @since 2019-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("AV_ACCOUNT_ELEMENT")
@ApiModel(value="AvAccountElement对象", description="核算要素信息")
public class AvAccountElement extends Model<AvAccountElement> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "科目表ID（跟COA关联）")
    @TableField("CHART_OF_ACCOUNTS_ID")
    private Long chartOfAccountsId;

    @ApiModelProperty(value = "核算要素编码")
    @TableField("SEGMENT_CODE")
    private String segmentCode;

    @ApiModelProperty(value = "核算要素名称")
    @TableField("SEGMENT_DESC")
    private String segmentDesc;

    @ApiModelProperty(value = "COA的序号")
    @TableField("SEGMENT_NUM")
    private Long segmentNum;

    @ApiModelProperty(value = "核算要素值(N/Y)")
    @TableField("HAS_VALUE")
    private String hasValue;

    @ApiModelProperty(value = "是否有效(N/Y)")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "要素类型(COA/HEAD/LINE)")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "数据来源")
    @TableField("DATA_FROM")
    private String dataFrom;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_DATE")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_DATE")
    private LocalDateTime updateDate;

    @ApiModelProperty(value = "更新人")
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

    @ApiModelProperty(value = "COA值级")
    @TableField("SEGMENT_TYPE")
    private String segmentType;



    public static final String ID = "ID";

    public static final String CHART_OF_ACCOUNTS_ID = "CHART_OF_ACCOUNTS_ID";

    public static final String SEGMENT_CODE= "SEGMENT_CODE";

    public static final String SEGMENT_DESC = "SEGMENT_DESC";

    public static final String SEGMENT_NUM = "SEGMENT_NUM";

    public static final String HAS_VALUE = "HAS_VALUE";

    public static final String STATUS = "STATUS";

    public static final String TYPE = "TYPE";

    public static final String DATA_FROM = "DATA_FROM";

    public static final String CREATE_DATE = "CREATE_DATE";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_DATE = "UPDATE_DATE";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String ETX_1 = "ETX_1";

    public static final String ETX_2 = "ETX_2";

    public static final String ETX_3 = "ETX_3";

    public static final String ETX_4 = "ETX_4";

    public static final String ETX_5 = "ETX_5";

    public static final String SEGMENT_TYPE = "SEGMENT_TYPE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
