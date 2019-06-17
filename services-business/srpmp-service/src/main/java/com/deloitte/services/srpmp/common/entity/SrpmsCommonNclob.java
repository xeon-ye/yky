package com.deloitte.services.srpmp.common.entity;

import java.io.Serializable;
import java.sql.Clob;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 科研CLOB通用表
 * </p>
 *
 * @author pengchao
 * @since 2019-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_COMMON_NCLOB")
@ApiModel(value="SrpmsCommonNclob对象", description="科研CLOB通用表")
public class SrpmsCommonNclob extends Model<SrpmsCommonNclob> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "项目id")
    @TableField("PROJECT_ID")
    private Long projectId;

    @ApiModelProperty(value = "变更id")
    @TableField("UPDATE_ID")
    private Long updateId;

    @ApiModelProperty(value = "类型")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "内容")
    @TableField("CONTENT")
    private String content;

    @ApiModelProperty(value = "创建日期")
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


    public static final String ID = "ID";

    public static final String PROJECT_ID = "PROJECT_ID";

    public static final String UPDATE_ID = "UPDATE_ID";

    public static final String TYPE = "TYPE";

    public static final String CONTENT = "CONTENT";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
