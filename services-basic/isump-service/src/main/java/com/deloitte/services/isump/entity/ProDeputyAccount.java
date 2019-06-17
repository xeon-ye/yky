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
 * 
 * </p>
 *
 * @author jianglong
 * @since 2019-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_PRO_DEPUTY_ACCOUNT")
@ApiModel(value="ProDeputyAccount对象", description="")
public class ProDeputyAccount extends Model<ProDeputyAccount> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @TableField("DEPUTY_ACCOUNT_ID")
    private Long deputyAccountId;

    @TableField("PRO_CATEGORY_ID")
    private Long proCategoryId;


    public static final String ID = "ID";

    public static final String DEPUTY_ACCOUNT_ID = "DEPUTY_ACCOUNT_ID";

    public static final String PRO_CATEGORY_ID = "PRO_CATEGORY_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
