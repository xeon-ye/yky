package com.deloitte.services.srpmp.common.entity;

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
 * 序列号中心
 * </p>
 *
 * @author lixin
 * @since 2019-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SERIAL_NO_CENTER")
@ApiModel(value="SerialNoCenter对象", description="序列号中心")
public class SerialNoCenter extends Model<SerialNoCenter> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "序列号类型")
    @TableField("SERIAL_TYPE")
    private String serialType;

    @ApiModelProperty(value = "序列号头")
    @TableField("SERIAL_HEADER")
    private String serialHeader;

    @ApiModelProperty(value = "流水号")
    @TableField("SERIAL_NO")
    private Long serialNo;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


    public static final String ID = "ID";

    public static final String SERIAL_TYPE = "SERIAL_TYPE";

    public static final String SERIAL_HEADER = "SERIAL_HEADER";

    public static final String SERIAL_NO = "SERIAL_NO";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
