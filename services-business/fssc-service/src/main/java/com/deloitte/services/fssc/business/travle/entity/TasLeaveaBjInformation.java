package com.deloitte.services.fssc.business.travle.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
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
 * @author hjy
 * @since 2019-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("TAS_LEAVEA_BJ_INFORMATION")
@ApiModel(value="TasLeaveaBjInformation对象", description="")
public class TasLeaveaBjInformation extends Model<TasLeaveaBjInformation> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "创建人ID")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty(value = "创建人姓名")
    @TableField("CREATE_USER_NAME")
    private String createUserName;

    @ApiModelProperty(value = "更新人ID")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "预留字段1")
    @TableField("EXT1")
    private String ext1;

    @ApiModelProperty(value = "预留字段2")
    @TableField("EXT2")
    private String ext2;

    @ApiModelProperty(value = "预留字段3")
    @TableField("EXT3")
    private String ext3;

    @ApiModelProperty(value = "预留字段4")
    @TableField("EXT4")
    private String ext4;

    @ApiModelProperty(value = "预留字段5")
    @TableField("EXT5")
    private String ext5;

    @ApiModelProperty(value = "预留字段6")
    @TableField("EXT6")
    private String ext6;

    @ApiModelProperty(value = "预留字段7")
    @TableField("EXT7")
    private String ext7;

    @ApiModelProperty(value = "预留字段8")
    @TableField("EXT8")
    private String ext8;

    @ApiModelProperty(value = "预留字段9")
    @TableField("EXT9")
    private String ext9;

    @ApiModelProperty(value = "预留字段10")
    @TableField("EXT10")
    private String ext10;

    @ApiModelProperty(value = "预留字段11")
    @TableField("EXT11")
    private String ext11;

    @ApiModelProperty(value = "预留字段12")
    @TableField("EXT12")
    private String ext12;

    @ApiModelProperty(value = "预留字段13")
    @TableField("EXT13")
    private String ext13;

    @ApiModelProperty(value = "预留字段14")
    @TableField("EXT14")
    private String ext14;

    @ApiModelProperty(value = "预留字段15")
    @TableField("EXT15")
    private String ext15;

    @ApiModelProperty(value = "版本")
    @TableField("VERSION")
    @Version
    private Long version;

    @ApiModelProperty(value = "姓名")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "工号")
    @TableField("JOB_NUMBER")
    private String jobNumber;

    @ApiModelProperty(value = "级别")
    @TableField("LEVEL_NAME")
    private String levelName;

    @ApiModelProperty(value = "行号")
    @TableField("LINE_NUMBER")
    private Long lineNumber;

    @ApiModelProperty(value = "单价id")
    @TableField("DOCUMENT_ID")
    private Long documentId;

    @ApiModelProperty(value = "单价类型")
    @TableField("DOCUMENT_TYPE")
    private String documentType;

    @ApiModelProperty(value = "职务")
    @TableField("POST")
    private String post;

    @ApiModelProperty(value = "出差地点")
    @TableField("TRAVEL_ADDRESS")
    private String travelAddress;

    @ApiModelProperty(value = "离京时间")
    @TableField("LEAVEA_BJ_TIME")
    private LocalDateTime leaveaBjTime;

    @ApiModelProperty(value = "返京时间")
    @TableField("RETURN_BJ_TIME")
    private LocalDateTime returnBjTime;

    @ApiModelProperty(value = "本年度出差次数统计")
    @TableField("YEAR_TRAVEL_NUM")
    private Long yearTravelNum;

    @ApiModelProperty(value = "在京主持工作负责人")
    @TableField("HEAD_WORK_BJ")
    private String headWorkBj;

    @ApiModelProperty(value = "组织id")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "组织path")
    @TableField("ORG_PATH")
    private String orgPath;


    public static final String ID = "ID";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_USER_NAME = "CREATE_USER_NAME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String EXT6 = "EXT6";

    public static final String EXT7 = "EXT7";

    public static final String EXT8 = "EXT8";

    public static final String EXT9 = "EXT9";

    public static final String EXT10 = "EXT10";

    public static final String EXT11 = "EXT11";

    public static final String EXT12 = "EXT12";

    public static final String EXT13 = "EXT13";

    public static final String EXT14 = "EXT14";

    public static final String EXT15 = "EXT15";

    public static final String VERSION = "VERSION";

    public static final String NAME = "NAME";

    public static final String JOB_NUMBER = "JOB_NUMBER";

    public static final String LEVEL_NAME = "LEVEL_NAME";

    public static final String LINE_NUMBER = "LINE_NUMBER";

    public static final String DOCUMENT_ID = "DOCUMENT_ID";

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String POST = "POST";

    public static final String TRAVEL_ADDRESS = "TRAVEL_ADDRESS";

    public static final String LEAVEA_BJ_TIME = "LEAVEA_BJ_TIME";

    public static final String RETURN_BJ_TIME = "RETURN_BJ_TIME";

    public static final String YEAR_TRAVEL_NUM = "YEAR_TRAVEL_NUM";

    public static final String HEAD_WORK_BJ = "HEAD_WORK_BJ";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
