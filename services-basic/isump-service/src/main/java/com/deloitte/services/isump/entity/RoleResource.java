package com.deloitte.services.isump.entity;

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
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色资源表
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_ROLE_RESOURCE")
@ApiModel(value="RoleResource对象", description="角色资源表")
public class RoleResource extends Model<RoleResource> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "角色ID")
    @TableField("ROLE_ID")
    private Long roleId;

    @ApiModelProperty(value = "资源ID")
    @TableField("RESOURCE_ID")
    private Long resourceId;


    public static final String ID = "ID";

    public static final String ROLE_ID = "ROLE_ID";

    public static final String RESOURCE_ID = "RESOURCE_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
