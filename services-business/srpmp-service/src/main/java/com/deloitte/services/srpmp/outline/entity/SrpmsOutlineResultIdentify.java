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
 * 题录 03成果鉴定信息表
 * </p>
 *
 * @author Apeng
 * @since 2019-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SRPMS_OUTLINE_RESULT_IDENTIFY")
@ApiModel(value="SrpmsOutlineResultIdentify对象", description="题录 03成果鉴定信息表")
public class SrpmsOutlineResultIdentify extends Model<SrpmsOutlineResultIdentify> {

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

    @ApiModelProperty(value = "成果名称")
    @TableField("PRO_RESULT_NAME")
    private String proResultName;

    @ApiModelProperty(value = "第一完成单位")
    @TableField("COMPLETION_ORG")
    private String completionOrg;

    @ApiModelProperty(value = "完成人")
    @TableField("COMPLETION_PERSON")
    private String completionPerson;

    @ApiModelProperty(value = "任务来源")
    @TableField("TASK_SOURCE")
    private String taskSource;

    @ApiModelProperty(value = "鉴定/验收单位")
    @TableField("IDENTIFY_ORG")
    private String identifyOrg;

    @ApiModelProperty(value = "鉴定/验收形式")
    @TableField("IDENTIFY_WAY")
    private String identifyWay;

    @ApiModelProperty(value = "鉴定/验收时间")
    @TableField("IDENTIFY_TIME")
    private LocalDateTime identifyTime;

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

    public static final String PRO_RESULT_NAME = "PRO_RESULT_NAME";

    public static final String COMPLETION_ORG = "COMPLETION_ORG";

    public static final String COMPLETION_PERSON = "COMPLETION_PERSON";

    public static final String TASK_SOURCE = "TASK_SOURCE";

    public static final String IDENTIFY_ORG = "IDENTIFY_ORG";

    public static final String IDENTIFY_WAY = "IDENTIFY_WAY";

    public static final String IDENTIFY_TIME = "IDENTIFY_TIME";

    public static final String CREATE_TIME = "CREATE_TIME";

    public static final String CREATE_BY = "CREATE_BY";

    public static final String UPDATE_TIME = "UPDATE_TIME";

    public static final String UPDATE_BY = "UPDATE_BY";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
