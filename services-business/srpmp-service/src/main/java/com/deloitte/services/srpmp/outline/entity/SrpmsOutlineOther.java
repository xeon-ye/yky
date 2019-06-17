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
 * 题录 11其他
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_OUTLINE_OTHER")
@ApiModel(value="SrpmsOutlineOther对象", description="题录 11其他")
public class SrpmsOutlineOther extends Model<SrpmsOutlineOther> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    @TableField("BASE_ID")
    private Long baseId;

    @ApiModelProperty(value = "科研基地类型")
    @TableField("SRPMS_BASE_CAT")
    private String srpmsBaseCat;

    @ApiModelProperty(value = "基地级别")
    @TableField("SRPMS_BASE_LEVEL")
    private String srpmsBaseLevel;

    @ApiModelProperty(value = "实验室/中心名称")
    @TableField("EXP_CENTER_NAME")
    private String expCenterName;

    @ApiModelProperty(value = "实验室/中心主任")
    @TableField("EXP_CENTER_DIRECTOR")
    private String expCenterDirector;

    @ApiModelProperty(value = "依托单位")
    @TableField("SUPPORT_ORG")
    private String supportOrg;

    @ApiModelProperty(value = "批准时间")
    @TableField("APPROVAL_TIME")
    private LocalDateTime approvalTime;

    @ApiModelProperty(value = "批准文号")
    @TableField("APPROVAL_NUM")
    private String approvalNum;

    @ApiModelProperty(value = "成立时间")
    @TableField("BUILT_TIME")
    private LocalDateTime builtTime;

    @ApiModelProperty(value = "详细地址")
    @TableField("DETAIL_ADDRESS")
    private String detailAddress;

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

    public static final String SRPMS_BASE_CAT = "SRPMS_BASE_CAT";

    public static final String SRPMS_BASE_LEVEL = "SRPMS_BASE_LEVEL";

    public static final String EXP_CENTER_NAME = "EXP_CENTER_NAME";

    public static final String EXP_CENTER_DIRECTOR = "EXP_CENTER_DIRECTOR";

    public static final String SUPPORT_ORG = "SUPPORT_ORG";

    public static final String APPROVAL_TIME = "APPROVAL_TIME";

    public static final String APPROVAL_NUM = "APPROVAL_NUM";

    public static final String BUILT_TIME = "BUILT_TIME";

    public static final String DETAIL_ADDRESS = "DETAIL_ADDRESS";

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
