package com.deloitte.services.fssc.base.entity;

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
 * 
 * </p>
 *
 * @author hjy
 * @since 2019-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BASE_CONTENT_COMMITMENT_UNIT")
@ApiModel(value="BaseContentCommitmentUnit对象", description="")
public class BaseContentCommitmentUnit extends Model<BaseContentCommitmentUnit> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "承诺书内容ID")
    @TableField("CONTENT_COMMITMENT_ID")
    private Long contentCommitmentId;

    @ApiModelProperty(value = "组织名称")
    @TableField("ORG_UNIT_NAME")
    private String orgUnitName;

    @ApiModelProperty(value = "组织ID")
    @TableField("ORG_UNIT_ID")
    private Long orgUnitId;

    @ApiModelProperty(value = "是否有效")
    @TableField("VALID_FLAG")
    private String validFlag;

    @ApiModelProperty(value = "组织编码")
    @TableField("ORG_UNIT_CODE")
    private String orgUnitCode;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    public static final String ID = "ID";

    public static final String CONTENT_COMMITMENT_ID = "CONTENT_COMMITMENT_ID";

    public static final String ORG_UNIT_NAME = "ORG_UNIT_NAME";

    public static final String ORG_UNIT_ID = "ORG_UNIT_ID";

    public static final String VALID_FLAG = "VALID_FLAG";

    public static final String ORG_UNIT_CODE = "ORG_UNIT_CODE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
