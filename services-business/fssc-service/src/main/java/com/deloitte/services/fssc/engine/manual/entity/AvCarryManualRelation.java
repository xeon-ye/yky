package com.deloitte.services.fssc.engine.manual.entity;

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
 * 关联
 * </p>
 *
 * @author chenx
 * @since 2019-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("AV_CARRY_MANUAL_RELATION")
@ApiModel(value="AvCarryManualRelation对象", description="关联")
public class AvCarryManualRelation extends Model<AvCarryManualRelation> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "凭证ID")
    @TableField("JE_HEADER_ID")
    private Long jeHeaderId;

    @ApiModelProperty(value = "结转ID")
    @TableField("CARRAY_ID")
    private Long carrayId;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;


    public static final String ID = "ID";

    public static final String JE_HEADER_ID = "JE_HEADER_ID";

    public static final String CARRAY_ID = "CARRAY_ID";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
