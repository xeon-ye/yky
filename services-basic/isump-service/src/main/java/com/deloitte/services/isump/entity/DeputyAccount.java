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
 * 副账号
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_DEPUTY_ACCOUNT")
@ApiModel(value="DeputyAccount对象", description="副账号")
public class DeputyAccount extends Model<DeputyAccount> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "组织架构ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "个人账号排序")
    @TableField("SORT")
    private Long sort;

    @ApiModelProperty(value = "组织内排序")
    @TableField("ORG_SORT")
    private Long orgSort;

    @ApiModelProperty(value = "状态")
    @TableField("STATE")
    private String state;

    @ApiModelProperty(value = "启用时间")
    @TableField("OPEN_TIME")
    private LocalDateTime openTime;

    @ApiModelProperty(value = "停用时间")
    @TableField("CLOSE_TIME")
    private LocalDateTime closeTime;

    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty(value = "备用字段1")
    @TableField("RESERVE")
    private String reserve;

    @ApiModelProperty(value = "备用字段2")
    @TableField("VERSION")
    private String version;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "直接上级")
    @TableField("PARENT_EMP_NO")
    private String parentEmpNo;


    public static final String ID = "ID";

    public static final String USER_ID = "USER_ID";

    public static final String ORG_ID = "ORG_ID";

    public static final String NAME = "NAME";

    public static final String SORT = "SORT";

    public static final String ORG_SORT = "ORG_SORT";

    public static final String STATE = "STATE";

    public static final String OPEN_TIME = "OPEN_TIME";

    public static final String CLOSE_TIME = "CLOSE_TIME";

    public static final String REMARK = "REMARK";

    public static final String RESERVE = "RESERVE";

    public static final String VERSION = "VERSION";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String PARENT_EMP_NO = "PARENT_EMP_NO";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
