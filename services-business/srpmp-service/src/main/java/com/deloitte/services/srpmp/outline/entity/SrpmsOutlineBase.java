package com.deloitte.services.srpmp.outline.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 题录 基础信息表
 * </p>
 *
 * @author pengchao
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_OUTLINE_BASE")
@ApiModel(value="SrpmsOutlineBase对象", description="题录 基础信息表")
public class SrpmsOutlineBase extends Model<SrpmsOutlineBase> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableField("ID")
    private Long id;

    @ApiModelProperty(value = "单位ID")
    @TableField("ORG_ID")
    private Long orgId;

    @ApiModelProperty(value = "年份")
    @TableField("YEAR")
    private String year;

    @ApiModelProperty(value = "月份")
    @TableField("MONTH")
    private String month;

    @ApiModelProperty(value = "题录类型")
    @TableField("TYPE")
    private String type;

    @ApiModelProperty(value = "记录总条数")
    @TableField("TOTAL")
    private Long total;

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

    public static final String ORG_ID = "ORG_ID";

    public static final String YEAR = "YEAR";

    public static final String MONTH = "MONTH";

    public static final String TYPE = "TYPE";

    public static final String TOTAL = "TOTAL";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
