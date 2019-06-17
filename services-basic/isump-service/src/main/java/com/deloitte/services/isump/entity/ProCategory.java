package com.deloitte.services.isump.entity;

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
 * @author jianglong
 * @since 2019-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ISUMP_PRO_CATEGORY")
@ApiModel(value="ProCategory对象", description="")
public class ProCategory extends Model<ProCategory> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "名称")
    @TableField("NAME")
    private String name;

    @ApiModelProperty(value = "上级code")
    @TableField("PARENT_CODE")
    private String parentCode;

    @TableField("REMARK")
    private String remark;

    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "CREATE_BY", fill = FieldFill.INSERT)
    private Long createBy;

    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "UPDATE_BY", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @ApiModelProperty(value = "所属系统类别")
    @TableField("SYS_TYPE")
    private String sysType;


    public static final String ID = "ID";

    public static final String CODE = "CODE";

    public static final String NAME = "NAME";

    public static final String PARENT_CODE = "PARENT_CODE";

    public static final String REMARK = "REMARK";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    public static final String SYS_TYPE = "SYS_TYPE";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
