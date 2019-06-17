package com.deloitte.services.meeting.entity;

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
 * 
 * </p>
 *
 * @author fuqiao
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_MEETING_ADDRESS")
@ApiModel(value="OaMeetingAddress对象", description="")
public class OaMeetingAddress extends Model<OaMeetingAddress> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "会议主键")
    @TableField("MEETING_ID")
    private Long meetingId;

    @ApiModelProperty(value = "会议室主键")
    @TableField("ROOM_ID")
    private String roomId;

    @ApiModelProperty(value = "会议室地址")
    @TableField("ADDRESS")
    private String address;

    @ApiModelProperty(value = "会议室名称")
    @TableField("ROOM_NAME")
    private String roomName;

    @ApiModelProperty(value = "会议室编号")
    @TableField("ROOM_NUM")
    private String roomNum;

    @ApiModelProperty(value = "是否强制备案")
    @TableField("FORCE_BACK")
    private String forceBack;

    @ApiModelProperty(value = "是否后勤统管")
    @TableField("LOGISTICS_MANAGER")
    private String logisticsManager;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后更新人")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "备用字段")
    @TableField("EXT5")
    private String ext5;


    public static final String ID = "ID";

    public static final String MEETING_ID = "MEETING_ID";

    public static final String ROOM_ID = "ROOM_ID";

    public static final String ROOM_NUM = "ROOM_NUM";

    public static final String FORCE_BACK = "FORCE_BACK";

    public static final String LOGISTICS_MANAGER = "LOGISTICS_MANAGER";

    public static final String ADDRESS = "ADDRESS";

    public static final String ROOM_NAME = "ROOM_NAME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

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
