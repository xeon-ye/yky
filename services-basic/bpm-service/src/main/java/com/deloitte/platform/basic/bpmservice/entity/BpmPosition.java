package com.deloitte.platform.basic.bpmservice.entity;

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
 * @author jackliu
 * @since 2019-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("BPM_POSITION")
@ApiModel(value="BpmPosition对象", description="")
public class BpmPosition extends Model<BpmPosition> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "对应的流程定义ID")
    @TableField("PROCESS_DEFINE_KEY")
    private String processDefineKey;

    @ApiModelProperty(value = "对应的流程名称")
    @TableField("PROCESS_NAME")
    private String processName;

    @ApiModelProperty(value = "审批流程中的节点ID")
    @TableField("NODE_ID")
    private String nodeId;

    @ApiModelProperty(value = "审批流程中的节点名称")
    @TableField("NODE_NAME")
    private String nodeName;

    @ApiModelProperty(value = "审批流程中的节点序号")
    @TableField("NODE_SEQUENCE")
    private Integer nodeSequence;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("EXT1")
    private String ext1;

    @TableField("EXT2")
    private String ext2;

    @TableField("EXT3")
    private String ext3;

    @TableField("EXT4")
    private String ext4;

    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "虚拟组织名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "虚拟组织状态")
    @TableField("STATE")
    private String state;


    public static final String ID = "ID";

    public static final String PROCESS_DEFINE_KEY = "PROCESS_DEFINE_KEY";

    public static final String PROCESS_NAME = "PROCESS_NAME";

    public static final String NODE_ID = "NODE_ID";

    public static final String NODE_NAME = "NODE_NAME";

    public static final String NODE_SEQUENCE = "NODE_SEQUENCE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String NAME = "NAME";

    public static final String STATE = "STATE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
