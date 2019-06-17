package com.deloitte.services.oaservice.entity;

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
 * 
 * </p>
 *
 * @author fuqiao
 * @since 2019-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_ASSISTANT_MAPPING")
@ApiModel(value="OaAssistantMapping对象", description="")
public class OaAssistantMapping extends Model<OaAssistantMapping> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "秘书ID")
    @TableField("USER_ID")
    private String userId;

    @ApiModelProperty(value = "秘书名称")
    @TableField("USER_NAME")
    private String userName;

    @ApiModelProperty(value = "领导ID")
    @TableField("LEADER_ID")
    private String leaderId;

    @ApiModelProperty(value = "领导名称")
    @TableField("LEADER_NAME")
    private String leaderName;

    @ApiModelProperty(value = "领导所属部门ID")
    @TableField("LEADER_DEPT_ID")
    private String leaderDeptId;

    @ApiModelProperty(value = "领导所属部门名称")
    @TableField("LEADER_DEPT_NAME")
    private String leaderDeptName;

    @ApiModelProperty(value = "是否有效")
    @TableField("ENABLE_FLAG")
    private String enableFlag;

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

    public static final String USER_ID = "USER_ID";

    public static final String USER_NAME = "USER_NAME";

    public static final String LEADER_ID = "LEADER_ID";

    public static final String LEADER_NAME = "LEADER_NAME";

    public static final String LEADER_DEPT_ID = "LEADER_DEPT_ID";

    public static final String LEADER_DEPT_NAME = "LEADER_DEPT_NAME";

    public static final String ENABLE_FLAG = "ENABLE_FLAG";

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
