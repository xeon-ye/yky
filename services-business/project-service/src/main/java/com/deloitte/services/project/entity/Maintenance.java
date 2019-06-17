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
 * 维护项目
 * </p>
 *
 * @author zhengchun
 * @since 2019-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_MAINTENANCE")
@ApiModel(value="Maintenance对象", description="维护项目")
public class Maintenance extends Model<Maintenance> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "维护项目id")
    @TableField("MAINTENANCE_ID")
    private String maintenanceId;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private String projectId;

    @ApiModelProperty(value = "申报书id")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "项目主要内容")
    @TableField("MAIN_NOTE")
    private String mainNote;

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

    @ApiModelProperty(value = "维护状态code")
    @TableField("MAIN_STATUS_CODE")
    private String mainStatusCode;

    @ApiModelProperty(value = "维护状态name")
    @TableField("MAIN_STATUS_NAME")
    private String mainStatusName;

    @ApiModelProperty(value = "关联父级项目id projectId")
    @TableField("MAIN_PARENT_ID")
    private String mainParentId;


    public static final String ID = "ID";

    public static final String MAINTENANCE_ID = "MAINTENANCE_ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String MAIN_NOTE = "MAIN_NOTE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String MAIN_STATUS_CODE = "MAIN_STATUS_CODE";

    public static final String MAIN_STATUS_NAME = "MAIN_STATUS_NAME";

    public static final String MAIN_PARENT_ID = "MAIN_PARENT_ID";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}