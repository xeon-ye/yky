package com.deloitte.services.isump.entity;

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
 * @author zhangdi
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_LOGIN_LOG")
@ApiModel(value="LoginLog对象", description="")
public class LoginLog extends Model<LoginLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "登陆账号")
    @TableField("LOGNAME")
    private String logname;

    @ApiModelProperty(value = "登陆IP地址")
    @TableField("IP_ADDRESS")
    private String ipAddress;

    @ApiModelProperty(value = "登陆时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    public static final String ID = "ID";

    public static final String LOGNAME = "LOGNAME";

    public static final String IP_ADDRESS = "IP_ADDRESS";

    public static final String CREATE_TIME = "CREATE_TIME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
