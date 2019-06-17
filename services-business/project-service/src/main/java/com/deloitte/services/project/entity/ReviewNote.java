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
 * 评审记录表
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_REVIEW_NOTE")
@ApiModel(value="ReviewNote对象", description="评审记录表")
public class ReviewNote extends Model<ReviewNote> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "审批人")
    @TableField("REVIEW_MAN")
    private String reviewMan;

    @ApiModelProperty(value = "审批时间")
    @TableField("REVIEW_DATE")
    private LocalDateTime reviewDate;

    @ApiModelProperty(value = "审批意见")
    @TableField("REVIEW_OPI")
    private String reviewOpi;

    @ApiModelProperty(value = "创建")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "拓展1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展3")
    @TableField("EXT3")
    private String ext3;


    public static final String ID = "ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String REVIEW_MAN = "REVIEW_MAN";

    public static final String REVIEW_DATE = "REVIEW_DATE";

    public static final String REVIEW_OPI = "REVIEW_OPI";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
