package com.deloitte.services.fssc.budget.mq.entity;

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

/**
 * <p>
 * MQ消息同步日志表
 * </p>
 *
 * @author jaws
 * @since 2019-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("FSSC_SYNC_MSG")
@ApiModel(value="SyncMsg对象", description="MQ消息同步日志表")
public class SyncMsg extends Model<SyncMsg> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "关联ID")
    @TableField("RELATION_ID")
    private Long relationId;

    @ApiModelProperty(value = "消息类型")
    @TableField("MSG_TYPE")
    private String msgType;

    @ApiModelProperty(value = "消息主体")
    @TableField("MSG_BODY")
    private String msgBody;

    @ApiModelProperty(value = "消息状态")
    @TableField("MSG_STATUS")
    private String msgStatus;

    @ApiModelProperty(value = "失败错误描述")
    @TableField("MSG_ERROR_INFO")
    private String msgErrorInfo;

    @ApiModelProperty(value = "创建日期")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新日期")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


    public static final String ID = "ID";

    public static final String RELATION_ID = "RELATION_ID";

    public static final String MSG_TYPE = "MSG_TYPE";

    public static final String MSG_BODY = "MSG_BODY";

    public static final String MSG_STATUS = "MSG_STATUS";

    public static final String MSG_ERROR_INFO = "MSG_ERROR_INFO";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
