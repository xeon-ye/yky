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
 * 题录 01新工程信息表
 * </p>
 *
 * @author Apeng
 * @since 2019-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_OUTLINE_NEW_TITLE")
@ApiModel(value="SrpmsOutlineNewTitle对象", description="题录 01新工程信息表")
public class SrpmsOutlineNewTitle extends Model<SrpmsOutlineNewTitle> {

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

    @ApiModelProperty(value = "项目类别")
    @TableField("PRO_CATEGORY")
    private String proCategory;

    @ApiModelProperty(value = "来源机构")
    @TableField("PRO_SOURCE_ORG")
    private String proSourceOrg;

    @ApiModelProperty(value = "参与程度")
    @TableField("PRO_ENTER_TYPE")
    private String proEnterType;

    @ApiModelProperty(value = "项目负责人")
    @TableField("PRO_IN_CHARGE")
    private String proInCharge;

    @ApiModelProperty(value = "项目开始时间")
    @TableField("PRO_START_DATE")
    private LocalDateTime proStartDate;

    @ApiModelProperty(value = "项目结束时间")
    @TableField("PRO_END_DATE")
    private LocalDateTime proEndDate;

    @ApiModelProperty(value = "项目状态")
    @TableField("PRO_STATE")
    private String proState;

    @ApiModelProperty(value = "项目总经费")
    @TableField("PRO_TOTAL_OUTLAY")
    private Double proTotalOutlay;

    @ApiModelProperty(value = "项目到位经费")
    @TableField("PRO_RECEIVE_OUTLAY")
    private Double proReceiveOutlay;

    @ApiModelProperty(value = "备注")
    @TableField("REMARKS")
    private String remarks;

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

    public static final String PRO_CATEGORY = "PRO_CATEGORY";

    public static final String PRO_SOURCE_ORG = "PRO_SOURCE_ORG";

    public static final String PRO_ENTER_TYPE = "PRO_ENTER_TYPE";

    public static final String PRO_IN_CHARGE = "PRO_IN_CHARGE";

    public static final String PRO_HEAD_NUMBER = "PRO_HEAD_NUMBER";

    public static final String PRO_START_DATE = "PRO_START_DATE";

    public static final String PRO_END_DATE = "PRO_END_DATE";

    public static final String PRO_STATE = "PRO_STATE";

    public static final String PRO_TOTAL_OUTLAY = "PRO_TOTAL_OUTLAY";

    public static final String PRO_RECEIVE_OUTLAY = "PRO_RECEIVE_OUTLAY";

    public static final String REMARKS = "REMARKS";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
