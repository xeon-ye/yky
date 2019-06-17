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
 * 副账号-角色 关联表
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_DEPUTY_ACCOUNT_ROLE")
@ApiModel(value="DeputyAccountRole对象", description="副账号-角色 关联表")
public class DeputyAccountRole extends Model<DeputyAccountRole> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "副账号ID")
    @TableField("DEPUTY_ACCOUNT_ID")
    private Long deputyAccountId;

    @ApiModelProperty(value = "角色ID")
    @TableField("ROLE_ID")
    private Long roleId;


    public static final String ID = "ID";

    public static final String DEPUTY_ACCOUNT_ID = "DEPUTY_ACCOUNT_ID";

    public static final String ROLE_ID = "ROLE_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
