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
 * 集值表 集值表
 * </p>
 *
 * @author zhengchun
 * @since 2019-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("PROJECT_VALUE")
@ApiModel(value="Value对象", description="集值表 集值表")
public class Value extends Model<Value> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "集值ID")
    @TableField("VALUE_ID")
    private Long valueId;

    @ApiModelProperty(value = "集值编码")
    @TableField("VALUE_CODE")
    private String valueCode;

    @ApiModelProperty(value = "集值名称")
    @TableField("VALUE_NAME")
    private String valueName;

    @ApiModelProperty(value = "集值状态,0生效,1失效")
    @TableField("VALUE_STATUS")
    private Long valueStatus;

    @ApiModelProperty(value = "生效时间")
    @TableField("SUCCESS_TIME")
    private LocalDateTime successTime;

    @ApiModelProperty(value = "父ID")
    @TableField("PAR_ID")
    private Long parId;

    @ApiModelProperty(value = "父名称")
    @TableField("PAR_NAME")
    private String parName;

    @ApiModelProperty(value = "失效时间")
    @TableField("FAILURE_TIME")
    private LocalDateTime failureTime;

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
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "拓展字段")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "拓展字段")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "拓展字段")
    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_ID")
    private String orgId;

    @ApiModelProperty(value = "拓展字段")
    @TableField("ORG_PATH")
    private String orgPath;

    @ApiModelProperty(value = "项目分类id")
    @TableField("PRO_ID")
    private Long proId;

    @ApiModelProperty(value = "值集描述")
    @TableField("VALUE_DES")
    private String valueDes;


    public static final String ID = "ID";

    public static final String VALUE_ID = "VALUE_ID";

    public static final String VALUE_CODE = "VALUE_CODE";

    public static final String VALUE_NAME = "VALUE_NAME";

    public static final String VALUE_STATUS = "VALUE_STATUS";

    public static final String SUCCESS_TIME = "SUCCESS_TIME";

    public static final String PAR_ID = "PAR_ID";

    public static final String PAR_NAME = "PAR_NAME";

    public static final String FAILURE_TIME = "FAILURE_TIME";

    public static final String EXT1 = "EXT1";

    public static final String EXT2 = "EXT2";

    public static final String EXT3 = "EXT3";

    public static final String EXT4 = "EXT4";

    public static final String EXT5 = "EXT5";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String ORG_ID = "ORG_ID";

    public static final String ORG_PATH = "ORG_PATH";

    public static final String PRO_ID = "PRO_ID";

    public static final String VALUE_DES = "VALUE_DES";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
