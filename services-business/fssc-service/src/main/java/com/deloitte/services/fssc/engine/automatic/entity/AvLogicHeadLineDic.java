package com.deloitte.services.fssc.engine.automatic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 凭证逻辑头表和行表信息关联信息表
 * </p>
 *
 * @author chenx
 * @since 2019-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("AV_LOGIC_HEAD_LINE_DIC")
@ApiModel(value="AvLogicHeadLineDic对象", description="凭证逻辑头表和行表信息关联信息表")
public class AvLogicHeadLineDic extends Model<AvLogicHeadLineDic> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "单据类型")
    @TableField("DOCUMENT_MODULE")
    private String documentModule;

    @ApiModelProperty(value = "头表")
    @TableField("HEAD_TABLE")
    private String headTable;

    @ApiModelProperty(value = "行表")
    @TableField("LINE_TABLE")
    private String lineTable;

    @ApiModelProperty(value = "行类型")
    @TableField("LINE_TYPE")
    private String lineType;

    @ApiModelProperty(value = "扩展字段1")
    @TableField("ETX_1")
    private String etx1;

    @ApiModelProperty(value = "扩展字段2")
    @TableField("ETX_2")
    private String etx2;

    @ApiModelProperty(value = "扩展字段3")
    @TableField("ETX_3")
    private String etx3;

    @ApiModelProperty(value = "扩展字段4")
    @TableField("ETX_4")
    private String etx4;

    @ApiModelProperty(value = "扩展字段5")
    @TableField("ETX_5")
    private String etx5;


    public static final String ID = "ID";

    public static final String DOCUMENT_MODULE = "DOCUMENT_MODULE";

    public static final String HEAD_TABLE = "HEAD_TABLE";

    public static final String LINE_TABLE = "LINE_TABLE";

    public static final String LINE_TYPE = "LINE_TYPE";

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
