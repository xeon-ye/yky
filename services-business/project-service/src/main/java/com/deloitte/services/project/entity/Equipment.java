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
 * 设备购置
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_EQUIPMENT")
@ApiModel(value="Equipment对象", description="设备购置")
public class Equipment extends Model<Equipment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "设备购置ID")
    @TableField("EQU_ID")
    private String equId;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "明细名称")
    @TableField("PROJECT_ABS")
    private String projectAbs;

    @ApiModelProperty(value = "规格型号")
    @TableField("MODEL")
    private String model;

    @ApiModelProperty(value = "产地")
    @TableField("EQU_ADDRESS")
    private String equAddress;

    @ApiModelProperty(value = "用途")
    @TableField("EQU_USE")
    private String equUse;

    @ApiModelProperty(value = "数量")
    @TableField("EQU_NUM")
    private String equNum;

    @ApiModelProperty(value = "实施周期")
    @TableField("EQU_CYCLE")
    private String equCycle;

    @ApiModelProperty(value = "中央财政经费")
    @TableField("FUNDING_CENTER")
    private String fundingCenter;

    @ApiModelProperty(value = "主管部门经费")
    @TableField("FUNDING_DIRECTOR")
    private String fundingDirector;

    @ApiModelProperty(value = "其他经费")
    @TableField("FUNDING_OTHER")
    private String fundingOther;

    @ApiModelProperty(value = "经费合计")
    @TableField("FUNDING_TOTAL")
    private String fundingTotal;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "更新")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "拓展字段")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_PATH")
    private String orgPath;


    public static final String ID = "ID";

    public static final String EQU_ID = "EQU_ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String PROJECT_ABS = "PROJECT_ABS";

    public static final String MODEL = "MODEL";

    public static final String EQU_ADDRESS = "EQU_ADDRESS";

    public static final String EQU_USE = "EQU_USE";

    public static final String EQU_NUM = "EQU_NUM";

    public static final String EQU_CYCLE = "EQU_CYCLE";

    public static final String FUNDING_CENTER = "FUNDING_CENTER";

    public static final String FUNDING_DIRECTOR = "FUNDING_DIRECTOR";

    public static final String FUNDING_OTHER = "FUNDING_OTHER";

    public static final String FUNDING_TOTAL = "FUNDING_TOTAL";

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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
