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
 * 题录 10学术交流
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_OUTLINE_ACA_EXC")
@ApiModel(value="SrpmsOutlineAcaExc对象", description="题录 10学术交流")
public class SrpmsOutlineAcaExc extends Model<SrpmsOutlineAcaExc> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID，主键，业务不相关")
    @TableId(value = "ID", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "BASEID，父主键，关联SRPMS_OUTLINE_BASE的ID")
    @TableField("BASE_ID")
    private Long baseId;

    @ApiModelProperty(value = "区域")
    @TableField("REGION")
    private String region;

    @ApiModelProperty(value = "主办/参办 10-参办 20-主办")
    @TableField("SPONSOR_FLAG")
    private String sponsorFlag;

    @ApiModelProperty(value = "参与单位")
    @TableField("JOIN_ORG")
    private String joinOrg;

    @ApiModelProperty(value = "会议类型")
    @TableField("JOIN_TYPE")
    private String joinType;

    @ApiModelProperty(value = "参与形式")
    @TableField("JOIN_CAT")
    private String joinCat;

    @ApiModelProperty(value = "举办时间")
    @TableField("HOLDING_TIME")
    private LocalDateTime holdingTime;

    @ApiModelProperty(value = "会议名称")
    @TableField("TEAM_NAME")
    private String teamName;

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

    public static final String REGION = "REGION";

    public static final String SPONSOR_FLAG = "SPONSOR_FLAG";

    public static final String JOIN_ORG = "JOIN_ORG";

    public static final String JOIN_TYPE = "JOIN_TYPE";

    public static final String JOIN_CAT = "JOIN_CAT";

    public static final String HOLDING_TIME = "HOLDING_TIME";

    public static final String TEAM_NAME = "TEAM_NAME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
