package com.deloitte.services.project.entity;

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
 * 项目负责人
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_PRO_USER")
@ApiModel(value="ProUser对象", description="项目负责人")
public class ProUser extends Model<ProUser> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "人员id")
    @TableField("USER_ID")
    private String userId;

    @ApiModelProperty(value = "名称")
    @TableField("USER_NAME")
    private String userName;

    @ApiModelProperty(value = "项目编码")
    @TableField("PROJECT_CODE")
    private String projectCode;

    @ApiModelProperty(value = "员工工号")
    @TableField("USER_NO")
    private String userNo;

    @ApiModelProperty(value = "所在部门")
    @TableField("USER_DEP")
    private String userDep;

    @ApiModelProperty(value = "所处科室")
    @TableField("USER_KS")
    private String userKs;

    @ApiModelProperty(value = "职位code")
    @TableField("USER_POST_CODE")
    private String userPostCode;

    @ApiModelProperty(value = "职位名称")
    @TableField("USER_POST_NAME")
    private String userPostName;

    @ApiModelProperty(value = "人员标志code")
    @TableField("USER_MARK_CODE")
    private String userMarkCode;

    @ApiModelProperty(value = "人员标志name")
    @TableField("USER_MARK_NAME")
    private String userMarkName;

    @ApiModelProperty(value = "手机")
    @TableField("PHONE")
    private String phone;

    @ApiModelProperty(value = "邮件")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty(value = "固定电话")
    @TableField("TEL")
    private String tel;

    @ApiModelProperty(value = "专业")
    @TableField("MAJOR")
    private String major;

    @ApiModelProperty(value = "专业code")
    @TableField("MAJOR_CODE")
    private String majorCode;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展5")
    @TableField("EXT5")
    private String ext5;


    public static final String ID = "ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String USER_ID = "USER_ID";

    public static final String USER_NAME = "USER_NAME";

    public static final String PROJECT_CODE = "PROJECT_CODE";

    public static final String USER_NO = "USER_NO";

    public static final String USER_DEP = "USER_DEP";

    public static final String USER_KS = "USER_KS";

    public static final String USER_POST_CODE = "USER_POST_CODE";

    public static final String USER_POST_NAME = "USER_POST_NAME";

    public static final String USER_MARK_CODE = "USER_MARK_CODE";

    public static final String USER_MARK_NAME = "USER_MARK_NAME";

    public static final String PHONE = "PHONE";

    public static final String EMAIL = "EMAIL";

    public static final String TEL = "TEL";

    public static final String MAJOR = "MAJOR";

    public static final String MAJOR_CODE = "MAJOR_CODE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
