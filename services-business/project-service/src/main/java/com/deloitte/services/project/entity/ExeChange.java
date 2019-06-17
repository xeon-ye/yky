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
 * 项目执行变更记录
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_EXE_CHANGE")
@ApiModel(value="ExeChange对象", description="项目执行变更记录")
public class ExeChange extends Model<ExeChange> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "变更id")
    @TableField("CHANGE_ID")
    private Long changeId;

    @ApiModelProperty(value = "项目执行id")
    @TableField("EXECUTION_ID")
    private Long executionId;

    @ApiModelProperty(value = "变更原因")
    @TableField("CHANGE_ADV")
    private String changeAdv;

    @ApiModelProperty(value = "变更状态code")
    @TableField("CHANGE_STATUS_CODE")
    private Long changeStatusCode;

    @ApiModelProperty(value = "变更状态name")
    @TableField("CHANGE_STATUS_NAME")
    private String changeStatusName;

    @ApiModelProperty(value = "历史记录时间")
    @TableField("HIS_TIME")
    private LocalDateTime hisTime;

    @ApiModelProperty(value = "历史记录变更人")
    @TableField("HIS_BY")
    private String hisBy;

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

    @ApiModelProperty(value = "维度id")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "path")
    @TableField("ORG_PATH")
    private String orgPath;


    public static final String ID = "ID";

    public static final String CHANGE_ID = "CHANGE_ID";

    public static final String EXECUTION_ID = "EXECUTION_ID";

    public static final String CHANGE_ADV = "CHANGE_ADV";

    public static final String CHANGE_STATUS_CODE = "CHANGE_STATUS_CODE";

    public static final String CHANGE_STATUS_NAME = "CHANGE_STATUS_NAME";

    public static final String HIS_TIME = "HIS_TIME";

    public static final String HIS_BY = "HIS_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
