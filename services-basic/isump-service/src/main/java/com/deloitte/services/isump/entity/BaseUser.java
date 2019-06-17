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
 * 用户基础信息表
 * </p>
 *
 * @author jianglong
 * @since 2019-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_BASE_USER")
@ApiModel(value="BaseUser对象", description="用户基础信息表")
public class BaseUser extends Model<BaseUser> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    @TableField("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "身份证")
    @TableField("IDENTITY_CARD")
    private String identityCard;

    @ApiModelProperty(value = "地址")
    @TableField("ADDRESS")
    private String address;

    @ApiModelProperty(value = "生日")
    @TableField("BIRTHDAY")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "护照号")
    @TableField("PASSPORT_NO")
    private String passportNo;

    @ApiModelProperty(value = "工作单位")
    @TableField("COMPANY")
    private String company;

    @ApiModelProperty(value = "岗位职务")
    @TableField("POSITION")
    private String position;

    @ApiModelProperty(value = "学历")
    @TableField("EDUCATION")
    private String education;

    @ApiModelProperty(value = "性别")
    @TableField("GENDER")
    private String gender;

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


    public static final String ID = "ID";

    public static final String USER_ID = "USER_ID";

    public static final String IDENTITY_CARD = "IDENTITY_CARD";

    public static final String ADDRESS = "ADDRESS";

    public static final String BIRTHDAY = "BIRTHDAY";

    public static final String PASSPORT_NO = "PASSPORT_NO";

    public static final String COMPANY = "COMPANY";

    public static final String POSITION = "POSITION";

    public static final String EDUCATION = "EDUCATION";

    public static final String GENDER = "GENDER";

    public static final String REMARK = "REMARK";

    public static final String RESERVE = "RESERVE";

    public static final String VERSION = "VERSION";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
