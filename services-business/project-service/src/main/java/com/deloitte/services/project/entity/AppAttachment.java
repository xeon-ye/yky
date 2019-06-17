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
 * 房屋修缮附件
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_APP_ATTACHMENT")
@ApiModel(value="AppAttachment对象", description="房屋修缮附件")
public class AppAttachment extends Model<AppAttachment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "申报书ID")
    @TableField("APPLICATION_ID")
    private String applicationId;

    @ApiModelProperty(value = "房屋修缮附件ID")
    @TableField("ATTACHMENT_ID")
    private String attachmentId;

    @ApiModelProperty(value = "建成时间")
    @TableField("START_DATE")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "建筑面积")
    @TableField("BUILT_UP_AREA")
    private String builtUpArea;

    @ApiModelProperty(value = "修缮面积")
    @TableField("REPAIR_AREA")
    private String repairArea;

    @ApiModelProperty(value = "实施周期")
    @TableField("OPERATE_CYCLE")
    private String operateCycle;

    @ApiModelProperty(value = "内容摘要")
    @TableField("CONTENTS")
    private String contents;

    @ApiModelProperty(value = "原值")
    @TableField("ORIGINAL_VALUE")
    private String originalValue;

    @ApiModelProperty(value = "规格及型号")
    @TableField("SPECIFICATIONS_AND_MODELS")
    private String specificationsAndModels;

    @ApiModelProperty(value = "产地")
    @TableField("PLACE_OF_ORIGIN")
    private String placeOfOrigin;

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

    @ApiModelProperty(value = "明细名称")
    @TableField("ATTACHMENT_NAME")
    private String attachmentName;

    @ApiModelProperty(value = "中央财政-经费申请数(万元)")
    @TableField("CENTRAL_FIN")
    private String centralFin;

    @ApiModelProperty(value = "主管部门-经费申请数(万元)")
    @TableField("DEPARTMENT")
    private String department;

    @ApiModelProperty(value = "其它-经费申请数(万元)")
    @TableField("OTHER")
    private String other;


    public static final String ID = "ID";

    public static final String APPLICATION_ID = "APPLICATION_ID";

    public static final String ATTACHMENT_ID = "ATTACHMENT_ID";

    public static final String START_DATE = "START_DATE";

    public static final String BUILT_UP_AREA = "BUILT_UP_AREA";

    public static final String REPAIR_AREA = "REPAIR_AREA";

    public static final String OPERATE_CYCLE = "OPERATE_CYCLE";

    public static final String CONTENTS = "CONTENTS";

    public static final String ORIGINAL_VALUE = "ORIGINAL_VALUE";

    public static final String SPECIFICATIONS_AND_MODELS = "SPECIFICATIONS_AND_MODELS";

    public static final String PLACE_OF_ORIGIN = "PLACE_OF_ORIGIN";

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

    public static final String ATTACHMENT_NAME = "ATTACHMENT_NAME";

    public static final String CENTRAL_FIN = "CENTRAL_FIN";

    public static final String DEPARTMENT = "DEPARTMENT";

    public static final String OTHER = "OTHER";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
