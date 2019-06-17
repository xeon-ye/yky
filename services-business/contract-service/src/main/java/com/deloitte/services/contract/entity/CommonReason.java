package com.deloitte.services.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 常见原因表
 * </p>
 *
 * @author zhengchun
 * @since 2019-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_COMMON_REASON")
@ApiModel(value="CommonReason对象", description="常见原因表")
public class CommonReason extends Model<CommonReason> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "原因")
    @TableField("REASON")
    private String reason;

    @ApiModelProperty(value = "原因类型")
    @TableField("REASON_TYPE")
    private String reasonType;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


    public static final String ID = "ID";

    public static final String REASON = "REASON";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String REASON_TYPE = "REASON_TYPE";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
