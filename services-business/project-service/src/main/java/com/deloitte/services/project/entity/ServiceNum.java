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
 * 业务单号
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_SERVICE_NUM")
@ApiModel(value="ServiceNum对象", description="业务单号")
public class ServiceNum extends Model<ServiceNum> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "申报业务号")
    @TableField("APPLY_NUM")
    private String applyNum;

    @ApiModelProperty(value = "申报书取消业务号")
    @TableField("APPLY_CANCEL_NUM")
    private String applyCancelNum;

    @ApiModelProperty(value = "评审业务号")
    @TableField("REVIEW_NUM")
    private String reviewNum;

    @ApiModelProperty(value = "批复业务号")
    @TableField("REPLY_NUM")
    private String replyNum;

    @ApiModelProperty(value = "项目变更业务号")
    @TableField("CHANGE_NUM")
    private String changeNum;

    @ApiModelProperty(value = "业务项目号")
    @TableField("BUSS_NUM")
    private String bussNum;

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

    @ApiModelProperty(value = "业务重复号")
    @TableField("SERVICE_ONLY")
    private String serviceOnly;

    @ApiModelProperty(value = "当前年度")
    @TableField("CUR_YEAR")
    private Long curYear;

    @ApiModelProperty(value = "序列号")
    @TableField("NUM")
    private Integer num;


    public static final String ID = "ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String APPLY_NUM = "APPLY_NUM";

    public static final String APPLY_CANCEL_NUM = "APPLY_CANCEL_NUM";

    public static final String REVIEW_NUM = "REVIEW_NUM";

    public static final String REPLY_NUM = "REPLY_NUM";

    public static final String CHANGE_NUM = "CHANGE_NUM";

    public static final String BUSS_NUM = "BUSS_NUM";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String SERVICE_ONLY = "SERVICE_ONLY";

    public static final String CUR_YEAR = "CUR_YEAR";

    public static final String NUM = "NUM";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
