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
 * 成员管理
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_PERSON")
@ApiModel(value="Person对象", description="成员管理")
public class Person extends Model<Person> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "成员管理id")
    @TableField("MAN_ID")
    private String manId;

    @ApiModelProperty(value = "评审id")
    @TableField("REVIEW_ID")
    private String reviewId;

    @ApiModelProperty(value = "批复id")
    @TableField("REPLY_ID")
    private String replyId;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "人员id")
    @TableField("PER_ID")
    private String perId;

    @ApiModelProperty(value = "人员名称")
    @TableField("PER_NAME")
    private String perName;

    @ApiModelProperty(value = "职责code")
    @TableField("PER_POSITION_CODE")
    private String perPositionCode;

    @ApiModelProperty(value = "职责")
    @TableField("PER_POSITION_NAME")
    private String perPositionName;

    @ApiModelProperty(value = "电话")
    @TableField("PHONE")
    private String phone;

    @ApiModelProperty(value = "邮件")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty(value = "开始时间")
    @TableField("BEGIN_TIME")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "终止时间")
    @TableField("END_TIME")
    private LocalDateTime endTime;

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

    @ApiModelProperty(value = "拓展字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "维度id")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "维度")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "联系地址")
    @TableField("ADRESS")
    private String adress;

    @ApiModelProperty(value = "人员标识CODE,如法人代表，联系人")
    @TableField("PERSON_CODE")
    private String personCode;

    @ApiModelProperty(value = "人员标识名称")
    @TableField("PERSON_NAME")
    private String personName;


    public static final String ID = "ID";

    public static final String MAN_ID = "MAN_ID";

    public static final String REVIEW_ID = "REVIEW_ID";

    public static final String REPLY_ID = "REPLY_ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String PER_ID = "PER_ID";

    public static final String PER_NAME = "PER_NAME";

    public static final String PER_POSITION_CODE = "PER_POSITION_CODE";

    public static final String PER_POSITION_NAME = "PER_POSITION_NAME";

    public static final String PHONE = "PHONE";

    public static final String EMAIL = "EMAIL";

    public static final String BEGIN_TIME = "BEGIN_TIME";

    public static final String END_TIME = "END_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String ADRESS = "ADRESS";

    public static final String PERSON_CODE = "PERSON_CODE";

    public static final String PERSON_NAME = "PERSON_NAME";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
