package com.deloitte.services.srpmp.outline.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 题录 07国合项目
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_OUTLINE_COOPERATION")
@ApiModel(value="SrpmsOutlineCooperation对象", description="题录 07国合项目")
public class SrpmsOutlineCooperation extends Model<SrpmsOutlineCooperation> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    @TableField("BASE_ID")
    private Long baseId;

    @ApiModelProperty(value = "项目编码")
    @TableField("PRO_CODE")
    private String proCode;

    @ApiModelProperty(value = "项目名称")
    @TableField("PRO_NAME")
    private String proName;

    @ApiModelProperty(value = "合作类别")
    @TableField("COOPERATION_CAT")
    private String cooperationCat;

    @ApiModelProperty(value = "合作单位")
    @TableField("COOPERATION_ORG")
    private String cooperationOrg;

    @ApiModelProperty(value = "项目负责人")
    @TableField("PRO_IN_CHARGE")
    private String proInCharge;

    @ApiModelProperty(value = "项目经费(万元)")
    @TableField("PRO_OUTLAY")
    private Double proOutlay;

    @ApiModelProperty(value = "项目开始时间")
    @TableField("PRO_START_DATE")
    private LocalDateTime proStartDate;

    @ApiModelProperty(value = "项目结束时间")
    @TableField("PRO_END_DATE")
    private LocalDateTime proEndDate;

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

    public static final String BASE_ID = "BASE_ID";

    public static final String PRO_CODE = "PRO_CODE";

    public static final String PRO_NAME = "PRO_NAME";

    public static final String COOPERATION_CAT = "COOPERATION_CAT";

    public static final String COOPERATION_ORG = "COOPERATION_ORG";

    public static final String PRO_IN_CHARGE = "PRO_IN_CHARGE";

    public static final String PRO_OUTLAY = "PRO_OUTLAY";

    public static final String PRO_START_DATE = "PRO_START_DATE";

    public static final String PRO_END_DATE = "PRO_END_DATE";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
