package com.deloitte.platform.basic.bpmservice.entity;

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
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jackliu
 * @since 2019-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BPM_POSITION_RELATIONSHIP")
@ApiModel(value="BpmPositionRelationship对象", description="")
public class BpmPositionRelationship extends Model<BpmPositionRelationship> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关系表ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "BMP虚拟岗位ID")
    @TableField("BPM_POSITION_ID")
    private Long bpmPositionId;

    @ApiModelProperty(value = "实体岗位ID")
    @TableField("PHYSICAL_POSITION_ID")
    private Long physicalPositionId;

    @ApiModelProperty(value = "负责组织ID")
    @TableField("CHARGE_ORG_ID")
    private Long chargeOrgId;

    @ApiModelProperty(value = "状态")
    @TableField("STATE")
    private String state;

    @ApiModelProperty(value = "生效时间")
    @TableField("EFFECT_TIME")
    private LocalDateTime effectTime;

    @ApiModelProperty(value = "失效时间")
    @TableField("LOSE_EFFICACY_TIME")
    private LocalDateTime loseEfficacyTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    public static final String ID = "ID";

    public static final String BPM_POSITION_ID = "BPM_POSITION_ID";

    public static final String PHYSICAL_POSITION_ID = "PHYSICAL_POSITION_ID";

    public static final String CHARGE_ORG_ID = "CHARGE_ORG_ID";

    public static final String STATE = "STATE";

    public static final String EFFECT_TIME = "EFFECT_TIME";

    public static final String LOSE_EFFICACY_TIME = "LOSE_EFFICACY_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
