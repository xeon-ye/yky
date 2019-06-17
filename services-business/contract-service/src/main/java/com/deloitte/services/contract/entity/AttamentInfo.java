package com.deloitte.services.contract.entity;

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
 * 合同管理系统文件信息表
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("CONTRACT_ATTAMENT_INFO")
@ApiModel(value="AttamentInfo对象", description="合同管理系统文件信息表")
public class AttamentInfo extends Model<AttamentInfo> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "附件类型编号")
    @TableField("ATTAMENT_TYPE_CODE")
    private String attamentTypeCode;

    @ApiModelProperty(value = "附件类型")
    @TableField("ATTAMENT_TYPE")
    private String attamentType;

    @ApiModelProperty(value = "文件名称（重命名）")
    @TableField("ATTAMENT_RENANEM")
    private String attamentRenanem;

    @ApiModelProperty(value = "文件名称（原名称）")
    @TableField("ATTAMENT_FULL_NAME")
    private String attamentFullName;

    @ApiModelProperty(value = "路径")
    @TableField("PATH")
    private String path;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "是否在用，0弃用 1在用")
    @TableField("IS_USED")
    private String isUsed;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_1")
    private String spareField1;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_2")
    private String spareField2;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_3")
    private String spareField3;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_4")
    private LocalDateTime spareField4;

    @ApiModelProperty(value = "备用字段")
    @TableField("SPARE_FIELD_5")
    private Long spareField5;


    public static final String ID = "ID";

    public static final String ATTAMENT_TYPE_CODE = "ATTAMENT_TYPE_CODE";

    public static final String ATTAMENT_TYPE = "ATTAMENT_TYPE";

    public static final String ATTAMENT_RENANEM = "ATTAMENT_RENANEM";

    public static final String ATTAMENT_FULL_NAME = "ATTAMENT_FULL_NAME";

    public static final String PATH = "PATH";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String IS_USED = "IS_USED";

    public static final String SPARE_FIELD_1 = "SPARE_FIELD_1";

    public static final String SPARE_FIELD_2 = "SPARE_FIELD_2";

    public static final String SPARE_FIELD_3 = "SPARE_FIELD_3";

    public static final String SPARE_FIELD_4 = "SPARE_FIELD_4";

    public static final String SPARE_FIELD_5 = "SPARE_FIELD_5";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
