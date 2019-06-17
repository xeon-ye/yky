package com.deloitte.services.demomybatiesauto.entity;

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
 * 
 * </p>
 *
 * @author jack
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserDemo对象", description="")
public class UserDemo extends Model<UserDemo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "扩展字段1")
    private String ext1;

    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String AGE = "age";

    public static final String EMAIL = "email";

    public static final String CREATE_TIME = "create_time";

    public static final String CREATE_BY = "create_by";

    public static final String UPDATE_TIME = "update_time";

    public static final String UPDATE_BY = "update_by";

    public static final String EXT1 = "ext1";

    public static final String BIRTHDAY = "birthday";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
